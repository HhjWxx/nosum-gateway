package cn.nosum.gateway.handler;

import cn.nosum.common.dto.ResultInfo;
import cn.nosum.common.enums.ResultEnum;
import cn.nosum.common.exception.ExcludeException;
import cn.nosum.common.http.entity.Context;
import cn.nosum.gateway.slot.ProcessorSlot;
import cn.nosum.gateway.slot.build.SlotChainProvider;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 执行处理槽链
 */
public class SlotProcessHandler extends SimpleChannelInboundHandler<Context> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Context context) throws Exception {
        // TODO 进行过滤链的执行，应该使用扩展点实现获取
        ProcessorSlot<Context> chain = SlotChainProvider.newSlotChain();
        try {
            chain.exec(context);
            // 传递上下文对象到下一个 handler
            ctx.fireChannelRead(context);
        } catch (ExcludeException e) {
            context.getResponse().write(ResultInfo.valueOf(ResultEnum.URL_EXCLUDE));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            context.getResponse().write(ResultInfo.valueOf(ResultEnum.FAILING));
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
