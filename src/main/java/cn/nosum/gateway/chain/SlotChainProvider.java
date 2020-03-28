package cn.nosum.gateway.chain;


public final class SlotChainProvider {
    private SlotChainProvider() {}
    private static volatile SlotChainBuilder builder = null;

    public static ProcessorSlotChain newSlotChain() {
        if (builder != null) {
            return builder.build();
        }
        // TODO 使用 SPI 与静态扩展点优化
        if (builder == null) {
            builder = new DefaultSlotChainBuilder();
        }
        return builder.build();
    }
}