package ${packageName}.entity.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
<#list enumsList as e>
    import ${packageName}.entity.enums.${ClassName}${e.bigName}Enum;
</#list>

<#assign b = 0>
/**
* @author: ${author}
* @since:  ${date}
*/
@Data
@ApiModel(value = "${functionName}渲染")
public class ${ClassName}VO implements Serializable {
<#list list as item>

    @ApiModelProperty(value = "${item.columnComment}")
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
}

