package ${packageName}.entity.bo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
<#list enumsList as e>
import ${packageName}.entity.enums.${ClassName}${e.bigName}Enum;
</#list>

<#assign b = 0>
/**
 * @author:  ${author}
 * @since:   ${date}
 */
@Data
@ApiModel(value = "${functionName}传输")
public class ${ClassName}BO implements Serializable {
	<#list list as item>

    @ApiModelProperty(value = "${item.columnComment}")
    <#if item.columnName == "id">
    @NotBlank(groups = {${ClassName}BO.Update.class,${ClassName}BO.Remove.class},message = "id不能为空")
    </#if>
    <#if item.dataType == "LocalDateTime">
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    </#if>
    <#list enumsList as e>
        <#if item.columnName == e.smallName>
    private ${ClassName}${e.bigName}Enum ${item.columnName};
            <#assign b = 1>
        </#if>
    </#list>
    <#if b = 0>
    private ${item.dataType} ${item.columnName};
    </#if>
    <#assign b = 0>
    </#list>


    /**
    * 新增
    */
    public interface Save{}

    /**
    * 修改
    */
    public interface Update{}

    /**
    * 删除
    */
    public interface Remove{}
}

