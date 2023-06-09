package com.didi.servicedriveruser.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * 自动生成代码工具类
 */
public class MysqlGenerator {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/service-driver-user?characterEncoding=utf-8","root","root").globalConfig(builder -> {
            builder.author("shawn").fileOverride().outputDir("D:\\IdeaProjects\\didi2023\\service-driver-user\\src\\main\\java");
        }).packageConfig(builder -> {
            builder.parent("com.didi.servicedriveruser").pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D:\\IdeaProjects\\didi2023\\service-driver-user\\src\\main\\java\\com\\didi\\servicedriveruser\\mapper"));
        }).strategyConfig(builder -> {
            builder.addInclude("driver_user_work_status").entityBuilder().enableLombok();
        }).templateEngine(new FreemarkerTemplateEngine()).execute();
    }
}
