package cn.nosum.common.extension;

import cn.nosum.common.annotation.SPI;

@SPI("adaptive")
public interface ExtensionFactory {

    /**
     * Get extension.
     *
     * @param type object type.
     * @param name object name.
     * @return object instance.
     */
    <T> T getExtension(Class<T> type, String name);

}