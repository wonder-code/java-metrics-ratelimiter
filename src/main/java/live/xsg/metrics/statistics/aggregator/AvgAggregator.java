package live.xsg.metrics.statistics.aggregator;

import live.xsg.metrics.request.ApiRequest;
import live.xsg.metrics.statistics.RequestStat;

import java.util.List;

/**
 * 统计平均响应时间
 * Created by xsg on 2020/10/3.
 */
public class AvgAggregator implements Aggregator {

    @Override
    public void aggregate(RequestStat requestStat, List<ApiRequest> apiRequests) {
        if (apiRequests == null) return;

        //请求次数
        int requestCount = apiRequests.size();
        //请求总执行时间
        long totalExec = apiRequests.stream().
                mapToLong(request -> request.getEndTime() - request.getStartTime())
                .sum();
        long avg = totalExec / requestCount;
        requestStat.setAvg(avg);
    }
}
