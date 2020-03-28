package cn.nosum.common.http.servlet.factory;

import cn.nosum.common.http.servlet.IGateWayServlet;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Servlet工厂，根据不同的请求类型调用不同的处理类
 */
public class GateWayServletFactory {
    private static  Map<String, IGateWayServlet> gateWayServletMap = new ConcurrentHashMap<>();

    public static IGateWayServlet getGateWayServlet(String method){
        return gateWayServletMap.get(method);
    }
    public static void register(String method,IGateWayServlet gateWayServlet){
        gateWayServletMap.put(method,gateWayServlet);
    }
}
