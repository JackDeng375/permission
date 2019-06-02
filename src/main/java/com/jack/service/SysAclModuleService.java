package com.jack.service;

import com.jack.dao.SysAclModuleMapper;
import com.jack.model.SysAcl;
import com.jack.model.SysAclModule;
import com.jack.param.AclModuleParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysAclModuleService {
    @Autowired
    private SysAclModuleMapper sysAclModuleMapper;

    public void save(AclModuleParam param) {

    }

    public void update(AclModuleParam param) {

    }

    //递归更新子权限
    private void updateWithChild(SysAclModule before, SysAclModule after) {

    }

    //TODO:9-2权限开发
}
