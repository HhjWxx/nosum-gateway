package cn.nosum.gateway.handler.build;

import cn.nosum.common.annotation.SPI;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

@SPI("context")
public interface HandlerBuilder {
    ChannelInitializer<SocketChannel> build();
}
