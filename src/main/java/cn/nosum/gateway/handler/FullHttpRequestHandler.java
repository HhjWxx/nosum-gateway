package cn.nosum.gateway.handler;


import cn.nosum.common.http.entity.Context;
import cn.nosum.common.http.factory.ContextFactory;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.ReferenceCountUtil;

public class FullHttpRequestHandler  extends SimpleChannelInboundHandler<FullHttpRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest req) throws Exception {
        // 构建上下文对象
        Context context= ContextFactory.build(ctx,req);
        ctx.fireChannelRead(context);
        ReferenceCountUtil.release(req);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
