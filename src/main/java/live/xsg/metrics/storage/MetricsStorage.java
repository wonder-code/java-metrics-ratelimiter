package live.xsg.metrics.storage;

import live.xsg.metrics.request.ApiRequest;

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

}
