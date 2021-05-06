package com.rwj.springbootspark.controller;

import com.rwj.springbootspark.model.SparkJobResponse;
import com.rwj.springbootspark.model.SparkRequestParam;
import com.rwj.springbootspark.service.ExecutorService;
import com.rwj.springbootspark.service.impl.SparkReqParamService;
import com.rwj.springbootspark.task.TaskExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author rwj
 * @create_time 2021/5/5
 * @description
 */
@Slf4j
@RestController
@RequestMapping("spark")
public class ParamController {

    @Resource
    private ExecutorService executorService;

    @PostMapping("execute/{id}")
    public void execute(@PathVariable String id) {
        log.info("执行任务ID：{}", id);
        executorService.submit(id);
    }

//    @GetMapping("test/{id}")
//    public SparkJobResponse<SparkRequestParam> test(@PathVariable String id) {
//        return SparkJobResponse.success(service.getSparkReqParmDao(id));
//    }
}
