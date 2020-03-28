package cn.nosum.gateway.chain;

public abstract class AbstractLinkedProcessorSlot<T> implements ProcessorSlot<T> {

    private AbstractLinkedProcessorSlot<T> next = null;

    public void fireExec(T t) throws Throwable {
        if (next != null) {
            next.exec(t);
        }
    }

    public AbstractLinkedProcessorSlot<T> getNext() {
        return next;
    }

    public void setNext(AbstractLinkedProcessorSlot<T> next) {
        this.next = next;
    }

}