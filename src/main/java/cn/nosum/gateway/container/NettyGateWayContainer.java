package cn.nosum.gateway.container;

import cn.nosum.common.annotation.Adaptive;
import cn.nosum.gateway.container.spi.GateWayContainer;
import cn.nosum.gateway.handler.FinalProcessHandler;
import cn.nosum.gateway.handler.FullHttpRequestHandler;
import cn.nosum.gateway.handler.PreProcessHandler;
import cn.nosum.gateway.handler.SlotProcessHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Adaptive
public class NettyGateWayContainer implements GateWayContainer {
	Logger logger= LoggerFactory.getLogger(NettyGateWayContainer.class);
	private int port = 8888;
	public void start(){
		// Boss线程
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		// Worker线程
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap server = new ServerBootstrap();
			server.group(bossGroup, workerGroup)
					.channel(NioServerSocketChannel.class)
					// 子线程处理类 , Handler
					.childHandler(new ChannelInitializer<SocketChannel>() {
						// 客户端初始化处理
						protected void initChannel(SocketChannel client) throws Exception {
							// Netty对HTTP协议的封装，顺序有要求
							client.pipeline().addLast(new HttpRequestDecoder());
							client.pipeline().addLast(new HttpObjectAggregator(65535));//将多个消息转化成一个
							client.pipeline().addLast(new HttpResponseEncoder());
							client.pipeline().addLast(new FullHttpRequestHandler());
							// 业务逻辑处理
							client.pipeline().addLast(new SlotProcessHandler());
							client.pipeline().addLast(new PreProcessHandler());
							client.pipeline().addLast(new FinalProcessHandler());
						}
					})
					// 针对主线程的配置 分配线程最大数量 128
					.option(ChannelOption.SO_BACKLOG, 128)
					// 针对子线程的配置 保持长连接
					.childOption(ChannelOption.SO_KEEPALIVE, true);

			// 启动服务器
			ChannelFuture f = server.bind(port).sync();
			logger.debug("NettyGateWayContainer启动成功,访问端口号是：{}",port);
			f.channel().closeFuture().sync();
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			// 关闭线程池
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
}
