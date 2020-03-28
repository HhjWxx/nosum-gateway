package cn.nosum.gateway.handler;

import cn.nosum.common.dto.ResultInfo;
import cn.nosum.common.http.entity.Context;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class FinalProcessHandler extends SimpleChannelInboundHandler<Context> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Context context) throws Exception {
        // TODO 这里直接使用了 stat 脚本返回值作为输出，实际处理应该更为复杂
        context.getResponse().write(ResultInfo.valueOf(context.getResult()));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
    }
}