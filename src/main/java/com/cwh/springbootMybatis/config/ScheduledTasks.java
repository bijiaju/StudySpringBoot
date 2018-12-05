package com.cwh.springbootMybatis.config;

import com.cwh.springbootMybatis.service.MailService;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Configurable
@EnableScheduling
public class ScheduledTasks{

    @Resource
    MailService mailService;

    @Resource
    TemplateEngine templateEngine;//添加模板引擎

   /* @Scheduled(fixedRate = 1000 * 30)//每30秒发一次
    public void reportCurrentTime(){
        System.out.println ("Test " + dateFormat ().format (new Date())+"-----30秒跑一次");
    }*/

    //每1分钟执行一次
   /* @Scheduled(cron = "0 0 6,12,15,20  * * ? ")//00 00 6,12,15,20 * * ?
    public void reportCurrentByCron(){
        System.out.println ("发邮件时间： " + dateFormat ().format (new Date ()));
        //sendAttachmentMail();
    }*/

    private SimpleDateFormat dateFormat(){
        return new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
    }


}
