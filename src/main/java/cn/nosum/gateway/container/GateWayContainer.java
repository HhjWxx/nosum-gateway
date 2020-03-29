package cn.nosum.gateway.container;


import cn.nosum.common.annotation.SPI;

@SPI("netty")
public interface GateWayContainer {
    void start();
}
