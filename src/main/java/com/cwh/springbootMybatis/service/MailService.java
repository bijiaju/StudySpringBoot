package com.cwh.springbootMybatis.service;

import java.io.File;

public interface MailService {

    void sendSimpleMail(String to,String suject,String content);//发文本文件

    void sendHTMLMail(String to,String suject,String content);//发html// 文件

    void sendAttachmentMail(String to,String suject,String content,String filePath);//发带附件的邮件

    void sendInLiResourceMail(String to,String suject,String content,String rscPath,String rscId);//发带附件的邮件

}
