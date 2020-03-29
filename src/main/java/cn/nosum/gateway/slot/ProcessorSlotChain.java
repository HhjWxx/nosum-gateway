package cn.nosum.gateway.slot;

import cn.nosum.common.annotation.SPI;
import cn.nosum.common.http.entity.Context;

@SPI("link")
public interface ProcessorSlotChain extends ProcessorSlot<Context> {

    /**
     * Add a processor to the head of this slot chain.
     *
     * @param protocolProcessor processor to be added.
     */
    void addFirst(AbstractLinkedProcessorSlot<Context> protocolProcessor);

    /**
     * Add a processor to the tail of this slot chain.
     *
     * @param protocolProcessor processor to be added.
     */
    void addLast(AbstractLinkedProcessorSlot<Context> protocolProcessor);
}