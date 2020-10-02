package live.xsg.metrics;

import live.xsg.metrics.exception.MetricsException;
import live.xsg.metrics.request.ApiRequest;
import live.xsg.metrics.statistics.aggregator.CountAggregator;
import live.xsg.metrics.statistics.task.StatisticsTask;
import live.xsg.metrics.statistics.task.StatisticsTaskBuilder;
import live.xsg.metrics.storage.MapMetricsStorage;
import org.testng.annotations.Test;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * Created by xsg on 2020/10/2.
 */
@Test
public class MetricsCollectorTest {

    private String[] mockApi = {"/user/login", "/user/register", "/user/logout", "/user/detail"};

    public void metrics_test() throws MetricsException {
        MapMetricsStorage storage = new MapMetricsStorage();
        MetricsCollector metricsCollector = new MetricsCollector(storage);
        StatisticsTask statisticsTask = StatisticsTaskBuilder.builder()
                .aggregator(new CountAggregator())
                .storage(storage)
                .build();

        statisticsTask.execute();

        for (int i = 0; i < 1000; i++) {
            String api = mockApi[ThreadLocalRandom.current().nextInt(mockApi.length)];
            long startRequest = System.currentTimeMillis() + 30000;
            long endRequest = startRequest + ThreadLocalRandom.current().nextLong(5000);
            ApiRequest request = new ApiRequest(api, startRequest, endRequest);

            metricsCollector.collector(request);
        }

        sleep(2);
    }

    private void sleep(int minutes) {
        try {
            TimeUnit.MINUTES.sleep(minutes);
        } catch (InterruptedException ignored) { }
    }
}
