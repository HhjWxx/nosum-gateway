package cn.nosum.gateway.slot;

import cn.nosum.common.http.entity.Context;

public class DefaultProcessorSlotChain extends ProcessorSlotChain {

    AbstractLinkedProcessorSlot<Context> first = new AbstractLinkedProcessorSlot<Context>() {
        @Override
        public void exec(Context context) throws Throwable {
            this.fireExec(context);
        }
    };
    AbstractLinkedProcessorSlot<Context> end = first;

    @Override
    public void addFirst(AbstractLinkedProcessorSlot<Context> protocolProcessor) {
        protocolProcessor.setNext(first.getNext());
        first.setNext(protocolProcessor);
        if (end == first) {
            end = protocolProcessor;
        }
    }

    @Override
    public void addLast(AbstractLinkedProcessorSlot<Context> protocolProcessor) {
        end.setNext(protocolProcessor);
        end = protocolProcessor;
    }

    @Override
    public void exec(Context context) throws Throwable {
        first.exec(context);
    }
}