package cn.nosum.gateway.container;

import cn.nosum.common.annotation.Adaptive;
import cn.nosum.common.util.Convert;
import cn.nosum.common.util.PropertiesUtil;
import cn.nosum.gateway.handler.build.HandlerProvider;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Adaptive
public class NettyGateWayContainer implements GateWayContainer {
    Logger logger = LoggerFactory.getLogger(NettyGateWayContainer.class);
    private int port = Convert.toInt(PropertiesUtil.getProperty("container.port"));

    public void start() {
        // Boss线程
        EventLoopGroup bossGroup = new NioEventLoopGroup(6);
        // Worker线程
        EventLoopGroup workerGroup = new NioEventLoopGroup(12);
        try {
            ServerBootstrap server = new ServerBootstrap();
            server.group(bossGroup, workerGroup);
            server.channel(NioServerSocketChannel.class);
            server.childHandler(HandlerProvider.newHandlerChannel());
            server.option(ChannelOption.SO_BACKLOG, 128);
            server.option(ChannelOption.TCP_NODELAY,true);
            server.childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
            server.childOption(ChannelOption.TCP_NODELAY, true);
            // server.childOption(ChannelOption.SO_KEEPALIVE, true);
            // 启动服务器
            ChannelFuture f = server.bind(port).sync();
            logger.debug("NettyGateWayContainer启动成功,访问端口号是：{}", port);
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭线程池
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
