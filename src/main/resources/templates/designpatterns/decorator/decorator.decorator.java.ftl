package ${packageName};


/**
* @author: ${author}
* @since: ${date}
*/
@Service
public class ${ClassName}Decorator {
    private I${ClassName} origin${ClassName};

    public ${ClassName}Decorator(I${ClassName} ${ClassName}) {
        this.origin${ClassName} = ${ClassName};
    }


    public boolean doBiz(String param) {
        origin${ClassName}.doBiz(param);
        doElse();
        return true;
    }

    private void doElse() {
    }
}

