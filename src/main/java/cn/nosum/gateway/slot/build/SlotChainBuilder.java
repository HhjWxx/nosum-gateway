package cn.nosum.gateway.slot.build;


import cn.nosum.common.annotation.SPI;
import cn.nosum.gateway.slot.ProcessorSlotChain;

/**
 * 处理槽构建器顶层接口
 */
@SPI("context")
public interface SlotChainBuilder {

    /**
     * 构建处理槽链
     * @return 返回构建完成的处理槽链
     */
    ProcessorSlotChain build();
}