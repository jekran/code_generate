package ${packageName};


/**
* @author: ${author}
* @since: ${date}
*/
public abstract class abstract${ClassName} implements I${ClassName}{

    @Override
    public boolean doBiz(String param) {
        // 公共处理
        doFist(param);
        // 个性化操作
        personalityAction(param);
        return true;
    }

    public abstract void personalityAction(String params);


    public void doFist(String param) {
    }
}

