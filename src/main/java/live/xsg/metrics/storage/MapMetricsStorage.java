package live.xsg.metrics.storage;

import live.xsg.metrics.request.ApiRequest;
import live.xsg.metrics.statistics.RequestStat;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

/**
 * 基于 map 实现的本地存储
 * Created by xsg on 2020/10/2.
 */
public class MapMetricsStorage implements MetricsStorage {

    private static final Queue<ApiRequest> apiRequestQueue = new ConcurrentLinkedQueue<>();

    @Override
    public void saveApiRequest(ApiRequest apiRequest) {
        apiRequestQueue.add(apiRequest);
    }

    @Override
    public List<ApiRequest> getApiRequest(long start, long end) {
        return apiRequestQueue.stream()
                .filter(request -> request.getStartTime() >= start && request.getStartTime() <= end)
                .collect(Collectors.toList());
    }

    @Override
    public void saveRequestStat(List<RequestStat> requestStats) {
        if (requestStats == null) return;

        for (RequestStat requestStat : requestStats) {
            String print = String.format("api: %s, count: %d", requestStat.getApi(), requestStat.getCount());
            System.out.println(print);
        }
    }
}
