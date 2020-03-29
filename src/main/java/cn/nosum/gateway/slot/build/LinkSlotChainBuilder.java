package cn.nosum.gateway.slot.build;

import cn.nosum.common.annotation.Adaptive;
import cn.nosum.common.extension.ExtensionLoader;
import cn.nosum.gateway.slot.ProcessorSlotChain;
import cn.nosum.gateway.slot.chain.FileProcessorSlotChain;
import cn.nosum.gateway.slot.chain.LogProcessorSlotChain;
import cn.nosum.gateway.slot.chain.UrlProcessorSlotChain;

@Adaptive
public class LinkSlotChainBuilder implements SlotChainBuilder {
    private ProcessorSlotChain chain=ExtensionLoader.getExtensionLoader(ProcessorSlotChain.class).getAdaptiveExtension();
    {
         chain.addLast(new UrlProcessorSlotChain());
         chain.addLast(new LogProcessorSlotChain());
         chain.addLast(new FileProcessorSlotChain());
    }

    @Override
    public ProcessorSlotChain build() {
        return chain;
    }
}
