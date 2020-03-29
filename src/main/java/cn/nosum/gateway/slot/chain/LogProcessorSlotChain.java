package cn.nosum.gateway.slot.chain;

import cn.nosum.common.http.entity.Context;
import cn.nosum.gateway.slot.AbstractLinkedProcessorSlot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogProcessorSlotChain extends AbstractLinkedProcessorSlot<Context> {

    private static final Logger logger = LoggerFactory.getLogger(LogProcessorSlotChain.class);

    @Override
    public void exec(Context context) throws Throwable {
        // TODO 日志记录
        logger.debug(context.getRequest().getUrl());
        fireExec(context);
    }
}
