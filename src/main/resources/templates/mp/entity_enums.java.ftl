package ${packageName}.entity.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
* @author: ${author}
* @since:  ${date}
*/
@Getter
@AllArgsConstructor
public enum ${ClassName}${enumsEntityName}Enum implements IEnum<Integer> {
    <#list enumsEntitiesList as enumsEntities>
        ${enumsEntities.note}(${enumsEntities.state}, "${enumsEntities.note}"),
    </#list>
    ;

    private Integer value;
    private String desc;


    /**
    * 通过value获得枚举
    *
    * @param value
    * @return
    */
    public static ${ClassName}${enumsEntityName}Enum get${ClassName}${enumsEntityName}Enum(Integer value) {
    for (${ClassName}${enumsEntityName}Enum ${className}${enumsEntityName}Enum : ${ClassName}${enumsEntityName}Enum.values()) {
    if (${className}${enumsEntityName}Enum.getValue().equals(value)) {
    return ${className}${enumsEntityName}Enum;
    }
    }
    return null;
    }


    /**
    * 获得备注
    *
    * @param value
    * @return
    */
    public static String getDesc(Integer value) {
    for (${ClassName}${enumsEntityName}Enum ${className}${enumsEntityName}Enum : ${ClassName}${enumsEntityName}Enum.values()) {
    if (${className}${enumsEntityName}Enum.getValue().equals(value)) {
    return ${className}${enumsEntityName}Enum.getDesc();
    }
    }
    return null;
    }


    /**
    * 获得值
    *
    * @param desc
    * @return
    */
    public static Integer getDesc(String desc) {
    for (${ClassName}${enumsEntityName}Enum ${className}${enumsEntityName}Enum : ${ClassName}${enumsEntityName}Enum.values()) {
    if (${className}${enumsEntityName}Enum.getDesc().equals(desc)) {
    return ${className}${enumsEntityName}Enum.getValue();
    }
    }
    return null;
    }
    }