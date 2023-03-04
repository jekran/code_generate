package ${packageName};
import java.util.function.Function;
import java.util.HashMap;
import com.google.common.collect.Maps;

/**
 * @author: ${author}
 * @since: ${date}
 */
public class ${ClassName} {


    private static HashMap<${ClassName}BizEnum, Function<Object, Object>> bizFuncMap = Maps.newHashMap();

    static {
        bizFuncMap.put(${ClassName}BizEnum.DI_ZHU, ${ClassName}::biz1);
        // todo add more action...
    }

    private static Object biz1(Object param) {
        return param;
    }

    public static void doBiz(${ClassName}BizEnum bizEnum, Object param) {
        bizFuncMap.get(bizEnum).apply(param);
    }
}

