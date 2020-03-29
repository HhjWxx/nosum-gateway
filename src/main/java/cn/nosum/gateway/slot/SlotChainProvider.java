package cn.nosum.gateway.slot;


import cn.nosum.common.extension.ExtensionLoader;
import cn.nosum.gateway.slot.build.DefaultSlotChainBuilder;
import cn.nosum.gateway.slot.build.SlotChainBuilder;

public final class SlotChainProvider {
    private SlotChainProvider() {}
    private static volatile SlotChainBuilder builder = null;

    public static ProcessorSlotChain newSlotChain() {
        if (builder != null) {
            return builder.build();
        }
        if (builder == null) {
            builder = ExtensionLoader.getExtensionLoader(SlotChainBuilder.class).getAdaptiveExtension();
        }
        return builder.build();
    }
}