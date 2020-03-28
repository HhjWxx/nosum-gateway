package cn.nosum.gateway.handler;

import cn.nosum.common.dto.ResultInfo;
import cn.nosum.common.enums.ResultEnum;
import cn.nosum.common.http.entity.Context;
import cn.nosum.common.http.entity.Response;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class FinalProcessHandler extends SimpleChannelInboundHandler<Context> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Context context) throws Exception {
        Response response = context.getResponse();
        // 实际业务处理
        response.write(ResultInfo.valueOf(ResultEnum.SUCCESS));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
    }
}