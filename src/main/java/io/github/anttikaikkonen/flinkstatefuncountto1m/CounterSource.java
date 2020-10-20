package io.github.anttikaikkonen.flinkstatefuncountto1m;

import io.github.anttikaikkonen.flinkstatefuncountto1m.generated.Counter;
import org.apache.flink.streaming.api.functions.source.SourceFunction;

public class CounterSource implements SourceFunction<Counter> {

    private static final long serialVersionUID = 1;

    private volatile boolean cancelled;

    @Override
    public void run(SourceContext<Counter> ctx) throws InterruptedException {
        synchronized (ctx.getCheckpointLock()) {
          ctx.collect(Counter.newBuilder().setCount(1).build());
        }
        while (!cancelled) {//Don't exit the run method to avoid mailbox is closed exception
            Thread.sleep(10);
        }
    }

    @Override
    public void cancel() {
        cancelled = true;
    }
    
}
