package live.xsg.metrics.exception;

/**
 * 接口请求指标统计异常
 * Created by xsg on 2020/10/2.
 */
public class MetricsException extends Exception {

    public MetricsException(String message) {
        super(message);
    }
}
