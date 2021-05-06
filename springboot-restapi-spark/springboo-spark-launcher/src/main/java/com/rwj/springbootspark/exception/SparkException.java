package com.rwj.springbootspark.exception;

/**
 * @author rwj
 * @create_time 2021/5/6
 * @description
 */
public class SparkException extends RuntimeException{

    public SparkException() {super();}
    public SparkException(String message) { super(message);}
    public SparkException(String message, Throwable cause) { super(message, cause);}
    public SparkException(Throwable cause) {super(cause);}
}