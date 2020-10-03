package live.xsg.metrics.statistics.task;

import live.xsg.metrics.statistics.aggregator.Aggregator;
import live.xsg.metrics.storage.MetricsStorage;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建 StatisticsTask 的 Builder
 * Created by xsg on 2020/10/2.
 */
public class StatisticsTaskBuilder {

    //执行统计的频率，默认 30 s
    private static final long DEFAULT_DELAY = 10000L;
    //默认的时间窗口大小，1 分钟
    private static final long DEFAULT_WINDOW = 60 * 1000L;

    //执行频率，默认 30 s
    private long delay = DEFAULT_DELAY;
    //统计的接口请求数据的时间窗口
    private long window = DEFAULT_WINDOW;
    //数据的存储接口
    private MetricsStorage metricsStorage;
    //具体的聚合统计实现
    private List<Aggregator> aggregators = new ArrayList<>();

    private StatisticsTaskBuilder() {}

    public static StatisticsTaskBuilder builder() {
        return new StatisticsTaskBuilder();
    }

    public StatisticsTaskBuilder delay(long delay) {
        this.delay = delay;
        return this;
    }

    public StatisticsTaskBuilder window(long window) {
        this.window = window;
        return this;
    }

    public StatisticsTaskBuilder storage(MetricsStorage storage) {
        this.metricsStorage = storage;
        return this;
    }

    public StatisticsTaskBuilder aggregator(Aggregator aggregator) {
        this.aggregators.add(aggregator);
        return this;
    }

    public StatisticsTask build() {
        if (this.metricsStorage == null) {
//            this.metricsStorage =
        }
        return new StatisticsTask(this.delay, this.window, this.metricsStorage, this.aggregators);
    }
}
