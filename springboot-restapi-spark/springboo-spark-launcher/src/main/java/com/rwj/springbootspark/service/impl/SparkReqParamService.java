package com.rwj.springbootspark.service.impl;

import com.rwj.springbootspark.constant.IConstant;
import com.rwj.springbootspark.dao.SparkRequestParamDao;
import com.rwj.springbootspark.model.SparkJobResponse;
import com.rwj.springbootspark.model.SparkRequestParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author zhangqi
 **/
@Service
public class SparkReqParamService {

    @Resource
    private SparkRequestParamDao sparkReqParmDao;
    @Resource
    private Environment environment;
    @Resource
    private RestTemplate restTemplate;

    public SparkRequestParam getSparReuestParam(String id) {
        return sparkReqParmDao.getAnlysExecuteParam(id);
    }

    public SparkJobResponse submit(SparkRequestParam param) {
        return restTemplate.postForObject(environment.getProperty(IConstant.SPARK_EXECUTE_URL), param, SparkJobResponse.class);
    }

}
