package cn.nosum.common.http.servlet.impl;

import cn.nosum.common.constant.CommonConstants;
import cn.nosum.common.http.entity.Context;
import cn.nosum.common.http.servlet.AbsGateWayServlet;

public class GetGateWayServlet extends AbsGateWayServlet {

    @Override
    protected void doService(Context context) {

    }

    @Override
    protected boolean doCheck(Context context) {
        if (context==null){
            return false;
        }
        return CommonConstants.REQUEST_METHOD_GET.equalsIgnoreCase(context.getRequest().getMethod());
    }
}
