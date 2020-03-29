package cn.nosum.gateway.slot.chain;

import cn.nosum.common.dto.ResultInfo;
import cn.nosum.common.http.entity.Context;
import cn.nosum.gateway.slot.AbstractLinkedProcessorSlot;

public class FinalProcessorSlotChain extends AbstractLinkedProcessorSlot<Context> {

    @Override
    public void exec(Context context) throws Throwable {
        // TODO 这里直接使用了 stat 脚本返回值作为输出，实际处理应该更为复杂
        context.getResponse().write(ResultInfo.valueOf(context.getResult()));
    }
}
