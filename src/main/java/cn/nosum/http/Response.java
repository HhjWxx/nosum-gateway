package cn.nosum.http;

import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;

import java.nio.charset.StandardCharsets;


public class Response {

    // SocketChannel的封装
    private ChannelHandlerContext ctx;

    private HttpRequest req;

    public Response(ChannelHandlerContext ctx, HttpRequest req) {
        this.ctx = ctx;
        this.req = req;
    }

    public void write(JSONObject out) throws Exception {
        try {
            if (out == null) {
                return;
            }
            // 设置 http协议及请求头信息
            FullHttpResponse response = new DefaultFullHttpResponse(
                    // 设置http版本为1.1
                    HttpVersion.HTTP_1_1,
                    // 设置响应状态码
                    HttpResponseStatus.OK,
                    // 将输出值写出 编码为UTF-8
                    Unpooled.wrappedBuffer(out.toJSONString().getBytes(StandardCharsets.UTF_8)));
            response.headers().set("Content-Type", "application/json;");
            ctx.writeAndFlush(response);
        } finally {
            ctx.close();
        }
    }
}
