package cn.nosum.common.http.entity;


import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.handler.codec.http.multipart.Attribute;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Request {

    private FullHttpRequest req;

    private Map<String, String> paramMap;


    public Request(FullHttpRequest req) {
        this.req = req;
        initParam(req);
    }

    private void initParam(FullHttpRequest req){
        try{
            HttpMethod method = req.method();
            paramMap = new HashMap<>();
            if (HttpMethod.GET == method) {
                // GET请求
                QueryStringDecoder decoder = new QueryStringDecoder(req.uri());
                decoder.parameters().forEach((key, value) -> {
                    // entry.getValue()是一个List, 只取第一个元素
                    paramMap.put(key, value.get(0));
                });
            } else if (HttpMethod.POST == method) {
                // POST请求
                HttpPostRequestDecoder decoder = new HttpPostRequestDecoder(req);
                decoder.offer(req);
                List<InterfaceHttpData> paramList = decoder.getBodyHttpDatas();
                for (InterfaceHttpData param : paramList) {
                    Attribute data = (Attribute) param;
                    paramMap.put(data.getName(), data.getValue());
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public String getUrl() {
        return this.req.uri().split("[?]")[0];
    }

    public String getMethod() {
        return req.method().name();
    }

    public Map<String, String> getParameters() {
        return paramMap;
    }

    public String getParameter(String name) {
        return paramMap.get(name);
    }

}
