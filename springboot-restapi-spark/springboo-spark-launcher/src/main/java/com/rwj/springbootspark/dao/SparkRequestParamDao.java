package com.rwj.springbootspark.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rwj.springbootspark.model.SparkRequestParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author rwj
 * @create_time 2021/5/5
 * @description
 */
@Mapper
public interface SparkRequestParamDao extends BaseMapper<SparkRequestParam> {
    SparkRequestParam getAnlysExecuteParam(@Param("id") String id);
}
