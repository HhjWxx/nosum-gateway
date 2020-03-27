package cn.nosum;

import cn.nosum.container.NettyGatewayContainer;

public class NoSumGateWayStart {
    public static void main(String[] args) {
        new NettyGatewayContainer().start();
    }
}
