package com.rwj.springbootspark.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.rwj.springbootspark.model.SparkApplicationParam;
import com.rwj.springbootspark.service.ISparkSubmitService;
import com.rwj.springbootspark.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.spark.launcher.SparkAppHandle;
import org.apache.spark.launcher.SparkLauncher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * @author rwj
 * @create_time 2021/4/24
 * @description
 */
@Service
@Slf4j
public class SparkSubmitServiceImpl implements ISparkSubmitService {


    @Value("${driver.name:n3.com.rwj}")
    private String driverName;

    @Override
    public String submitApplication(SparkApplicationParam sparkAppParams, String... otherParams) throws IOException, InterruptedException {
        log.info("spark传入参数：{}", sparkAppParams.toString());
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Map<String, String> confParams = sparkAppParams.getOtherConfParams();
        SparkLauncher launcher = new SparkLauncher()
                .setAppResource(sparkAppParams.getJarPath())
                .setMainClass(sparkAppParams.getMainClass())
                .setMaster(sparkAppParams.getMaster())
                .setDeployMode(sparkAppParams.getDeployMode())
                .setConf("spark.driver.memory", sparkAppParams.getDriverMemory())
                .setConf("spark.executor.memory", sparkAppParams.getExecutorMemory())
                .setConf("spark.executor.cores", sparkAppParams.getExecutorCores());
        if(confParams != null && confParams.size() != 0) {
            log.info("开始设置spark job运行参数：{}", JSONObject.toJSONString(confParams));
            for(Map.Entry<String, String> conf : confParams.entrySet()) {
                log.info("{}:{}", conf.getKey(), conf.getValue());
                launcher.setConf(conf.getKey(), conf.getValue());
            }
        }
        if(otherParams.length != 0) {
            log.info("开始设置spark job参数：{}", otherParams);
            launcher.addAppArgs(otherParams);
        }
        log.info("参数设置完成，开始提交spark任务");
        SparkAppHandle handle = launcher.setVerbose(true).startApplication(new SparkAppHandle.Listener() {

            @Override
            public void stateChanged(SparkAppHandle sparkAppHandle) {
                if(sparkAppHandle.getState().isFinal()) {
                    countDownLatch.countDown();
                }
                log.info("stateChanged:{}", sparkAppHandle.getState().toString());
            }

            @Override
            public void infoChanged(SparkAppHandle sparkAppHandle) {
                log.info("infoChanged:{}", sparkAppHandle.getState().toString());
            }
        });
        log.info("The task is executing,please wait...");
        //线程等待任务结束
        countDownLatch.await();
        log.info("The Task is finished");
        //通过Spark原生的检测api获取执行结果信息，需要在spark-default.xml、spark-env.sh、yarn-site.xml进行相应的配置
        String restUrl;
        try{
            restUrl = "http://"+driverName+":18080/api/v1/applications/" + handle.getAppId();
            log.info("访问application运算结果，url：{}", restUrl);
            return HttpUtil.httpGet(restUrl, null);
        } catch (Exception e) {
            log.info("18080端口异常，请确保spark-history-server服务已经开启！");
            return  "{\"state\":\"error\",\"data\":\"history server is not start\"}";
        }
    }
}
