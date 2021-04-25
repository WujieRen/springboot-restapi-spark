package com.rwj.springbootspark.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;

/**
 * @author rwj
 * @create_time 2021/4/24
 * @description Spark任务实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SparkApplicationParam {

    /** Spark任务的主类 */
    private String mainClass;

    /** jar包路径 */
    private String jarPath;
    @Value("${spark.master:yarn}")
    private String master;
    @Value("${spark.deploy.mode:cluster}")
    private String deployMode;
    @Value("${spark.driver.memory:1g}")
    private String driverMemory;
    @Value("${spark.executor.memory:1g}")
    private String executorMemory;
    @Value("${spark.executor.cores:1}")
    private String executorCores;

    /** 其他配置类，传递给spark job的参数 */
    private Map<String, String> otherConfParams;

    /** 调用该方法可获取是spark任务的设置参数 */
    public SparkApplicationParam getSparkAppParam() {
        return new SparkApplicationParam(mainClass, jarPath, master, deployMode, driverMemory, executorMemory, executorCores, otherConfParams);
    }

}
