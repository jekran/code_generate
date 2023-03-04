package com.cg.utils.entity.enums;

import lombok.Data;

@SuppressWarnings("all")
public enum SingletonTypeEnum {
    SG_CONSTANT(1, "singleton.constant.java.ftl", "常量单例")
    , SG_ENUM(2, "singleton.enum.java.ftl", "枚举单例")
    , SG_DOUBLE_CHECK(3,"singleton.doublecheck.java.ftl", "双重校验锁单例"),;
    private int type;
    private String scriptName;
    private String desc;

    SingletonTypeEnum(int type, String scriptName, String desc) {
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
