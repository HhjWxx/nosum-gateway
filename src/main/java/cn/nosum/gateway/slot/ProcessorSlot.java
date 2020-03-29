package cn.nosum.gateway.slot;



/**
 * 处理槽链顶层接口，参考 sentinel
 */
public interface ProcessorSlot<T> {

    void exec(T t) throws Throwable;

}