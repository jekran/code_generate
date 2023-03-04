package ${packageName};
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
* @author: ${author}
* @since: ${date}
*/
@Service
public class ${ClassName}StrategyFactory {

    private static Map<String,I${ClassName}> services = new ConcurrentHashMap<String,I${ClassName}>();


    boolean doBiz(String param) {
        // TODO
        return true;
    }

    public  static I${ClassName} getByBizType(String type){
        return services.get(type);
    }

    public static void register(String bizType, I${ClassName} bizService){
        services.put(bizType,bizService);
    }


}

