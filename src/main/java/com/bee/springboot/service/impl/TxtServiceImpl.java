package com.bee.springboot.service.impl;

import com.bee.springboot.service.TxtSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;

/**
 * 执行大批量插入文本
 */
@Service
public class TxtServiceImpl implements TxtSevice {

    public  static int runningCount = 0; //正在执行的个数
    public  static int sucCount = 0; //成功个数
    public static int failCount = 0; //失败个数3

    @Value("${srcfile}")
    public String srcFile;

    @Value("${desfile}")
    public String desfile;

    @Value("${perprint}")
    public int perprint;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    //@PostConstruct//启动后自己执行!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!t
    public  void insertTxt() throws IOException {

            String name = srcFile;
            File file = new File(name);
            InputStreamReader inputReader = new InputStreamReader(new FileInputStream(file));
            BufferedReader bf = new BufferedReader(inputReader);
            // 按行读取字符串
            String str = "";
            while ((str = bf.readLine()) != null) {
                //str = new String(str.getBytes("gb2312"), "UTF-8");//将读取出来的GBK格式的代码转换成UTF-8
                try{
                    jdbcTemplate.update(str);
                    sucCount++;
                    printRuningCount();
                }catch (Exception e) {
                    writeFile(str);
                    failCount++;
                    printRuningCount();
                }
                //System.out.println(str);
            }
            bf.close();
            inputReader.close();
            System.out.println("执行总条数："+(sucCount+failCount)+",  成功"+sucCount+"次  ,"+"失败"+failCount+"次  ,");

    }


    /**
     * 每xxx打印一次
     */
    private void printRuningCount(){
        runningCount++;
        if(runningCount%perprint==0){//每1000打印一次
            System.out.println("当前已经执行 "+runningCount+" 条");
        }
    }

    /**
     * 失败写文件
     * @param str
     * @throws IOException
     */
    public void writeFile(String str) throws IOException {
        File file = new File(desfile);
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw =null;
        try{
            if (!file.exists()) {
                boolean hasFile = file.createNewFile();
                if(hasFile){
                    //System.out.println("文件不存在，创建新文件");
                }
                fos = new FileOutputStream(file);
            } else {
                //System.out.println("文件已经存在，正在进行追加数据");
                fos = new FileOutputStream(file, true);
            }
            osw = new OutputStreamWriter(fos, "UTF-8");
            bw = new BufferedWriter(osw);
            bw.write(str);
            bw.newLine();//换行
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            bw.close();
            osw.close();
            fos.close();
        }
    }
}
