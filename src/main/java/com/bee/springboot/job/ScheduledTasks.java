package com.bee.springboot.job;

import com.bee.springboot.service.MailService;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Configurable
@EnableScheduling
public class ScheduledTasks{

    public static void main(String[] args) throws Exception {
        try{
            FileWriter fw=new FileWriter("C:\\All_Files\\StudySpringBoot\\src\\main\\java\\com\\bee\\springboot\\util\\RedisUtil1.txt");
            BufferedWriter bw=new BufferedWriter(fw);

            ///读取文件的东西
            FileReader fr=new FileReader("C:\\All_Files\\StudySpringBoot\\src\\main\\java\\com\\bee\\springboot\\util\\RedisUtil.txt");
            BufferedReader br=new BufferedReader(fr);
            String str="";
            int i=1;
            while((str=br.readLine())!=null){

                if(!isNumeric(str)){
                    System.out.println(str);
                    bw.write(str+"\n");
                }
              /*  if(i%2==0){
                    System.out.println("删除偶数行");
                    //bufferedReader.
                }else{
                    bw.newLine();
                    bw.write(str);
                }*/
                //bw.write("lijin-"+str+"\r\n");
                i++;
            }
            bw.flush();
            fw.close();//关闭此流，但要先刷新它。在关闭该流之
            bw.close();
            fr.close();
            br.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 利用正则表达式判断字符串是否是数字
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }

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
        sendAttachmentMail();
    }*/   //

    @Scheduled(cron = "* * * * * * ")//00 00 6,12,15,20 * * ?
    public void reportCurrentByCron(){
        System.out.println ("执行时间： " + dateFormat ().format (new Date()));
       // sendAttachmentMail();

    }


    private SimpleDateFormat dateFormat(){
        return new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
    }


}
