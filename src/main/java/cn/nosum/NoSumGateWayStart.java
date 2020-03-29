package cn.nosum;

import cn.nosum.common.extension.ExtensionLoader;
import cn.nosum.gateway.container.GateWayContainer;

public class NoSumGateWayStart {
    public static void main(String[] args) {
        GateWayContainer container = ExtensionLoader.getExtensionLoader(GateWayContainer.class).getAdaptiveExtension();
        container.start();
    }
}
