package ${packageName};


/**
* @author: ${author}
* @since: ${date}
*/
public enum ${ClassName}StrategyEnum {

    BIZ_1(1, "用户登录") {
    @Override
    public Object doBiz(Object o) {
        // dosomething
        return o;
    }};

    private final int type;
    private final String desc;

    ${ClassName}StrategyEnum(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public abstract Object doBiz(Object o);
}

