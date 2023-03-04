package ${packageName};
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;


/**
* @author: ${author}
* @since: ${date}
*/
@Service
public class ${ClassName}B extends abstract${ClassName} implements InitializingBean {


    @Override
    public void personalityAction(String param) {
        // 个性化处理B
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        ${ClassName}StrategyFactory.register(${ClassName}BizEnum.NONG_MING.getBizType(), this);
    }

}

