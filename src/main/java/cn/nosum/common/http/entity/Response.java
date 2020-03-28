package cn.nosum.common.http.entity;

import cn.nosum.common.dto.ResultInfo;
import com.alibaba.fastjson.JSON;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import java.nio.charset.StandardCharsets;


public class Response {

    private ChannelHandlerContext ctx;

    public Response(ChannelHandlerContext ctx) {
        this.ctx = ctx;
    }

    public void write(ResultInfo resultInfo) throws Exception {
        try {
            if (resultInfo == null) {
                return ;
            }
            // 设置 http协议及请求头信息
            FullHttpResponse response = new DefaultFullHttpResponse(
                    // 设置http版本为1.1
                    HttpVersion.HTTP_1_1,
                    // 设置响应状态码
                    HttpResponseStatus.OK,
                    // 将输出值写出 编码为UTF-8
                    Unpooled.wrappedBuffer(JSON.toJSONString(resultInfo).getBytes(StandardCharsets.UTF_8)));
            response.headers().set("Content-Type", "application/json;");
            ctx.writeAndFlush(response);
        } finally {
            ctx.close();
        }
    }
}
