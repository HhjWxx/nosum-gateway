package cn.nosum.gateway.chain;

import cn.nosum.gateway.slot.FileProcessorSlotChain;
import cn.nosum.gateway.slot.LogProcessorSlotChain;
import cn.nosum.gateway.slot.UrlProcessorSlotChain;

public class DefaultSlotChainBuilder implements SlotChainBuilder {
    @Override
    public ProcessorSlotChain build() {
        ProcessorSlotChain chain = new DefaultProcessorSlotChain();
        chain.addLast(new UrlProcessorSlotChain());
        chain.addLast(new LogProcessorSlotChain());
        chain.addLast(new FileProcessorSlotChain());
        return chain;
    }
}
