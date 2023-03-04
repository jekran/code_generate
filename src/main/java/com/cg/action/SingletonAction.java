package com.cg.action;

import com.cg.dialog.SingletonDialog;
import com.cg.utils.CodeGenerateUtils;
import com.cg.utils.MyNotifier;
import com.cg.utils.entity.enums.SingletonTypeEnum;
import com.cg.utils.freemarker.FreeMarkers;
import com.google.common.collect.Maps;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.psi.PsiDirectory;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class SingletonAction extends BaseAnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        this.init(e);
        SingletonDialog formTestDialog = new SingletonDialog(e.getProject(), this);
        //是否允许用户通过拖拽的方式扩大或缩小你的表单框
        formTestDialog.setResizable(true);
        formTestDialog.show();
    }


    public void doGenerate(SingletonTypeEnum type, String moduleName, String author, String className) {
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
            cfg.setClassLoaderForTemplateLoading(getClass().getClassLoader(),"/templates/designpatterns/singleton");

            //FreeMarkers模板地址
            Template designTemplate = cfg.getTemplate(type.getScriptName());
            //渲染生成模板
            String designContent = FreeMarkers.renderTemplate(designTemplate, model);
            //生成的文件名称,判断如果有二号项目,生成在二号项目
            String writePath = path + "/src/main/java/" + moduleName + "/" + className + ".java";
            //将内容写入文件
            CodeGenerateUtils.writeFile(designContent, writePath);
            getProject().getBaseDir().refresh(false, true);
            MyNotifier.notifyInformation(this.getProject(), "代码生成成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
