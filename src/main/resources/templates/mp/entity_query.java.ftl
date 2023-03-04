package ${packageName}.entity.query;


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
 * @author:  ${author}
 * @since:   ${date}
 */
@Data
@ApiModel(value = "${functionName}查询")
public class ${ClassName}Query extends PageParam implements Serializable {
    <#list list as item>
    <#if item.columnName == "version">
    <#elseif item.columnName == "createDateTime">
    <#elseif item.columnName == "createName">
    <#elseif item.columnName == "modifyDateTime">
    <#elseif item.columnName == "modifyName">
    <#elseif item.columnName == "isDelete">
    <#else>

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
    </#if>
    </#list>

    @ApiModelProperty(value = "显示字段")
    private String fields;

    @ApiModelProperty(value = "排序规则(0:desc,1:asc)")
    private Boolean collation;

    @ApiModelProperty(value = "排序字段")
    private String collationFields = "create_date_time";

    @ApiModelProperty(value = "模糊查询")
    private String query;
<#if beanQuery??>
    <#list beanQuery as bq>

    @ApiModelProperty(value = "${bq.description}")
    private ${bq.format} ${bq.name};
    </#list>
</#if>

    /**
    * 查询分页方法
    */
    public interface Select{}

    /**
    * 查询方法
    */
    public interface SelectList{}

    /**
    * 单条查询
    */
    public interface SelectOne{}

    /**
    * 总数参数
    */
    public interface Count{}
}

