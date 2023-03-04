package com.cg.swing;

import com.cg.action.BConsumer;

import javax.swing.*;
import java.awt.*;


public class CgSwing {

    private JPanel north = new JPanel();

    private JPanel center = new JPanel();

    private JPanel south = new JPanel();

    //为了让位于底部的按钮可以拿到组件内容，这里把表单组件做成类属性

    private JLabel authorName = new JLabel("作者：");
    private JTextArea authorContent = new JTextArea();

    private JLabel moduleName = new JLabel("模块名：");
    private JTextArea moduleNameContent = new JTextArea();

    private JLabel tableNames = new JLabel("表名（多表逗号分隔）：");
    private JTextArea tableNamesContent = new JTextArea();

    public JPanel initNorth() {

        //定义表单的标题部分，放置到IDEA会话框的顶部位置

        JLabel title = new JLabel();
        title.setFont(new Font("微软雅黑", Font.PLAIN, 26)); //字体样式
        title.setHorizontalAlignment(SwingConstants.CENTER); //水平居中
        title.setVerticalAlignment(SwingConstants.CENTER); //垂直居中
        north.add(title);

        return north;
    }

    public JPanel initCenter() {

        //定义表单的主体部分，放置到IDEA会话框的中央位置

        //一个简单的3行2列的表格布局
        center.setLayout(new GridLayout(4, 1));
//        center.setLayout(new GridBagLayout());


//        authorContent.setBounds(0,0,200,200);
        authorContent.setPreferredSize(new Dimension(200, 30));
        center.add(authorName);
        authorContent.setLineWrap(true);
        authorContent.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
        center.add(authorContent);
//        authorContent.setHorizontalAlignment(JTextField.LEFT);

        //row3：年龄+文本框
        center.add(moduleName);
        moduleNameContent.setLineWrap(true);
        moduleNameContent.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
        center.add(moduleNameContent);

        //row3：年龄+文本框
        center.add(tableNames);
        tableNamesContent.setLineWrap(true);
        tableNamesContent.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
        center.add(tableNamesContent);

        return center;
    }


    public JPanel initSouth(BConsumer<String, String, String> consumer) {

        //定义表单的提交按钮，放置到IDEA会话框的底部位置

        JButton submit = new JButton("提交");
        submit.setHorizontalAlignment(SwingConstants.CENTER); //水平居中
        submit.setVerticalAlignment(SwingConstants.CENTER); //垂直居中
        south.add(submit);

        //按钮事件绑定
        submit.addActionListener(e -> {
            //获取到name和age
            String authorName = authorContent.getText();
            String moduleName = moduleNameContent.getText();
            String tableNames = tableNamesContent.getText();
            consumer.accept(authorName, tableNames, moduleName);
        });
        return south;
    }
}
