package io.renren.common.utils.result;

import com.alibaba.fastjson.JSON;

/**
 * @author zhaoxiubin
 * 创建日期:2018/7/4
 * 返回值为单条数据的类
 * @version V1.0
 */
public class SingleDataResponse<T> extends BaseResponse {
    /**
     * 数据内容
     */
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public SingleDataResponse(){
    }

    public SingleDataResponse(Integer code, String msg, T data){
        this.setCode(code);
        this.setMsg(msg);
        this.data = data;
    }

    public SingleDataResponse(BaseResponse baseResponse, T data){
        this.setCode(baseResponse.getCode());
        this.setMsg(baseResponse.getMsg());
        this.data = data;
    }
    @Override
    public String toString() {
        String jsonString = JSON.toJSONString(this);
        System.out.println(jsonString);
        return jsonString;
    }
    /**
     * 成功返回值的静态方法
     * @param data
     * @return
     */
    public static SingleDataResponse success(Object data){
        return new SingleDataResponse(BaseResponse.SUCCESS, data);
    }
}
