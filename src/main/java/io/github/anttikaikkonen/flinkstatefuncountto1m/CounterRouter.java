package io.github.anttikaikkonen.flinkstatefuncountto1m;

import io.github.anttikaikkonen.flinkstatefuncountto1m.generated.Counter;
import org.apache.flink.statefun.sdk.io.Router;

public class CounterRouter implements Router<Counter> {

    @Override
    public void route(Counter counter, Downstream<Counter> downstream) {
      downstream.forward(CounterFunction.TYPE, ""+counter.getCount(), counter);
    }

}
