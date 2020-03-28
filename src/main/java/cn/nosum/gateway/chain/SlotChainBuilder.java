package cn.nosum.gateway.chain;


/**
 * 处理槽构建器顶层接口
 */
public interface SlotChainBuilder {

    /**
     * 构建处理槽链
     * @return 返回构建完成的处理槽链
     */
    ProcessorSlotChain build();
}