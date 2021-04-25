package com.rwj.springbootspark.controller;

import com.rwj.springbootspark.service.ISparkSubmitService;
import com.rwj.springbootspark.vo.DataBaseExtractorVo;
import com.rwj.springbootspark.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author rwj
 * @create_time 2021/4/24
 * @description controller主要的职责就是接收页面的参数，将参数传递到service层
 */
@Slf4j
@RestController
@RequestMapping("extract")
public class SparkController {

    @Resource
    private ISparkSubmitService sparkSubmitService;

    /**
     * 调用service进行远程提交spark任务
     *
     * @param vo
     * @return
     */
    @PostMapping("database")
    public Result dbExtractAndLoad2Hdfs(@RequestBody DataBaseExtractorVo vo) {
        try {
            String res = sparkSubmitService.submitApplication(vo.getSparkAppParam(),
                    vo.getUrl(),
                    vo.getTableName(),
                    vo.getUserName(),
                    vo.getPassword(),
                    vo.getTargetFileType(),
                    vo.getTargetFilePath());
            return Result.success(res);
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
            log.error("执行出错：{}", e.getMessage());
            return Result.err(500, e.getMessage());
        }
    }

}
