package cn.nosum.gateway.handler;

import cn.nosum.common.http.entity.Context;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class PreProcessHandler extends SimpleChannelInboundHandler<Context> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Context context) throws Exception {
        ctx.fireChannelRead(context);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
