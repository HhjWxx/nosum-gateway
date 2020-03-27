package cn.nosum.handler.spi;


public interface IRequestHandler {
    boolean doCheck(Object msg);
    void doRun(Object msg);
}
