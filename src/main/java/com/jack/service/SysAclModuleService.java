package com.jack.service;

import com.google.common.base.Preconditions;
import com.jack.common.RequestHolder;
import com.jack.dao.SysAclModuleMapper;
import com.jack.exception.ParamException;
import com.jack.model.SysAcl;
import com.jack.model.SysAclModule;
import com.jack.model.SysDept;
import com.jack.param.AclModuleParam;
import com.jack.util.BeanValidator;
import com.jack.util.IpUtil;
import com.jack.util.LevelUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class SysAclModuleService {
    @Autowired
    private SysAclModuleMapper sysAclModuleMapper;

    public void save(AclModuleParam param) {
        BeanValidator.check(param);
        if (checkExist(param.getParent_id(), param.getName(), param.getId())) {
            throw new ParamException("同一层级下存在相同名称的权限模块");
        }
        SysAclModule aclModule = SysAclModule.builder().name(param.getName())
                .parent_id(param.getParent_id())
                .seq(param.getSeq())
                .status(param.getStatus())
                .remark(param.getRemark())
                .build();
        aclModule.setLevel(LevelUtil.calculateLevel(getLevel(param.getParent_id()), param.getParent_id()));
        aclModule.setOperator(RequestHolder.getCurrentUser().getUsername());
        aclModule.setOperate_ip(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        aclModule.setOperate_time(new Date());

        sysAclModuleMapper.insertSelective(aclModule);

    }

    public void update(AclModuleParam param) {
        BeanValidator.check(param);
        if (checkExist(param.getParent_id(), param.getName(), param.getId())) {
            throw new ParamException("同一层级下存在相同名称的权限模块");
        }
        SysAclModule before = sysAclModuleMapper.selectByPrimaryKey(param.getId());
        Preconditions.checkNotNull(before, "待更新的权限模块不存在");
        SysAclModule after = SysAclModule.builder().id(param.getId()).name(param.getName())
                .parent_id(param.getParent_id())
                .seq(param.getSeq())
                .status(param.getStatus())
                .remark(param.getRemark())
                .build();
        after.setLevel(LevelUtil.calculateLevel(getLevel(param.getParent_id()), param.getParent_id()));
        after.setOperator(RequestHolder.getCurrentUser().getUsername());
        after.setOperate_ip(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        after.setOperate_time(new Date());

        //更新
        updateWithChild(before, after);
    }




    @Transactional
    //递归更新子权限
    private void updateWithChild(SysAclModule before, SysAclModule after) {
        String newLevelPrefix = after.getLevel();
        String oldLevelPrefix = before.getLevel();
        if (!after.getLevel().equals(before.getLevel())) {
            //子部门的更新
            List<SysAclModule> sysAclModuleList = sysAclModuleMapper.getChildAclModuleListByLevel(before.getLevel());
            if (CollectionUtils.isNotEmpty(sysAclModuleList)) {
                for (SysAclModule sysAclModule : sysAclModuleList) {
                    String level = sysAclModule.getLevel();
                    if (level.indexOf(oldLevelPrefix) == 0) {
                        level = newLevelPrefix + level.substring(oldLevelPrefix.length());
                        sysAclModule.setLevel(level);
                    }
                    sysAclModuleMapper.updateByPrimaryKey(sysAclModule);
                }
            }
        }
        sysAclModuleMapper.updateByPrimaryKeySelective(after);
    }

    private boolean checkExist(Integer parentId, String aclModuleName, Integer aclModuleId) {
        return sysAclModuleMapper.countByNameAndParentId(parentId, aclModuleName, aclModuleId) > 0;
    }

    private String getLevel(Integer aclModuleId) {
        SysAclModule sysAclModule = sysAclModuleMapper.selectByPrimaryKey(aclModuleId);
        if (sysAclModule == null) {
            return null;
        }
        return sysAclModule.getLevel();
    }
}
