package cn.nosum.container;

import cn.nosum.handler.GateWayHandler;
import cn.nosum.handler.HttpRequestHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;


//Netty就是一个同时支持多协议的网络通信框架
public class NettyGatewayContainer {
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
							// HttpResponseEncoder 编码器
							client.pipeline().addLast(new HttpResponseEncoder());
							// HttpRequestDecoder 解码器
							client.pipeline().addLast(new HttpRequestDecoder());
							// 业务逻辑处理
							client.pipeline().addLast(new HttpRequestHandler());
							client.pipeline().addLast(new GateWayHandler());
						}
					})
					// 针对主线程的配置 分配线程最大数量 128
					.option(ChannelOption.SO_BACKLOG, 128)
					// 针对子线程的配置 保持长连接
					.childOption(ChannelOption.SO_KEEPALIVE, true);

			// 启动服务器
			ChannelFuture f = server.bind(port).sync();
			System.out.println("已启动，监听的端口是：" + port);
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
