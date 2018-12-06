package com.test.service;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

/**
 * created at 2018/8/21
 * created by bee
 */
public class MyGenerator {

    private final static String PATH ="C:/All_Files/git/demo";

    public static void main(String[] args) {
      /*  // 自定义需要填充的字段
        List<TableFill> tableFillList = new ArrayList<TableFill>();
        //如 每张表都有一个创建时间、修改时间
        //而且这基本上就是通用的了，新增时，创建时间和修改时间同时修改
        //修改时，修改时间会修改，
        //虽然像Mysql数据库有自动更新几只，但像ORACLE的数据库就没有了，
        //使用公共字段填充功能，就可以实现，自动按场景更新了。
        //如下是配置
        TableFill createField = new TableFill("gmt_create", FieldFill.INSERT);
        TableFill modifiedField = new TableFill("gmt_modified", FieldFill.INSERT_UPDATE);
        tableFillList.add(createField);
        tableFillList.add(modifiedField);*/

        AutoGenerator mpg = new AutoGenerator();

        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        // set null to turn off the default behavior
        //TODO
        TemplateConfig tc = new TemplateConfig();
        tc.setXml(null);
//        tc.setController(null);
//        tc.setService(null);
//        tc.setServiceImpl(null);
        mpg.setTemplate(tc);

        // 全局配置
        final GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(PATH + "/src/main/java");
        gc.setFileOverride(true);//是否覆盖文件
        gc.setActiveRecord(false);// 不需要ActiveRecord特性的请改为false
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(true);// XML columList
        gc.setSwagger2(true);
        gc.setDateType(DateType.ONLY_DATE);
        gc.setOpen(false);
        gc.setAuthor("bee");


        // define filename
        gc.setMapperName("%sRepository");
//        gc.setXmlName("%s");
        //gc.setXmlName("%sMapper").setMapperName("%sDao");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");
        mpg.setGlobalConfig(gc);

        //datasource configuration
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        dsc.setUrl("jdbc:mysql://localhost:3306/mytest?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true");
        mpg.setDataSource(dsc);

        StrategyConfig strategy = new StrategyConfig();
        strategy.setRestControllerStyle(true);
        //the controller、service、mapper names will remove the prefix
        strategy.setTablePrefix("");
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略

        //TODO
        strategy.setInclude(
            //    "file_store",
               //  "common_dict"
//                ,"batch_send_task","telephone_store"
//                ,"send_task_store"
        );

        mpg.setStrategy(strategy);

        // package configuration
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.example.demo");
        pc.setEntity("entity");
        pc.setMapper("repository");
        pc.setXml("");

        mpg.setPackageInfo(pc);

        mpg.execute();

    }

}
