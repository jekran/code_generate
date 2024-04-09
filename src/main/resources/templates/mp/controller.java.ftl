package ${packageName}.controller;

import com.gb.utils.annotations.RequestRequired;
import com.gb.utils.annotations.PreventRepeat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;
import org.springframework.validation.annotation.Validated;
import ${packageName}.service.${ClassName}Service;
import ${packageName}.entity.query.${ClassName}Query;
import ${packageName}.entity.vo.${ClassName}VO;
import ${packageName}.entity.bo.${ClassName}BO;
import ${returnCodePackage}.${returnCodeName};
import ${resultPackage}.${resultName};


/**
* @author: ${author}
* @since:  ${date}
*/
@Slf4j
@Setter(onMethod_ = {@Autowired})
@RestController
@RequestRequired
@RequestMapping("/${className}")
@Api(tags = "${functionName}")
public class ${ClassName}Controller {


/**
* ${functionName}
*/
private ${ClassName}Service ${className}Service;



    /**
    * ${functionName}集合分页查询
    *
    * @param ${className}Query:
    * @author ${author}
    * @since ${date}
    */
    @ApiOperation(value = "${functionName}集合分页查询", httpMethod = "GET", notes = "${functionName}集合查询", response = ${resultName}.class)
    @GetMapping("/select")
    public ${resultName}<Page<${ClassName}VO>> select(@Validated(value = ${ClassName}Query.Select.class) ${ClassName}Query ${className}Query) {
        //返回内容
        return new ${resultName}(${successCodeName}, ${className}Service.pageEnhance(${className}Query));
    }



    /**
    * ${functionName}单条查询
    *
    * @param ${className}Query:
    * @author ${author}
    * @since ${date}
    */
    @ApiOperation(value = "${functionName}单条查询", httpMethod = "GET", notes = "${functionName}单条查询", response = ${resultName}.class)
    @GetMapping("/selectOne")
    public ${resultName}<${ClassName}VO> selectOne(@Validated(value = ${ClassName}Query.SelectOne.class) ${ClassName}Query ${className}Query) {
        //返回内容
        return new ${resultName}(${successCodeName}, ${className}Service.getOneEnhance(${className}Query));
    }



    /**
    * ${functionName}新增
    *
    * @param ${className}BO:
    * @author ${author}
    * @since ${date}
    */
    @PreventRepeat
    @ApiOperation(value = "${functionName}新增", httpMethod = "POST", notes = "${functionName}新增", response = ${resultName}.class)
    @PostMapping("/save")
    public ${resultName}<String> save(@Validated(value = ${ClassName}BO.Save.class) ${ClassName}BO ${className}BO, HttpServletRequest httpServletRequest) {
        //返回内容
        return new ${resultName}(${successCodeName}, ${className}Service.saveEnhance(${className}BO));
    }


    /**
    * ${functionName}修改
    *
    * @param ${className}BO:
    * @author ${author}
    * @since ${date}
    */
    @PreventRepeat
    @ApiOperation(value = "${functionName}修改", httpMethod = "PUT", notes = "${functionName}修改", response = ${resultName}.class)
    @PutMapping("/update")
    public ${resultName}<Boolean> update(@Validated(value = ${ClassName}BO.Update.class) ${ClassName}BO ${className}BO, HttpServletRequest httpServletRequest) {
        //返回内容
        return new ${resultName}(${successCodeName}, ${className}Service.updateEnhance(${className}BO));
    }


    /**
    * ${functionName}删除
    *
    * @param ${className}BO:
    * @author ${author}
    * @since ${date}
    */
    @ApiOperation(value = "${functionName}删除", httpMethod = "DELETE", notes = "${functionName}删除", response = ${resultName}.class)
    @DeleteMapping("/remove")
    public ${resultName}<Boolean> remove(@Validated(value = ${ClassName}BO.Remove.class) ${ClassName}BO ${className}BO) {
        return new ${resultName}(${successCodeName}, ${className}Service.removeEnhance(${className}BO));
    }

}