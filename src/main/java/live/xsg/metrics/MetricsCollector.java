package live.xsg.metrics;

import live.xsg.metrics.request.ApiRequest;
import live.xsg.metrics.storage.MetricsStorage;

/**
 * 接口数据采集器
 * Created by xsg on 2020/9/29.
 */
public class MetricsCollector {

    //指标数据的存储
    private MetricsStorage metricsStorage;

    public MetricsCollector(MetricsStorage metricsStorage) {
        this.metricsStorage = metricsStorage;
    }

    /**
     * 接口数据采集
     * @param apiRequest 接口的数据
     */
    public void collector(ApiRequest apiRequest) {
        this.metricsStorage.saveApiRequest(apiRequest);
    }
}
