package com.rwj.springbootspark.service;

import com.rwj.springbootspark.model.SparkApplicationParam;

import java.io.IOException;

/**
 * @author rwj
 * @create_time 2021/4/24
 * @description 每个Spark任务都需要指定运行参数，但是任务内部所需的参数不太一样；
 *              所以第一个参数为通用的参数对象，第二个参数为可变参数，根据不同的任务来传值。
 */
public interface ISparkSubmitService {
    String submitApplication(SparkApplicationParam sparkAppParams, String... otherParams) throws IOException, InterruptedException;
}
