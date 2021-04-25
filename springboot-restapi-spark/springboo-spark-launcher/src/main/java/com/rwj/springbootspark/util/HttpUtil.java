package com.rwj.springbootspark.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author rwj
 * @create_time 2021/4/24
 * @description
 */
public class HttpUtil {
    public static String httpGet(String urlStr, List<String> urlParam) throws IOException, InterruptedException {
        //实例一个url资源
        URL url = new URL(urlStr);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        int i = 0;
        while(connection != null || connection.getResponseCode() != 200) {
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Charset", "UTF-8");
            connection.setRequestProperty("Content-type", "application/json");
            //连接超时      单位：毫秒
            connection.setConnectTimeout(10000);
            //读取超时      单位：毫秒
            connection.setReadTimeout(10000);
            i++;
            if(i == 50) {
                break;
            }
            Thread.sleep(500);
        }
        //将返回值存入到String中
        BufferedReader brd = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        String line;
        while((line = brd.readLine()) != null) {
            sb.append(line);
        }
        brd.close();
        connection.disconnect();
        return sb.toString();
    }
}