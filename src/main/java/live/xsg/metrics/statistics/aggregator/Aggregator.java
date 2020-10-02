package live.xsg.metrics.statistics.aggregator;

import live.xsg.metrics.request.ApiRequest;
import live.xsg.metrics.statistics.RequestStat;

import java.util.List;

/**
 * 对请求数据执行聚合统计操作
 * Created by xsg on 2020/10/2.
 */
public interface Aggregator {

    /**
     * 执行接口请求的统计，将统计结果存入到 RequestStat 中
     * @param requestStat 存储接口的统计结果
     * @param apiRequests 接口的请求数据信息
     */
    void aggregate(RequestStat requestStat, List<ApiRequest> apiRequests);
}
