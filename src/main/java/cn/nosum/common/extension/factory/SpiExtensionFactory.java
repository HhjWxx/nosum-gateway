package cn.nosum.common.extension.factory;

import cn.nosum.common.annotation.SPI;
import cn.nosum.common.extension.ExtensionFactory;
import cn.nosum.common.extension.ExtensionLoader;

public class SpiExtensionFactory implements ExtensionFactory {

    @Override
    public <T> T getExtension(Class<T> type, String name) {
        if (type.isInterface() && type.isAnnotationPresent(SPI.class)) {
            ExtensionLoader<T> loader = ExtensionLoader.getExtensionLoader(type);
            if (!loader.getSupportedExtensions().isEmpty()) {
                return loader.getAdaptiveExtension();
            }
        }
        return null;
    }

}