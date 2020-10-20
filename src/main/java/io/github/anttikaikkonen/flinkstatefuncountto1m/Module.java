package io.github.anttikaikkonen.flinkstatefuncountto1m;

import org.apache.flink.statefun.sdk.spi.StatefulFunctionModule;
import java.util.Map;
import org.apache.flink.statefun.flink.io.datastream.SourceFunctionSpec;
import org.apache.flink.statefun.sdk.io.IngressIdentifier;

public final class Module implements StatefulFunctionModule {
    
    @Override
    public void configure(Map<String, String> globalConfiguration, Binder binder) {
        IngressIdentifier nameSourceId = new IngressIdentifier(String.class, "example", "name_source");
        SourceFunctionSpec nameSourceSpec = new SourceFunctionSpec(nameSourceId, new CounterSource());
        binder.bindIngress(nameSourceSpec);
        binder.bindIngressRouter(nameSourceId, new CounterRouter());
        binder.bindFunctionProvider(CounterFunction.TYPE, unused -> new CounterFunction());
    }
    
}
