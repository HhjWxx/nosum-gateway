package cn.nosum.gateway.container.spi;


import cn.nosum.common.annotation.SPI;

@SPI("netty")
public interface GateWayContainer {
    public void start();
}
