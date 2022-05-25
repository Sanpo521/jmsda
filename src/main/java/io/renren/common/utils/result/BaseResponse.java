package io.renren.common.utils.result;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * @author zhaoxiubin
 * 创建日期:2018/7/3
 * 返回值基础类
 * @version V1.0
 */
public class BaseResponse  implements Serializable {

    public static final BaseResponse SUCCESS = new BaseResponse(0, "success");
    /**
     * 返回值代码：0为成功，其它为失败
     */
    private Integer code;
    /**
     * 返回值内容：如果错误描述
     */
    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public BaseResponse(){}

    public BaseResponse(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String toString() {
        String jsonString = JSON.toJSONString(this);
        return jsonString;
    }

    /**
     * 失败返回值的静态方法
     * @param code
     * @param msg
     * @return
     */
    public static BaseResponse failure(Integer code, String msg){
        return new BaseResponse(code, msg);
    }
}
