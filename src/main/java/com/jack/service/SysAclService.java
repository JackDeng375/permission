package com.jack.service;

import com.google.common.base.Preconditions;
import com.jack.bean.PageQuery;
import com.jack.bean.PageResult;
import com.jack.common.RequestHolder;
import com.jack.dao.SysAclMapper;
import com.jack.exception.ParamException;
import com.jack.model.SysAcl;
import com.jack.param.AclParam;
import com.jack.util.BeanValidator;
import com.jack.util.IpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class SysAclService {
    @Autowired
    private SysAclMapper sysAclMapper;

    public void save(AclParam param) {
        BeanValidator.check(param);
        if (checkExist(param.getAcl_module_id(), param.getName(), param.getId())) {
            throw new ParamException("当前权限模块下面存在相同名称的权限点");
        }
        SysAcl acl = SysAcl.builder().name(param.getName()).acl_module_id(param.getAcl_module_id())
                .url(param.getUrl()).type(param.getType()).status(param.getStatus())
                .seq(param.getSeq()).remark(param.getRemark()).build();
        acl.setCode(generateCode());
        acl.setOperator(RequestHolder.getCurrentUser().getUsername());
        acl.setOperate_ip(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        acl.setOperate_time(new Date());

        sysAclMapper.insert(acl);
    }

    public void update(AclParam param) {
        BeanValidator.check(param);
        if (checkExist(param.getAcl_module_id(), param.getName(), param.getId())) {
            throw new ParamException("当前权限模块下面存在相同名称的权限点");
        }
        SysAcl before = sysAclMapper.selectByPrimaryKey(param.getId());
        Preconditions.checkNotNull(before, "待更新的权限点不存在");


        SysAcl after = SysAcl.builder().id(param.getId()).name(param.getName()).acl_module_id(param.getAcl_module_id())
                .url(param.getUrl()).type(param.getType()).status(param.getStatus())
                .seq(param.getSeq()).remark(param.getRemark()).build();
        after.setOperator(RequestHolder.getCurrentUser().getUsername());
        after.setOperate_ip(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        after.setOperate_time(new Date());

        sysAclMapper.updateByPrimaryKeySelective(after);

    }

    public boolean checkExist(int aclModuleId, String name, Integer id) {
        return sysAclMapper.countByNameAndAclModuleId(aclModuleId, name, id) > 0;
    }

    /**
     * 获取唯一编码
     */
    public String generateCode() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(new Date()) + "_" + (int)(Math.random()* 100);
    }

    public PageResult<SysAcl> getPageByAclModuleId(int aclModuleId, PageQuery page) {
        BeanValidator.check(page);

        int count = sysAclMapper.countByAclModuleId(aclModuleId);
        if (count > 0) {
            List<SysAcl> aclList = sysAclMapper.getPageByAclModuleId(aclModuleId, page);
            return PageResult.<SysAcl>builder().data(aclList).total(count).build();
        }

        return PageResult.<SysAcl>builder().build();

    }

}