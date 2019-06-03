package com.jack.dao;

import com.jack.model.SysAclModule;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysAclModuleMapper extends Mapper<SysAclModule> {

    int countByNameAndParentId(@Param("parentId") Integer parentId,
                                   @Param("aclModuleName") String aclModuleName,
                                   @Param("deptId") Integer deptId);

    List<SysAclModule> getChildAclModuleListByLevel(@Param("level") String level);
}