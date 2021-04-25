package com.rwj.springbootspark.vo;

import com.rwj.springbootspark.model.SparkApplicationParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author rwj
 * @create_time 2021/4/24
 * @description 每个任务执行的时候都必须指定运行参数，所以要继承SparkApplicationParam对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataBaseExtractorVo extends SparkApplicationParam {

    /** 数据库连接地址 */
    private String url;
    /** 数据库连接账号 */
    private String userName;
    /** 数据库连接密码 */
    private String password;
    /** 指定的表名 */
    private String tableName;
    /** 目标文件类型 */
    private String targetFileType;
    /** 目标文件保存路径 */
    private String targetFilePath;


}
