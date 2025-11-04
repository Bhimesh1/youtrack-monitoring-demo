package demo;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Gauge;
import io.prometheus.client.exporter.PushGateway;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CpuPusher {
    public static void main(String[] args) throws Exception {
        
        String pgwUrl = System.getenv().getOrDefault("PGW_URL", "pushgateway:9091");
        String job = System.getenv().getOrDefault("JOB_NAME", "java-demo");
        String instance = System.getenv().getOrDefault("INSTANCE", "java-1");

        CollectorRegistry registry = new CollectorRegistry();
        Gauge cpu = Gauge.build()
                .name("cpu_usage")
                .help("Simulated CPU usage percent")
                .register(registry);

        PushGateway pg = new PushGateway(pgwUrl);
        Random rnd = new Random();

        while (true) {
            int value = 10 + rnd.nextInt(86); // 10..95
            cpu.set(value);

            Map<String, String> groupingKey = new HashMap<>();
            groupingKey.put("job", job);
            groupingKey.put("instance", instance);

        
            pg.pushAdd(registry, job, groupingKey);

            System.out.println("Pushed value: " + value);

            Thread.sleep(5000);
        }
    }
}
