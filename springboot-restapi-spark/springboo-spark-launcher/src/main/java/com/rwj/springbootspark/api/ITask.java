package com.rwj.springbootspark.api;

/**
 * @author rwj
 * @create_time 2021/5/6
 * @description
 */
public interface ITask {
    /**
     * 对该任务的初始化
     */
    void init();

    /**
     * 执行任务逻辑
     */
    void handle();

    /**
     * 停止任务
     */
    void stop();
}
