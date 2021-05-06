package com.rwj.springbootspark.factory;

import com.rwj.springbootspark.api.ITask;
import com.rwj.springbootspark.exception.SparkException;
import com.rwj.springbootspark.service.impl.SparkReqParamService;
import com.rwj.springbootspark.task.TaskExecutor;

/**
 * @author rwj
 * @create_time 2021/5/6
 * @description
 */
public class TaskFactory {

    public static ITask createTask(SparkReqParamService service, String id) {
        switch (id) {
            case "1":
                return new TaskExecutor(service, id);
            default:
                throw new SparkException("不支持的任务类型。");
        }

    }
}
