package live.xsg.metrics.statistics.task;

import live.xsg.metrics.exception.MetricsException;
import live.xsg.metrics.request.ApiRequest;
import live.xsg.metrics.statistics.RequestStat;
import live.xsg.metrics.statistics.aggregator.Aggregator;
import live.xsg.metrics.storage.MetricsStorage;
import live.xsg.metrics.thread.DefaultThreadFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 定时任务，定时拉取接口请求数据进行数据的统计
 * Created by xsg on 2020/10/2.
 */
public class StatisticsTask {

    //默认一个线程
    private static final int DEFAULT_CORE_POOL_SIZE = 1;

    //定时执行数据的统计任务
    private ScheduledExecutorService scheduled;
    //执行频率
    private final long delay;
    //统计的接口请求数据的时间窗口
    private final long window;
    //数据的存储接口
    private MetricsStorage metricsStorage;
    //具体的聚合统计实现
    private List<Aggregator> aggregators;

    public StatisticsTask(long delay, long window, MetricsStorage metricsStorage, List<Aggregator> aggregators) {
        this.delay = delay;
        this.window = window;
        this.metricsStorage = metricsStorage;
        this.aggregators = aggregators;
    }

    /**
     * 执行数据的统计操作
     */
    public synchronized void execute() throws MetricsException {
        if (this.scheduled != null) {
            throw new MetricsException("定时任务已经正在执行.");
        }

        this.scheduled = Executors.newScheduledThreadPool(DEFAULT_CORE_POOL_SIZE, new DefaultThreadFactory("statics-task", false));
        this.scheduled.scheduleWithFixedDelay(() -> {
            try {
                long end = System.currentTimeMillis();
                long start = end - window;

                //获取要统计的数据
                List<ApiRequest> apiRequests = StatisticsTask.this.metricsStorage.getApiRequest(start, end);
                //将要统计的数据装换为 map 结构，key 为请求的 api， value 为 api 对应的接口请求数据集合
                Map<String, List<ApiRequest>> requestMap =
                        apiRequests.stream().collect(Collectors.groupingBy(ApiRequest::getApi));

                List<RequestStat> requestStats = new ArrayList<>(requestMap.size());
                if (aggregators != null) {
                    requestMap.forEach((k, v) -> {
                        RequestStat requestStat = new RequestStat(k);

                        for (Aggregator aggregator : aggregators) {
                            aggregator.aggregate(requestStat, v);
                        }

                        requestStats.add(requestStat);
                    });
                }

                //保存结果
                StatisticsTask.this.metricsStorage.saveRequestStat(requestStats);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, this.delay, this.delay, TimeUnit.MILLISECONDS);
    }
}
