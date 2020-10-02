package live.xsg.metrics.request;

/**
 * 封装接口的请求信息
 * Created by xsg on 2020/9/29.
 */
public class ApiRequest {
    //接口的请求路径
    private String api;
    //接口请求的开始时间
    private long startTime;
    //接口请求的结束时间
    private long endTime;

    public ApiRequest(String api, long startTime, long endTime) {
        this.api = api;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
}
