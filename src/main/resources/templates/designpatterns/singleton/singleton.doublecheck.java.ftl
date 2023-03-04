package ${packageName};


/**
* @author: ${author}
* @since: ${date}
*/
public class ${ClassName} {

    private ${ClassName}() {
    }

    private static volatile ${ClassName} INSTANCE;

    public static ${ClassName} getInstance() {
        if (INSTANCE == null) {
            synchronized (${ClassName}.class) {
            // 未初始化，则初始instance变量
                if (INSTANCE == null) {
                    INSTANCE = new ${ClassName}();
                }
            }
        }
        return INSTANCE;
    }

}
