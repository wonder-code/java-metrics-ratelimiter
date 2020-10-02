package live.xsg.metrics.statistics.aggregator;

import live.xsg.metrics.request.ApiRequest;
import live.xsg.metrics.statistics.RequestStat;

import java.util.List;

/**
 * 接口请求次数统计
 * Created by xsg on 2020/10/2.
 */
public class CountAggregator implements Aggregator {
    @Override
    public void aggregate(RequestStat requestStat, List<ApiRequest> apiRequests) {
        if (apiRequests == null) return;

        requestStat.setCount(apiRequests.size());
    }
}
