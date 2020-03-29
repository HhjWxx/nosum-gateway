package cn.nosum.gateway.handler.build;

import cn.nosum.common.annotation.Adaptive;
import cn.nosum.gateway.handler.*;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;


@Adaptive
public class ContextHandlerBuilder implements HandlerBuilder{
    private static ChannelInitializer<SocketChannel> channelInitializer=null;
    @Override
    public ChannelInitializer<SocketChannel> build() {
        if (channelInitializer==null){
            channelInitializer = new ChannelInitializer<SocketChannel>() {
                // 客户端初始化处理
                protected void initChannel(SocketChannel client) throws Exception {
                    client.pipeline().addLast(new HttpRequestDecoder());
                    client.pipeline().addLast(new HttpObjectAggregator(65535));
                    client.pipeline().addLast(new HttpResponseEncoder());
                    client.pipeline().addLast(new FullHttpRequestHandler());
                    client.pipeline().addLast(new SlotProcessHandler());
                }
            };
        }
        return channelInitializer;
    }
}
