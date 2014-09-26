package com.vogella;

import java.io.File;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

public class Test {
    public static void main(String[] args) throws Exception {
        /* 在整个应用的生命周期中，这个工作你应该只做一次。 */
        /* 创建和调整配置。 */
        Configuration cfg = new Configuration();
        System.out.println(new File(Test.class.getResource("/template").getFile()));
        cfg.setDirectoryForTemplateLoading(
                new File(Test.class.getResource("/template").getFile()));
        cfg.setObjectWrapper(new DefaultObjectWrapper());
        /* 在整个应用的生命周期中，这个工作你可以执行多次 */
        /* 获取或创建模板*/
        Template temp = cfg.getTemplate("test.ftl");
        /* 创建数据模型 */
        Map root = new HashMap();
        root.put("user", "Big Joe");
        Map latest = new HashMap();
        root.put("latestProduct", latest);
        latest.put("url", "products/greenmouse.html");
        latest.put("name", "green mouse");
        /* 将模板和数据模型合并 */
        Writer out = new OutputStreamWriter(System.out);
        temp.process(root, out);
        out.flush();
    }
}