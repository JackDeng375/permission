package com.jack.dto;

import com.google.common.collect.Lists;
import com.jack.model.SysAclModule;
import com.jack.model.SysDept;

import java.util.List;

//TODO: 9-2
public class AclModuleLevelDto extends SysAclModule {
    private List<AclModuleLevelDto> aclModuleLevelDtoList = Lists.newArrayList();
}
