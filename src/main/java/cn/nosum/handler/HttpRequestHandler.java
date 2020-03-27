package cn.nosum.handler;

import cn.nosum.http.Request;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpRequest;

public class HttpRequestHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof HttpRequest){
            HttpRequest req = (HttpRequest) msg;
            // TODO
            Request request = new Request(ctx,req);
            String uri=request.getUrl();
            if (uri.equals("/favicon.ico")) {
                return;
            }
            System.err.println(request.getParameter("name"));
            System.err.println(request.getUrl());
            // 传递request对象到下一个handler
            ctx.fireChannelRead(request);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

    }
}
