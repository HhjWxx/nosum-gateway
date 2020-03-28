package cn.nosum;

import cn.nosum.gateway.container.NettyGatewayContainer;

public class NoSumGateWayStart {
    public static void main(String[] args) {
        new NettyGatewayContainer().start();
    }
}
