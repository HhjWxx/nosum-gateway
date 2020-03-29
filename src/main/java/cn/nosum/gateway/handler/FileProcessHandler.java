package cn.nosum.gateway.handler;

import cn.nosum.common.constant.CommonConstants;
import cn.nosum.common.http.entity.Context;
import cn.nosum.common.util.LinuxCmd;
import cn.nosum.common.util.NettyFileUtil;
import cn.nosum.common.util.PropertiesUtil;
import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class FileProcessHandler extends SimpleChannelInboundHandler<Context> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Context context) throws Exception {
        NettyFileUtil.dataToFile(JSON.toJSONString(context.getRequest().getParameters()),
                PropertiesUtil.getProperty(CommonConstants.REQUEST_FILE_SAVE_FOLDER_NAME) + context.getRequest().getUrl(),
                true);
        context.setResult(LinuxCmd.executeLinuxCmd("stat "+context.getRequest().getUrl()));
        ctx.fireChannelRead(context);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
