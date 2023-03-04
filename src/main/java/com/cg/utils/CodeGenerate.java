package com.cg.utils;

import com.cg.utils.entity.*;
import com.cg.utils.freemarker.FreeMarkers;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

/**
 * details: MySql数据库类型,代码生成器
 */
public class CodeGenerate {

    public static void foundCode(Project project, String table, String moduleName, String author, Map<String, Object> parameterMap, String path) throws Exception {
        Logger logger = Logger.getInstance(CodeGenerate.class);
        //获得数据库配置
        PropertiesUtil propertiesUtil = PropertiesUtil.getMySqlProperties(path);
        //数据库
        String database = propertiesUtil.getProperty("database");
        //包地址
        String packageName = propertiesUtil.getProperty("packageName") + "." + moduleName;
        String resultName = propertiesUtil.getProperty("resultName");
        String resultPackage = propertiesUtil.getProperty("resultPackage");
        String returnCodeName = propertiesUtil.getProperty("returnCodeName");
        String successCodeName = propertiesUtil.getProperty("successCodeName");
        String returnCodePackage = propertiesUtil.getProperty("returnCodePackage");
        try {
            String table_name = "";
            //链接数据库操作
            Connection connection = CodeGenerateUtils.getDblink(path);
            //返回链接的Statement
            Statement stateMent_table = connection.createStatement();
            //查询该数据库所有的表名
            ResultSet tables = stateMent_table.executeQuery("show tables");
            logger.debug("已经获取表名");
            //循环
            while (tables.next()) {
                //查询出列名为Tables_in_good_loan_dev,所有数据库表名
                table_name = tables.getString("Tables_in_" + database);
                //判断执行那个表的,生成代码操作
                if (!table_name.equals(table)) {
                    continue;
                }
                logger.debug("开始生成代码");
                String cn = table_name;

                //获得,类名
                String className = CodeGenerateUtils.toUpper(cn.substring(0));

                //获得，表名注释
                ResultSet table_comment = stateMent_table.executeQuery("select TABLE_COMMENT from information_schema.tables where table_schema='" + database + "' and table_name='" + table + "'");
                String functionName = "";
                while (table_comment.next()) {
                    functionName = table_comment.getString("TABLE_COMMENT");
                }
                table_comment.close();
                //指定项目地址
                String address = path + "/src/main/java/" + packageName;
                //模板路径
//                String freeMarkersName = path + "src/main/resources/templates/";
                // 代码模板配置
                Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
                cfg.setClassLoaderForTemplateLoading(CodeGenerate.class.getClassLoader(),"/templates/mp/");
                // 定义模板变量
                Map<String, Object> model = Maps.newHashMap();
                model.put("resultName", resultName);
                model.put("resultPackage", resultPackage);
                model.put("returnCodeName", returnCodeName);
                model.put("successCodeName", successCodeName);
                model.put("returnCodePackage", returnCodePackage);
                //包地址,全部转换为小写
                model.put("packageName", StringUtils.lowerCase(packageName));
                //小写类名,全部转为首字母小写
                model.put("className", StringUtils.uncapitalize(className));
                //大写类名,全部转为首字母大写
                model.put("ClassName", className);
                //作者,判断字段是否为空
                model.put("author", new String(author.getBytes(StandardCharsets.UTF_8)));
                //获得当前时间
                model.put("date", CodeGenerateUtils.getDate());
                //模块名称
                model.put("functionName", new String(functionName.getBytes(StandardCharsets.UTF_8)));
                //数据库表名
                model.put("tableName", table_name);
                //表数据取得list
                List<Table> list = CodeGenerateUtils.getList(path, table_name, database);
                model.put("list", list);
                //括号
                model.put("braceL", "{");
                model.put("braceR", "}");
                //参数
                model.put("serviceQueryList", parameterMap.get("serviceQueryList"));
                model.put("beanQuery", parameterMap.get("beanQuery"));


                /**
                 * 生成 mapper
                 */
                //FreeMarkers模板地址
                Template daoTemplate = cfg.getTemplate("mapper.java.ftl");

                //渲染生成模板
                String daoContent = FreeMarkers.renderTemplate(daoTemplate, model);
                //生成的文件名称,判断如果有二号项目,生成在二号项目
                String daoFile = address.replace(".", "/") + "/mapper/" + model.get("ClassName") + "Mapper.java";
                //将内容写入文件
                if (StringUtils.equalsIgnoreCase(String.valueOf(parameterMap.get("mapper")), "y")) {
                    CodeGenerateUtils.writeFile(daoContent, daoFile);
                }

                /**
                 * 生成状态枚举类
                 */
                //给予实体类值对象
                List<EnumsEntityName> enumsList = Lists.newArrayList();
                //生成的枚举对象
                List<EnumsEntityContent> enumsEntitiesList = Lists.newArrayList();
                for (Table t : list) {
                    //判断是否取固定枚举
                    if (parameterMap.get("fixedEnums") != null) {
                        if (!t.getTypeName().equals(parameterMap.get("fixedEnums"))) {
                            continue;
                        }
                    }
                    //获得注释
                    String a = StringUtils.deleteWhitespace(t.getColumnComment());
                    //截取备注的状态
                    String b = StringUtils.substringBetween(a, "（", "）");
                    //判断是否为空
                    if (StringUtils.isNotBlank(b)) {
                        //截取多个状态
                        String[] c = b.split("，");
                        //循环
                        for (String cc : c) {
                            String[] dd = cc.split("：");
                            //判断是否有参数
                            if (dd.length >= 2) {
                                //根据多个状态循环
                                EnumsEntityContent enumsEntity = new EnumsEntityContent();
                                enumsEntity.setState(dd[0]);
                                enumsEntity.setNote(dd[1]);
                                enumsEntitiesList.add(enumsEntity);
                            }
                        }
                        //放入数据
                        enumsList.add(new EnumsEntityName() {{
                            setSmallName(t.getColumnName());
                            setBigName(t.getColumnNameUpper());
                        }});
                        //名称
                        model.put("enumsEntityName", t.getColumnNameUpper());
                        //状态集合
                        model.put("enumsEntitiesList", enumsEntitiesList);
                        /**
                         * 生成状态枚举类
                         */
                        //FreeMarkers模板地址
                        Template beanTemplateStateEnums = cfg.getTemplate("entity_enums.java.ftl");
                        beanTemplateStateEnums.setEncoding("UTF-8");
                        //渲染生成模板
                        String beanContentStateEnums = FreeMarkers.renderTemplate(beanTemplateStateEnums, model);
                        //生成的文件地址
                        String beanFileStateEnums = address.replace(".", "/") + "/entity/enums/" + model.get("ClassName") + t.getColumnNameUpper() + "Enum" + ".java";
                        //将内容写入文件
                        if (StringUtils.equalsIgnoreCase(String.valueOf(parameterMap.get("enums")), "y")) {
                            CodeGenerateUtils.writeFile(beanContentStateEnums, beanFileStateEnums);
                        }
                    }
                    enumsEntitiesList = Lists.newArrayList();
                }
                model.put("enumsList", enumsList);
                /**
                 * 生成实体类
                 */
                //FreeMarkers模板地址
                Template beanTemplate = cfg.getTemplate("entity.java.ftl");
                beanTemplate.setEncoding("UTF-8");
                //渲染生成模板
                String beanContent = FreeMarkers.renderTemplate(beanTemplate, model);
                //生成的文件地址
                String beanFile = address.replace(".", "/") + "/entity/" + model.get("ClassName") + ".java";
                //将内容写入文件
                if (StringUtils.equalsIgnoreCase(String.valueOf(parameterMap.get("entity")), "y")) {
                    CodeGenerateUtils.writeFile(beanContent, beanFile);
                }

                /**
                 * 生成VO类
                 */
                //FreeMarkers模板地址
                Template beanTemplateVO = cfg.getTemplate("entity_vo.java.ftl");
                beanTemplateVO.setEncoding("UTF-8");
                //渲染生成模板
                String beanContentVO = FreeMarkers.renderTemplate(beanTemplateVO, model);
                //生成的文件地址
                String beanFileVO = address.replace(".", "/") + "/entity/vo/" + model.get("ClassName") + "VO" + ".java";
                //将内容写入文件
                if (StringUtils.equalsIgnoreCase(String.valueOf(parameterMap.get("entityVO")), "y")) {
                    CodeGenerateUtils.writeFile(beanContentVO, beanFileVO);
                }

                /**
                 * 生成VO类
                 */
                //FreeMarkers模板地址
                Template beanTemplateBO = cfg.getTemplate("entity_bo.java.ftl");
                beanTemplateBO.setEncoding("UTF-8");
                //渲染生成模板
                String beanContentBO = FreeMarkers.renderTemplate(beanTemplateBO, model);
                //生成的文件地址
                String beanFileBO = address.replace(".", "/") + "/entity/bo/" + model.get("ClassName") + "BO" + ".java";
                //将内容写入文件
                if (StringUtils.equalsIgnoreCase(String.valueOf(parameterMap.get("entityBO")), "y")) {
                    CodeGenerateUtils.writeFile(beanContentBO, beanFileBO);
                }

                /**
                 * 生成Query类
                 */
                //FreeMarkers模板地址
                Template beanTemplateQuery = cfg.getTemplate("entity_query.java.ftl");
                beanTemplateQuery.setEncoding("UTF-8");
                //渲染生成模板
                String beanContentQuery = FreeMarkers.renderTemplate(beanTemplateQuery, model);
                //生成的文件地址
                String beanFileQuery = address.replace(".", "/") + "/entity/query/" + model.get("ClassName") + "Query" + ".java";
                //将内容写入文件
                if (StringUtils.equalsIgnoreCase(String.valueOf(parameterMap.get("entityQuery")), "y")) {
                    CodeGenerateUtils.writeFile(beanContentQuery, beanFileQuery);
                }

                /**
                 * 生成 Service 代码
                 */
                //FreeMarkers模板地址
                Template serviceTemplate = cfg.getTemplate("service.java.ftl");
                serviceTemplate.setEncoding("UTF-8");
                //渲染生成模板
                String serviceContent = FreeMarkers.renderTemplate(serviceTemplate, model);
                //生成的文件名称
                String serviceFile = address.replace(".", "/") + "/service/" + model.get("ClassName") + "Service.java";
                //将内容写入文件
                if (StringUtils.equalsIgnoreCase(String.valueOf(parameterMap.get("service")), "y")) {
                    CodeGenerateUtils.writeFile(serviceContent, serviceFile);
                }

                /**
                 * 生成 Service 实现代码
                 */
                //FreeMarkers模板地址
                Template serviceImplTemplate = cfg.getTemplate("serviceImpl.java.ftl");
                serviceImplTemplate.setEncoding("UTF-8");
                //渲染生成模板
                String serviceI_Content = FreeMarkers.renderTemplate(serviceImplTemplate, model);
                //生成的文件名称
                String serviceI_File = address.replace(".", "/") + "/service/impl/" + model.get("ClassName") + "ServiceImpl.java";
                //将内容写入文件
                if (StringUtils.equalsIgnoreCase(String.valueOf(parameterMap.get("service")), "y")) {
                    CodeGenerateUtils.writeFile(serviceI_Content, serviceI_File);
                }

                /**
                 * 生成 Action代码
                 */
                //FreeMarkers模板地址
                Template actionTemplate = cfg.getTemplate("controller.java.ftl");
                actionTemplate.setEncoding("UTF-8");
                //渲染生成模板
                String actionContent = FreeMarkers.renderTemplate(actionTemplate, model);
                //生成的文件名称
                String actionFile = address.replace(".", "/") + "/controller/" + model.get("ClassName") + "Controller.java";
                //将内容写入文件
                if (StringUtils.equalsIgnoreCase(String.valueOf(parameterMap.get("controller")), "y")) {
                    CodeGenerateUtils.writeFile(actionContent, actionFile);
                }


                break;
            }
            tables.close();
            stateMent_table.close();
            connection.close();
        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
        }
    }
}
