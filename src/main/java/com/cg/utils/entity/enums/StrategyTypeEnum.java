package com.cg.utils.entity.enums;

@SuppressWarnings("all")
public enum StrategyTypeEnum {
    SG_FUNCTION(1, "strategy.bizEnum.java.ftl,strategy.function.java.ftl", "函数式")
    , SG_ENUM(2, "strategy.enum.java.ftl", "枚举式")
    , SG_FACTORY(3,
            "strategy.bizEnum.java.ftl," +
                    "strategy.factory_context.java.ftl," +
            "strategy.factory_interface.java.ftl," +
                    "strategy.factory_bizA.java.ftl," +
            "strategy.factory_bizB.java.ftl,"+
            "strategy.factory_abstract.java.ftl",
            "工厂式"),;
    private int type;
    private String scriptName;
    private String desc;

    StrategyTypeEnum(int type, String scriptName, String desc) {
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
