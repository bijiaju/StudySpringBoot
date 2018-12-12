
package com.bee.springboot.service.impl;

import com.bee.springboot.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class MailServiceImpl implements MailService{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());//打印日志


    @Value("${spring.mail.username}")
    private String from;//注入全局配置参数
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendSimpleMail(String to, String suject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(suject);
        message.setText(content);
        message.setFrom(from);
        mailSender.send(message);
    }


/**
     * 发送html邮件
     * @param to
     * @param suject
     * @param content
     */

    @Override
    public void sendHTMLMail(String to, String suject, String content) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper= new MimeMessageHelper(message,true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(suject);
            helper.setText(content,true);
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


/**
     * 发送带附件的邮件
     * @param to
     * @param suject
     * @param content
     * @param filePath
     */

    @Override
    public void sendAttachmentMail(String to, String suject, String content, String filePath) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper= new MimeMessageHelper(message,true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(suject);
            helper.setText(content,true);

            //FileSystemResource file = new FileSystemResource(filePath);
            File file = new File(filePath);		//获取其file对象
            File[] fs = file.listFiles();	//遍历path下的文件和目录，放在File数组中
            for(File f:fs){					//遍历File[]数组
                if(!f.isDirectory()){
                    //若非目录(即文件)，则打印
                    System.out.println(f);
                    System.out.println(f.getName());
                    if(f.getName().equals("1.txt")){
                        helper.addAttachment(f.getName(),f);
                    }

                }

            }

            /*String fileName = file.getFilename();
            helper.addAttachment(fileName,file);
            helper.addAttachment(fileName+"_test",file);//发送多个附件的操纵
            helper.addAttachment(fileName+"_test",file);//发送多个附件的操纵
            helper.addAttachment(fileName+"_test",file);//发送多个附件的操纵*/
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


/***
     * 发送带图片的邮件
     * @param to
     * @param suject
     * @param content
     * @param rscPath
     * @param rscId
     */

    @Override
    public void sendInLiResourceMail(String to, String suject, String content, String rscPath, String rscId) {
        logger.info("发送静态邮件开始：{},{},{},{},{}",to,suject,content,rscPath,rscId);
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper= new MimeMessageHelper(message,true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(suject);
            helper.setText(content,true);

            FileSystemResource res = new FileSystemResource(new File(rscPath));
            helper.addInline(rscId,res);
            mailSender.send(message);
            logger.info("发送邮件成功");
        } catch (Exception e) {
            logger.error("发送邮件失败：",e);
            e.printStackTrace();
        }
    }


}

