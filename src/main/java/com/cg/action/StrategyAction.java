package com.cg.action;

import com.cg.dialog.StrategyDialog;
import com.cg.utils.CodeGenerateUtils;
import com.cg.utils.MyNotifier;
import com.cg.utils.entity.enums.StrategyTypeEnum;
import com.cg.utils.freemarker.FreeMarkers;
import com.google.common.collect.Maps;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.psi.PsiDirectory;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class StrategyAction extends BaseAnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        this.init(e);
        StrategyDialog formTestDialog = new StrategyDialog(e.getProject(), this);
        //是否允许用户通过拖拽的方式扩大或缩小你的表单框
        formTestDialog.setResizable(true);
        formTestDialog.show();
    }


    public void doGenerate(StrategyTypeEnum type, String moduleName, String author, String className) {
        /**
         * 参数
         */
        final PsiDirectory psiDirectory = this.getPsiDirectory();
        /**
         * 项目地址:
         */
        String path = psiDirectory.getVirtualFile().getPath().split("src/main/")[0];
        if (StringUtils.isBlank(path)) {
            MyNotifier.notifyError(this.getProject(), "目录信息未找到，请检查生成地址");
        }
        //参数
        try {
            Map<String, Object> model = Maps.newHashMap();
            model.put("ClassName", className);
            model.put("packageName", moduleName);
            //作者,判断字段是否为空
            model.put("author", author);
            //获得当前时间
            model.put("date", CodeGenerateUtils.getDate());
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
            cfg.setClassLoaderForTemplateLoading(getClass().getClassLoader(),"/templates/designpatterns/strategy/");

            String writePath;
            String[] scripts;
            switch (type) {
                case SG_FUNCTION:
                    // 生成业务枚举类
                    scripts = type.getScriptName().split(",");
                    writePath = path + "/src/main/java/" + moduleName + "/" + className + "BizEnum.java";
                    generateFile(scripts[0], cfg, writePath, model);
                    // 生成业务类
                    writePath = path + "/src/main/java/" + moduleName + "/" + className + ".java";
                    generateFile(scripts[1], cfg, writePath, model);
                    break;
                case SG_ENUM:
                    // 生成业务类
                    writePath = path + "/src/main/java/" + moduleName + "/" + className + "StrategyEnum.java";
                    generateFile(type.getScriptName(), cfg, writePath, model);
                    break;
                case SG_FACTORY:
                    // 生成业务枚举类
                    String[] split = type.getScriptName().split(",");
                    String enumScript = split[0];
                    String contextScript = split[1];
                    String interfaceScript = split[2];
                    String bizAScript = split[3];
                    String bizBScript = split[4];
                    String abstractScript = split[5];
                    writePath = path + "/src/main/java/" + moduleName + "/" + className + "BizEnum.java";
                    generateFile(enumScript, cfg, writePath, model);
                    // 生成工厂类
                    writePath = path + "/src/main/java/" + moduleName + "/" + className + "StrategyFactory.java";
                    generateFile(contextScript, cfg, writePath, model);
                    // 生成接口类
                    writePath = path + "/src/main/java/" + moduleName + "/" + "I" + className + ".java";
                    generateFile(interfaceScript, cfg, writePath, model);
                    // 生成业务类A
                    writePath = path + "/src/main/java/" + moduleName + "/" + className + "A.java";
                    generateFile(bizAScript, cfg, writePath, model);
                    // 生成业务类B
                    writePath = path + "/src/main/java/" + moduleName + "/" + className + "B.java";
                    generateFile(bizBScript, cfg, writePath, model);
                    // 生成抽象类
                    writePath = path + "/src/main/java/" + moduleName + "/" + "abstract" + className + ".java";
                    generateFile(abstractScript, cfg, writePath, model);
                    default:
                        break;
            }

            MyNotifier.notifyInformation(this.getProject(), "代码生成成功，请刷新目录");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void generateFile(String scriptName, Configuration cfg, String writePath, Map<String, Object> model) throws Exception {
        //FreeMarkers模板地址
        Template designTemplate = cfg.getTemplate(scriptName);
        //渲染生成模板
        String designContent = FreeMarkers.renderTemplate(designTemplate, model);
        //生成的文件名称,判断如果有二号项目,生成在二号项目
        //将内容写入文件
        CodeGenerateUtils.writeFile(designContent, writePath);
    }
}
