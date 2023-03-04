package ${packageName}.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.*;
<#list enumsList as e>
import ${packageName}.entity.enums.${ClassName}${e.bigName}Enum;
</#list>
<#assign b = 0>
/**
 * @author:  ${author}
 * @since:   ${date}
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("`${tableName}`")
@ApiModel(value="${ClassName}对象", description="${functionName}")
public class ${ClassName} extends EntityBase {
<#list list as item>
    <#if item.columnName == "id">
    <#elseif item.columnName == "description">
    <#elseif item.columnName == "createDateTime">
    <#elseif item.columnName == "createName">
    <#elseif item.columnName == "modifyDateTime">
    <#elseif item.columnName == "modifyName">
    <#elseif item.columnName == "isDelete">
    <#elseif item.columnName == "sorting">
    <#elseif item.columnName == "version">
    <#else>

        <#if item.dataType == "LocalDateTime">
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        </#if>
    @ApiModelProperty(value = "${item.columnComment}")
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
    </#if>
</#list>
}