package com.mga.financing.base.bean;

/**
 * Created by mga on 2017/11/21 10:55.
 */

public class BaseResponse<T> {
    private String code;

    private String description;

    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", data=" + data +
                '}';
    }
}
