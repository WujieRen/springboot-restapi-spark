package com.rwj.springbootspark.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.Map;


/**
 * @author rwj
 * @create_time 2021/4/26
 * @description
 */
@Data
@ToString
public class SparkRequestParam implements Serializable {

    private String taskId;
    private String dbName;
    private String tableName;
    private String hql;
    private Map<String, String> options;

}
