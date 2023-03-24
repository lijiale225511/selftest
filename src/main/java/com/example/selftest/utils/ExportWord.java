package com.example.selftest.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class ExportWord {

    @Value("${spring.upload.folder}")
    private String uploadFolder;

    public File createTemplate() throws Exception {

        String wordName = "demo.ftl";
        // 用户的数据封装
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("userCode", "3102021243");
        dataMap.put("userName", "张三丰");
        dataMap.put("userPhone", "13305748888");
        dataMap.put("userEmail", "1234567890@qq.com");
        // 生成FTL模板文档
        Configuration configuration = new Configuration();
        configuration.setDefaultEncoding("UTF-8");
        configuration.setClassForTemplateLoading(this.getClass(), "/templates");
        Template t = configuration.getTemplate(wordName, "UTF-8");
        // 生成Word文档
        DateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
        // 判断路径是否存在，不存在则创建
        File file = new File(uploadFolder);
        if (!file.exists()) {
            file.mkdirs();
        }
        String name = uploadFolder + df.format(new Date()) + ".doc";
        File outFile = new File(name);
        Writer w = new OutputStreamWriter(new FileOutputStream(outFile), "utf-8");
        t.process(dataMap, w);
        w.close();
        return outFile;
    }
}
