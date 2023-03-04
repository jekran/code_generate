package ${packageName}.service.impl;

import ${packageName}.entity.query.${ClassName}Query;
import ${packageName}.entity.vo.${ClassName}VO;
import ${packageName}.entity.bo.${ClassName}BO;
import ${packageName}.entity.${ClassName};
import ${packageName}.mapper.${ClassName}Mapper;
import ${packageName}.service.${ClassName}Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Setter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
* @author: ${author}
* @since:  ${date}
*/
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class ${ClassName}ServiceImpl extends ServiceImpl<${ClassName}Mapper, ${ClassName}> implements ${ClassName}Service {


    /**
    * ${functionName}
    */
    private ${ClassName}Mapper ${className}Mapper;


    /**
    * 分页条件查询
    * @author  ${author}
    * @since   ${date}
    * @param   page:
    * @param   ${className}Query:
    * @return  com.baomidou.mybatisplus.core.metadata.IPage
    */
    @Override
    public Page<${ClassName}VO> pageEnhance(${ClassName}Query ${className}Query) {
        ${ClassName} ${className} = GeneralConvertor.convertor(${className}Query, ${ClassName}.class);
        QueryWrapper<${ClassName}> queryWrapper = new QueryWrapper<>(${className});
        //DO数据
        Page<${ClassName}> pageDO = ${className}Mapper.selectPage(${className}Query.getPage(), queryWrapper);
        //VO数据
        Page<${ClassName}VO> pageVO = new Page<${ClassName}VO>();
        pageVO.setRecords(GeneralConvertor.convertor(pageDO.getRecords(), ${ClassName}VO.class));
        pageVO.setCurrent(pageDO.getCurrent());
        pageVO.setPages(pageDO.getPages());
        pageVO.setSize(pageDO.getSize());
        pageVO.setTotal(pageDO.getTotal());
        return pageVO;
    }


    /**
    * 单条条件查询
    * @author  ${author}
    * @since   ${date}
    * @param   ${className}Query:
    * @return  java.util.List<com.entity.${ClassName}VO>
    */
    @Override
    public ${ClassName}VO getOneEnhance(${ClassName}Query ${className}Query) {
    ${ClassName} ${className} = GeneralConvertor.convertor(${className}Query, ${ClassName}.class);
        QueryWrapper<${ClassName}> queryWrapper = new QueryWrapper<>(${className});
        //DO数据
        ${ClassName} ${className}DO = ${className}Mapper.selectOne(queryWrapper);
        //VO数据
        return GeneralConvertor.convertor(${className}DO, ${ClassName}VO.class);
    }



   /**
   * 新增
   * @author  ${author}
   * @since   ${date}
   * @param   ${className}BO:
   * @return  java.lang.String
   */
   @Override
   @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
   public String saveEnhance(${ClassName}BO ${className}BO) {
       ${ClassName} ${className} = GeneralConvertor.convertor(${className}BO, ${ClassName}.class);
       ${className}Mapper.insert(${className});
       return ${className}.getId();
   }


   /**
   * 修改
   * @author  ${author}
   * @since   ${date}
   * @param   ${className}BO:
   * @return  java.lang.Boolean
   */
   @Override
   @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
       public Boolean updateEnhance(${ClassName}BO ${className}BO) {
                //todo 权限校验
       ${ClassName} ${className} = GeneralConvertor.convertor(${className}BO, ${ClassName}.class);
       UpdateWrapper<${ClassName} > updateWrapper = new UpdateWrapper<>();
       updateWrapper.eq("id", ${className}BO.getId());
       Integer i = ${className}Mapper.update(${className}, updateWrapper);
       return i > 0 ? true : false;
   }


   /**
   * 删除
   * @author  ${author}
   * @since   ${date}
   * @param   ${className}BO:
   * @return  java.lang.Boolean
   */
   @Override
   @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
       public Boolean removeEnhance(${ClassName}BO ${className}BO) {
        //todo 权限校验
       ${ClassName} ${className} = GeneralConvertor.convertor(${className}BO, ${ClassName}.class);
       QueryWrapper<${ClassName}> queryWrapper = new QueryWrapper<>(${className});
       Integer i = ${className}Mapper.delete(queryWrapper);
       return i > 0 ? true : false;
   }

}