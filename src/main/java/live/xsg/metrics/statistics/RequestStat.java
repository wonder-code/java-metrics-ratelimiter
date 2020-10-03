package live.xsg.metrics.statistics;

/**
 * 请求数据的统计结果
 * Created by xsg on 2020/10/2.
 */
public class RequestStat {
    //请求的 api
    private String api;
    //请求次数
    private int count;
    //平均响应时间
    private long avg;

    public RequestStat(String api) {
        this.api = api;
    }

    public String getApi() {
        return api;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getAvg() {
        return avg;
    }

    public void setAvg(long avg) {
        this.avg = avg;
    }

    @Override
    public String toString() {
        return "RequestStat{" +
                "api='" + api + '\'' +
                ", count=" + count +
                ", avgResp=" + avg +
                '}';
    }
}
