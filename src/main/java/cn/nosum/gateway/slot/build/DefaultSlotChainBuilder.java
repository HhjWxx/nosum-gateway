package cn.nosum.gateway.slot.build;

import cn.nosum.common.annotation.Adaptive;
import cn.nosum.gateway.slot.DefaultProcessorSlotChain;
import cn.nosum.gateway.slot.ProcessorSlotChain;
import cn.nosum.gateway.slot.chain.FileProcessorSlotChain;
import cn.nosum.gateway.slot.chain.LogProcessorSlotChain;
import cn.nosum.gateway.slot.chain.UrlProcessorSlotChain;

@Adaptive
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
