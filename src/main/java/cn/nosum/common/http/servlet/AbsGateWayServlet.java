package cn.nosum.common.http.servlet;

import cn.nosum.common.http.entity.Context;

public abstract class AbsGateWayServlet implements IGateWayServlet{

    @Override
    public void service(Context context) {
        if (this.doCheck(context)){
            this.doService(context);
        }
    }

    protected abstract void doService(Context context);

    protected abstract boolean doCheck(Context context);
}
