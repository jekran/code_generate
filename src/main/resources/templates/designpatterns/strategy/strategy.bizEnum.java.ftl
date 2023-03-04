package ${packageName};


/**
* @author: ${author}
* @since: ${date}
*/
public enum ${ClassName}BizEnum {
    DI_ZHU("dizhu", "破产"),NONG_MING("nongming", "暴富"),;

    /**
    * 业务类型编码
    */
    private String bizType;
    /**
    * 业务类型描述
    */
    private String desc;


    ${ClassName}BizEnum(String bizType, String desc) {
        this.bizType = bizType;
        this.desc = desc;
    }

    public String getBizType() {
        return bizType;
    }

    public String getDesc() {
        return desc;
    }
}

