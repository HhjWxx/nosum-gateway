package cn.nosum.gateway.slot;

import cn.nosum.common.http.entity.Context;
import cn.nosum.common.util.NettyFileUtil;
import cn.nosum.gateway.chain.AbstractLinkedProcessorSlot;
import com.alibaba.fastjson.JSON;

/**
 * 创建文件，进行IO操作
 */
public class FileProcessorSlotChain extends AbstractLinkedProcessorSlot<Context> {
    @Override
    public void exec(Context context) throws Throwable {
        NettyFileUtil.dataToFile(JSON.toJSONString(context.getRequest().getParameters()),context.getRequest().getUrl());
        fireExec(context);
    }
}
