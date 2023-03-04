package ${packageName}.service;

import ${packageName}.entity.query.${ClassName}Query;
import ${packageName}.entity.vo.${ClassName}VO;
import ${packageName}.entity.bo.${ClassName}BO;
import ${packageName}.entity.${ClassName};
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;


/**
* @author: ${author}
* @since:  ${date}
*/
public interface ${ClassName}Service extends IService<${ClassName}> {

     /**
     * 分页条件查询
     * @author  ${author}
     * @since   ${date}
     * @param   page:
     * @param   ${className}Query:
     * @return  com.baomidou.mybatisplus.extension.plugins.pagination.Page
     */
     Page<${ClassName}VO> pageEnhance(${ClassName}Query ${className}Query);


     /**
     * 单条条件查询
     * @author  ${author}
     * @since   ${date}
     * @param   ${className}Query:
     * @return  java.util.List<com.entity.${ClassName}VO>
     */
     ${ClassName}VO getOneEnhance(${ClassName}Query ${className}Query);



     /**
     * 新增
     * @author  ${author}
     * @since   ${date}
     * @param   ${className}BO:
     * @return  java.lang.String
     */
     String saveEnhance(${ClassName}BO ${className}BO);


     /**
     * 修改
     * @author  ${author}
     * @since   ${date}
     * @param   ${className}BO:
     * @return  java.lang.Boolean
     */
     Boolean updateEnhance(${ClassName}BO ${className}BO);


     /**
     * 删除
     * @author  ${author}
     * @since   ${date}
     * @param   ${className}BO:
     * @return  java.lang.Boolean
     */
     Boolean removeEnhance(${ClassName}BO ${className}BO);
}