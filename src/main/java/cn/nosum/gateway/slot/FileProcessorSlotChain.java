package cn.nosum.gateway.slot;

import cn.nosum.common.http.entity.Context;
import cn.nosum.gateway.chain.AbstractLinkedProcessorSlot;

/**
 * 创建文件，进行IO操作
 */
public class FileProcessorSlotChain extends AbstractLinkedProcessorSlot<Context> {
    @Override
    public void exec(Context context) throws Throwable {
        fireExec(context);
    }
}
