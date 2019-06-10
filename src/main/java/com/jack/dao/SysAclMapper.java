package com.jack.dao;

import com.jack.bean.PageQuery;
import com.jack.bean.PageResult;
import com.jack.model.SysAcl;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysAclMapper extends Mapper<SysAcl> {

    int countByAclModuleId(@Param("aclModuleId") int aclModuleId);

    List<SysAcl> getPageByAclModuleId(@Param("aclModuleId") int aclModuleId, @Param("page")PageQuery pageQuery);

    int countByNameAndAclModuleId(@Param("aclModuleId") int aclModuleId, @Param("name")String name,@Param("id") Integer id);
}