package cn.nosum.gateway.slot.chain;

import cn.nosum.common.constant.CommonConstants;
import cn.nosum.common.http.entity.Context;
import cn.nosum.common.util.LinuxCmd;
import cn.nosum.common.util.NettyFileUtil;
import cn.nosum.common.util.PropertiesUtil;
import cn.nosum.gateway.slot.AbstractLinkedProcessorSlot;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileProcessorSlotChain extends AbstractLinkedProcessorSlot<Context> {

    @Override
    public void exec(Context context) throws Throwable {
        NettyFileUtil.dataToFile(JSON.toJSONString(context.getRequest().getParameters()),
                PropertiesUtil.getProperty(CommonConstants.REQUEST_FILE_SAVE_FOLDER_NAME) + context.getRequest().getUrl(),
                false);
        context.setResult(LinuxCmd.executeLinuxCmd("stat "
                + PropertiesUtil.getProperty(CommonConstants.REQUEST_FILE_SAVE_FOLDER_NAME)
                + context.getRequest().getUrl()));
        fireExec(context);
    }
}
