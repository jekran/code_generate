package com.cg.action;

import com.cg.dialog.CgDialog;
import com.cg.utils.CodeGenerate;
import com.cg.utils.MyNotifier;
import com.google.common.collect.Maps;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.psi.PsiDirectory;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

public class CgAction extends BaseAnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        this.init(e);
        CgDialog formTestDialog = new CgDialog(e.getProject(), this);
        //是否允许用户通过拖拽的方式扩大或缩小你的表单框
        formTestDialog.setResizable(true);
        formTestDialog.show();
    }


    public void doGenerate(String author, String tableName, String moduleName) {
        if (StringUtils.isNotBlank(author) && StringUtils.isNotBlank(tableName) && StringUtils.isNotBlank(moduleName)) {
            /**
             * 参数
             */
            final PsiDirectory psiDirectory = this.getPsiDirectory();
            /**
             * 项目地址
             */
            String path = psiDirectory.getVirtualFile().getPath().split("src/main/")[0];
            if (StringUtils.isBlank(path)) {
                MyNotifier.notifyError(this.getProject(), "目录信息未找到，请检查生成地址");
            }
            //参数
            Map<String, Object> map = Maps.newHashMap();
            map.put("author", author);
            map.put("moduleName", moduleName);
            map.put("include", tableName);
            map.put("controller", "y");
            map.put("entity", "y");
            map.put("entityBO", "y");
            map.put("entityVO", "y");
            map.put("entityQuery", "y");
            map.put("enums", "y");
            map.put("service", "y");
            map.put("serviceResults", "y");
            map.put("serviceQuery", "y");
            map.put("mapper", "y");
            try {
                String[] includeS = map.get("include").toString().split(",");
                for (String s : includeS) {
                    //重写代码生成器
                    CodeGenerate.foundCode(this.getProject(), s, String.valueOf(map.get("moduleName")), String.valueOf(map.get("author")), map, path);
                }
                getProject().getBaseDir().refresh(false, true);
                MyNotifier.notifyInformation(this.getProject(), "代码生成成功");
            } catch (FileNotFoundException e) {
                MyNotifier.notifyError(this.getProject(), "未找到对应文件：" + e.getMessage());
                e.printStackTrace();
            } catch (IOException e) {
                MyNotifier.notifyError(this.getProject(), "获取文件失败：" + e.getMessage());
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
