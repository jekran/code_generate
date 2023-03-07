package com.cg.dialog;

import com.cg.action.CgAction;
import com.cg.swing.CgSwing;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;

import javax.annotation.Nullable;
import javax.swing.*;

public class CgDialog extends DialogWrapper {
 
    private String projectName;
     
    //swing样式类，定义在4.3.2
    private CgSwing formTestSwing = new CgSwing();

    CgAction action;
 
    public CgDialog(@Nullable Project project, CgAction action) {
        super(true);
        this.action = action;
        setTitle("代码生成~~"); //设置会话框标题
        setSize(600, 200);
        this.projectName = project.getName(); //获取到当前项目的名称
        init(); //触发一下init方法，否则swing样式将无法展示在会话框
    }
 
    @Override
    protected JComponent createNorthPanel() {
        return formTestSwing.initNorth(); //返回位于会话框north位置的swing样式
    }
 
    // 特别说明：不需要展示SouthPanel要重写返回null，否则IDEA将展示默认的"Cancel"和"OK"按钮
    @Override
    protected JComponent createSouthPanel() {
        return formTestSwing.initSouth(action::doGenerate);
    }
 
    @Override
    protected JComponent createCenterPanel() {
        //定义表单的主题，放置到IDEA会话框的中央位置
        return formTestSwing.initCenter();
    }
}
