package com.cg.swing;

import com.cg.action.EBConsumer;
import com.cg.utils.entity.enums.StrategyTypeEnum;

import javax.swing.*;
import java.awt.*;

import static com.cg.utils.entity.enums.StrategyTypeEnum.*;


public class StrategySwing {

    private JPanel north = new JPanel();

    private JPanel center = new JPanel();

    private JPanel south = new JPanel();


    ButtonGroup gb = new ButtonGroup();
    JRadioButton enumJrb = new JRadioButton("枚举（简单业务推荐）");
    JRadioButton interfaceJrb = new JRadioButton("函数式");
    JRadioButton factoryJrb = new JRadioButton("工厂加模版（复杂业务推荐）");

    private JLabel authorName = new JLabel("作者：");
    private JTextArea authorContent = new JTextArea();
//
    private JLabel className = new JLabel("策略类名：");
    private JTextArea classNameContent = new JTextArea();
//
    private JLabel moduleName = new JLabel("模块名：");
    //    private JTextField tableNamesContent = new JTextField();
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
//        center.setLayout(new GridBagLayout());
        gb.add(enumJrb);
        gb.add(interfaceJrb);
        factoryJrb.setSelected(true);
        gb.add(factoryJrb);

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

        center.add(enumJrb);
        center.add(interfaceJrb);
        center.add(factoryJrb);
        return center;
    }


    public JPanel initSouth(EBConsumer<StrategyTypeEnum, String, String, String> consumer) {

        //定义表单的提交按钮，放置到IDEA会话框的底部位置

        JButton submit = new JButton("提交");
        submit.setHorizontalAlignment(SwingConstants.CENTER); //水平居中
        submit.setVerticalAlignment(SwingConstants.CENTER); //垂直居中
        south.add(submit);

        //按钮事件绑定
        submit.addActionListener(e -> {
            boolean enumJrbSelected = enumJrb.isSelected();
            boolean interfaceJrbSelected = interfaceJrb.isSelected();
            StrategyTypeEnum destEnum;
            if(enumJrbSelected) {
                destEnum = SG_ENUM;
            } else if(interfaceJrbSelected) {
                destEnum = SG_FUNCTION;
            } else {
                destEnum = SG_FACTORY;
            }
            String authorName = authorContent.getText();
            String moduleName = moduleNameContent.getText();
            String className = classNameContent.getText();
            consumer.accept(destEnum, moduleName, authorName, className);
        });
        return south;
    }
}
