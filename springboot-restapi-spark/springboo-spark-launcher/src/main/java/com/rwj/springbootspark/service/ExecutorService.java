package com.rwj.springbootspark.service;

import com.rwj.springbootspark.model.SparkJobResponse;
import com.rwj.springbootspark.service.impl.SparkReqParamService;
import com.rwj.springbootspark.task.TaskExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author rwj
 * @create_time 2021/5/6
 * @description
 */
@Slf4j
@Service
public class ExecutorService {

    @Resource
    private SparkReqParamService paramService;

    public void submit(String id) {
        TaskExecutor executor = new TaskExecutor(paramService, id);
        executor.init();
        executor.handle();
    }

}
