package live.xsg.metrics.storage;

import live.xsg.metrics.request.ApiRequest;
import live.xsg.metrics.statistics.RequestStat;

import java.util.List;

/**
 * 负责api指标统计数据的存储与提取
 * Created by xsg on 2020/9/29.
 */
public interface MetricsStorage {

    /**
     * 保存 api 请求的信息
     * @param apiRequest ApiRequest
     */
    void saveApiRequest(ApiRequest apiRequest);

    /**
     * 查询 api 请求开始时间在 start 和 end 范围内的请求
     * @param start 请求开始时间
     * @param end 请求结束时间
     * @return 返回在时间范围内的请求，如果无数据返回空集合
     */
    List<ApiRequest> getApiRequest(long start, long end);

    /**
     * 对接口的聚合统计后的数据进行保存
     * @param requestStats 接口聚合统计后的信息
     */
    void saveRequestStat(List<RequestStat> requestStats);
}
