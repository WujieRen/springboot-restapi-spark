package com.rwj.sparkjob;

import lombok.extern.slf4j.Slf4j;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

/**
 * @author rwj
 * @create_time 2021/4/24
 * @description 将数据库中的表保存到HDFS上
 */
@Slf4j
public class DbTableEtl {
    public static void main(String[] args) {
        SparkSession spark = SparkSession.builder()
                .appName(DbTableEtl.class.getSimpleName())
                .getOrCreate();
//        String url = "jdbc:mysql://localhost:3306/splc2020";
        String url = args[0];
//        String dbTable = "payment";
        String dbTable = args[1];
//        String user = "root";
        String user = args[2];
//        String password = "123456";
        String password = args[3];
        String targetFileType = args[4];
        String targetFilePath = args[5];
        Dataset<Row> dbData = spark.read()
                .format("jdbc")
//                .option("driver", "com.mysql.cj.jdbc.Driver")
                .option("driver", "com.mysql.jdbc.Driver")
                .option("url", url)
                .option("dbTable", dbTable)
                .option("user", user)
                .option("password", password)
                .load();
        log.info("展示部分样例数据，即将开始导入到hdfs");
        dbData.show(20, false);
        dbData.write().mode("overwrite").format(targetFileType).save(targetFilePath);
//        dbData.write().mode("overwrite").format("json").save("json.json");
    }
}