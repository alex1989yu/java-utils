package com.yuke.springboot.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务
 */
@Component
@Slf4j
@EnableScheduling
public class SimpleTask {
    //每天凌晨1点执行一次0 0 1 * * ?
    @Scheduled(cron = "0 0 1 * * ?")
    public void execute(){

    }
}
