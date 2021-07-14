package com.natian.doamin;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class R {


    @ApiModelProperty(value="是否成功")
    private  Boolean  success;
    @ApiModelProperty(value="返回状态码")
    private Integer code;
    @ApiModelProperty(value="返回消息")
    private   String message;
    @ApiModelProperty(value="返回数据")
    private Map<String, Object>  data =new  HashMap<String,Object>();

    public static  R  ok(String message){
        R  r=new R();
        r.setSuccess(true);
        r.setCode(200);
        r.setMessage(message);
        return  r;
    }

    public static   R  error(String message){
        R  r=new R();
        r.setSuccess(false);
        r.setCode(500);
        r.setMessage(message);
        return  r;
    }


    public   R  data(String key,Object value){
        this.data.put(key,value);
        return this;
    }

    public  R   data(Map<String, Object> map){
        this.setData(map);
        return this;
    }




}
