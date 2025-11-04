package com.thegentle.oldhealth.pojo;

//后端统一结果

import lombok.Data;

@Data
public class Result {
    private Integer code; //编码：1成功，0为失败
    private String msg; //错误信息
    private Object data; //数据
    public static Result success(Object data) {
        Result result = success();
        result.data=data;
        return result;
    }
    public static Result success() {
        Result result = new Result();
        result.setCode(1);
        result.msg="操作成功";
        return result;
    }
    public static Result error(String message) {
        Result result = new Result();
        result.code=0;
//        result.data=message;
        result.msg=message;
        return result;
    }

}
