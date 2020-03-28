package cn.nosum.common.http.factory;


import cn.nosum.common.http.entity.Context;
import cn.nosum.common.http.entity.Request;
import cn.nosum.common.http.entity.Response;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

public class ContextFactory {
    private ContextFactory(){}
    public static Context build(ChannelHandlerContext ctx, FullHttpRequest request){
        if(request!=null){
            return new Context(new Request(request),new Response(ctx));
        }
        return null;
    }
}
