package com.cg.utils.entity.enums;

@SuppressWarnings("all")
public enum CommonTypeEnum {
    P_DECORATOR(1, "singleton.constant.java.ftl", "装饰器模式"),;
    private int type;
    private String scriptName;
    private String desc;

    CommonTypeEnum(int type, String scriptName, String desc) {
        this.type = type;
        this.scriptName = scriptName;
        this.desc = desc;
    }

    public int getType() {
        return type;
    }

    public String getScriptName() {
        return scriptName;
    }

    public String getDesc() {
        return desc;
    }
}
