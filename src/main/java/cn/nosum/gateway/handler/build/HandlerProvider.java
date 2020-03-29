package cn.nosum.gateway.handler.build;


import cn.nosum.common.extension.ExtensionLoader;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

public final class HandlerProvider {
    private HandlerProvider(){}
    private static volatile HandlerBuilder builder = null;
    public static ChannelInitializer<SocketChannel> newHandlerChannel(){
        if (builder != null) {
            return builder.build();
        }
        if (builder == null) {
            builder = ExtensionLoader.getExtensionLoader(HandlerBuilder.class).getAdaptiveExtension();
        }
        return builder.build();
    }
}
