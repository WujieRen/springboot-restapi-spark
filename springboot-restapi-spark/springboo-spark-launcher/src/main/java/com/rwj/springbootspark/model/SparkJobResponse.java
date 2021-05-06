package com.rwj.springbootspark.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author yangyao
 */
@Data
@NoArgsConstructor
public class SparkJobResponse<T> implements Serializable {

    /**
     * 返回状态，0:失败 1:成功
     */
    private Integer code;

    /**
     * 返回消息
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

    private static final String SUCCESS_MSG = "SUCCESS";

    public static <T> SparkJobResponse<T> success() {
        SparkJobResponse<T> response = new SparkJobResponse<T>();
        response.setCode(1);
        response.setMessage(SUCCESS_MSG);
        return response;
    }

    public static <T> SparkJobResponse<T> success(String message) {
        SparkJobResponse<T> response = new SparkJobResponse<T>();
        response.setCode(1);
        response.setMessage(message);
        return response;
    }

    public static <T> SparkJobResponse<T> success(T data) {
        SparkJobResponse<T> response = new SparkJobResponse<T>();
        response.setCode(1);
        response.setMessage(SUCCESS_MSG);
        response.setData(data);
        return response;
    }

    public static <T> SparkJobResponse<T> success(String message, T data) {
        SparkJobResponse<T> response = new SparkJobResponse<T>();
        response.setCode(1);
        response.setMessage(message);
        response.setData(data);
        return response;
    }

    public static <T> SparkJobResponse<T> error(String message) {
        SparkJobResponse<T> response = new SparkJobResponse<T>();
        response.setCode(0);
        response.setMessage(message);
        return response;
    }

    public static <T> SparkJobResponse<T> error(int code, String message) {
        SparkJobResponse<T> response = new SparkJobResponse<T>();
        response.setCode(code);
        response.setMessage(message);
        return response;
    }

    public static final int REQUEST_SUCCESS = 200;
    public static final int REQUEST_ERROR = 404;
    public static final int REQUEST_TIMEOUT = 408;
    public static final int NOT_LOGIN = -1;
}