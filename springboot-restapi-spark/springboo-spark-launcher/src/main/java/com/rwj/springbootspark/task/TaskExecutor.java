package com.rwj.springbootspark.task;

import com.rwj.springbootspark.api.ITask;
import com.rwj.springbootspark.model.SparkRequestParam;
import com.rwj.springbootspark.service.impl.SparkReqParamService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author rwj
 * @create_time 2021/5/5
 * @description
 */
@Slf4j
public class TaskExecutor implements ITask {

    private SparkRequestParam param;

    private String id;
    private SparkReqParamService service;
    public TaskExecutor(SparkReqParamService service, String id) {
        this.service = service;
        this.id = id;
    }

    @Override
    public void init() {
        log.info("获取执行sql参数 ...");
        param = service.getSparReuestParam(id);
        log.info("参数信息：{}",param.toString());
    }

    @Override
    public void handle() {
        log.info("提交执行...");
        service.submit(param);
    }

    @Override
    public void stop() {

    }
}
