package com.test.service;

import com.cwh.springbootMybatis.Application;
import com.cwh.springbootMybatis.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)//spring对junit的支持
@SpringBootTest(classes = Application.class)//设置启动类
public class MailServiceTest {
    @Resource
    MailService mailService;

    @Resource
    TemplateEngine templateEngine;//添加模板引擎

    @Test
    public void sendSimpleMail(){
        mailService.sendSimpleMail("byq_work@163.com","这是邮件主题","这是文本邮件内容");
    }

    @Test
    public void sendHTMLMail(){
        StringBuilder sb = new StringBuilder();
        sb.append("<html>\n");
        sb.append("<body>\n");
        sb.append("<h3> 这是一个html邮件");
        sb.append("</h3>\n");
        sb.append("</body");
        sb.append("</html");
        mailService.sendHTMLMail("byq_work@163.com","这是邮件主题",sb.toString());
    }

    @Test
    public void sendAttachmentMail(){
        String filePath = "C:\\Users\\bee\\Downloads";
        StringBuilder sb = new StringBuilder();
        sb.append("<html>\n");
        sb.append("<body>\n");
        sb.append("<h3> 这是一个html邮件");
        sb.append("</h3>\n");
        sb.append("</body");
        sb.append("</html");
        mailService.sendAttachmentMail("byq_work@163.com","这是邮件主题",sb.toString(),filePath);

    }

    /**
     * 图邮件就是html加入入img
     */
    @Test
    public void sendInLiResourceMail(){
        String imgPath = "C:\\Users\\bee\\Downloads\\1.png";
        String rscId = "test1";//随便起的

        StringBuilder sb = new StringBuilder();
        sb.append("<html>\n");
        sb.append("<body>\n");
        sb.append("<h3> 这是一个pic邮件");
        sb.append("</h3>\n");
        sb.append("<img src=\'cid:"+rscId+"\'></img>");
        sb.append("</h3>\n");
        sb.append("</body");
        sb.append("</html");
        mailService.sendInLiResourceMail("byq_work@163.com","这是邮件主题",sb.toString(),imgPath,rscId);
    }

    /**
     * 发送模板邮件
     */
    @Test
    public void sendTemplateMail(){
         Context context  = new Context();
         context.setVariable("postid","9016934");
         String emailContent = templateEngine.process("mailTemplate",context);//这样把 emailTemplate.html  读出一个html的文本
         mailService.sendHTMLMail("byq_work@163.com","这是邮件主题",emailContent);
    }
}

