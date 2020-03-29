package cn.nosum.gateway.slot.chain;

import cn.nosum.common.exception.ExcludeException;
import cn.nosum.gateway.slot.AbstractLinkedProcessorSlot;
import cn.nosum.common.constant.Constants;
import cn.nosum.common.http.entity.Context;

public class UrlProcessorSlotChain extends AbstractLinkedProcessorSlot<Context> {
    @Override
    public void exec(Context context) throws Throwable {
        String uri=context.getRequest().getUrl();
        // 过滤掉请求图标的地址
        if (uri.equals(Constants.ICON_URL)) {
            throw new ExcludeException();
        }
        // 执行下一个过滤器
        fireExec(context);
    }
}
