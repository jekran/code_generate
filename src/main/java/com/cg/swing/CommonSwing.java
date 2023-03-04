package com.cg.swing;

import com.cg.action.BConsumer;

import javax.swing.*;
import java.awt.*;


public class CommonSwing {

    private JPanel north = new JPanel();

    private JPanel center = new JPanel();

    private JPanel south = new JPanel();


    private JLabel authorName = new JLabel("作者：");
    private JTextArea authorContent = new JTextArea();
//
    private JLabel className = new JLabel("类名：");
    private JTextArea classNameContent = new JTextArea();
//
    private JLabel moduleName = new JLabel("模块名：");
    private JTextArea moduleNameContent = new JTextArea();

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

        center.setLayout(new GridLayout(5, 2));

        center.add(authorName);
        authorContent.setLineWrap(true);
        authorContent.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
        center.add(authorContent);

        center.add(moduleName);
        moduleNameContent.setLineWrap(true);
        moduleNameContent.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
        center.add(moduleNameContent);

        center.add(className);
        classNameContent.setLineWrap(true);
        classNameContent.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
        center.add(classNameContent);

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
            String authorName = authorContent.getText();
            String moduleName = moduleNameContent.getText();
            String className = classNameContent.getText();
            consumer.accept(moduleName, authorName, className);
        });
        return south;
    }
}
