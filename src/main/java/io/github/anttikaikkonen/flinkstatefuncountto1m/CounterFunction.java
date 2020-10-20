package io.github.anttikaikkonen.flinkstatefuncountto1m;

import io.github.anttikaikkonen.flinkstatefuncountto1m.generated.Counter;
import org.apache.flink.statefun.sdk.Address;
import org.apache.flink.statefun.sdk.Context;
import org.apache.flink.statefun.sdk.FunctionType;
import org.apache.flink.statefun.sdk.StatefulFunction;

public class CounterFunction implements StatefulFunction {

    public static final FunctionType TYPE = new FunctionType("example", "counter_function");

    @Override
    public void invoke(Context context, Object input) {
        Counter counter = (Counter) input;
        if (counter.getCount() < 1000000) {
            Counter next = Counter.newBuilder().setCount(counter.getCount()+1).build();
            context.send(new Address(TYPE, ""+next.getCount()), next);
        }
    }
    
}