package io.renren.common.utils.result;

/**
 * @author zhaoxiubin
 * 创建日期:2018/7/4
 * 返回值为多条数据的类
 * @version V1.0
 */
public class MultipleDataResponse<T> extends SingleDataResponse {
    /**
     * 数据总数
     */
    private Long total;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public MultipleDataResponse(){
    }

    public MultipleDataResponse(Integer code, String msg, T data, Long total){
        this.setCode(code);
        this.setMsg(msg);
        this.setData(data);
        this.total = total;
    }

    public MultipleDataResponse(BaseResponse baseResponse, T data, Long total){
        this.setCode(baseResponse.getCode());
        this.setMsg(baseResponse.getMsg());
        this.setData(data);
        this.total = total;
    }

    /**
     * 成功返回值的静态方法
     * @param data
     * @param total
     * @return
     */
    public static MultipleDataResponse success(Object data, Long total){
        return new MultipleDataResponse(BaseResponse.SUCCESS, data, total);
    }
}
