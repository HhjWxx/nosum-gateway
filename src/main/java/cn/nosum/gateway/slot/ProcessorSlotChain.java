package cn.nosum.gateway.slot;

import cn.nosum.common.http.entity.Context;

public abstract class ProcessorSlotChain extends AbstractLinkedProcessorSlot<Context> {

    /**
     * Add a processor to the head of this slot chain.
     *
     * @param protocolProcessor processor to be added.
     */
    public abstract void addFirst(AbstractLinkedProcessorSlot<Context> protocolProcessor);

    /**
     * Add a processor to the tail of this slot chain.
     *
     * @param protocolProcessor processor to be added.
     */
    public abstract void addLast(AbstractLinkedProcessorSlot<Context> protocolProcessor);
}