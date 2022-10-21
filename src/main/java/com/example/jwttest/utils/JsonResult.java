package com.example.jwttest.utils;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

//统一返回结果的类
@Data
public class JsonResult {

    //@ApiModelProperty(value = "是否成功")
    private Boolean success;

    //@ApiModelProperty(value = "返回码")
    private Integer code;

    //@ApiModelProperty(value = "返回消息")
    private String message;

    //@ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<String, Object>();

    //把构造方法私有
    private JsonResult() {}

    //成功静态方法
    public static JsonResult ok() {
        JsonResult r = new JsonResult();
        r.setSuccess(true);
        r.setCode(200);
        r.setMessage("成功");
        return r;
    }

    //失败静态方法
    public static JsonResult error() {
        JsonResult r = new JsonResult();
        r.setSuccess(false);
        r.setCode(201);
        r.setMessage("失败");
        return r;
    }

    public JsonResult success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public JsonResult message(String message){
        this.setMessage(message);
        return this;
    }

    public JsonResult code(Integer code){
        this.setCode(code);
        return this;
    }

    public JsonResult data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public JsonResult data(Map<String, Object> map){
        this.setData(map);
        return this;
    }
}
