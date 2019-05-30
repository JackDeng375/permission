package com.jack.service;

import com.google.common.base.Preconditions;
import com.jack.dao.SysUserMapper;
import com.jack.exception.ParamException;
import com.jack.model.SysUser;
import com.jack.param.UserParam;
import com.jack.util.BeanValidator;
import com.jack.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;

@Service
public class SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 保存
     */
    public void save(UserParam param) {
        BeanValidator.check(param);
        if (checkTelephoneExists(param.getTelephone(), param.getId())) {
            throw new ParamException("电话已被占用");
        }
        if (checkEmailExists(param.getMaill(), param.getId())) {
            throw new ParamException("邮箱已被占用");
        }

        //密码
        String password = PasswordUtil.randomPassword();

        //TODO:还没有发邮件，密码设置自己的给定的值
        password = "123";

        //MD5加密的密码
        String encryptedPassword = DigestUtils.md5DigestAsHex(password.getBytes());

        //创建User对象
        SysUser sysUser = SysUser.builder().username(param.getUsername())
                .telephone(param.getTelephone()).maill(param.getMaill())
                .password(encryptedPassword).dept_id(param.getDept_id()).status(param.getStatus())
                .remark(param.getRemark()).build();
        sysUser.setOperator("system");
        sysUser.setOperate_ip("127.0.0.1");
        sysUser.setOperate_time(new Date());

        //TODO: sendEmaill

        sysUserMapper.insertSelective(sysUser);
    }

    /**
     * 更新
     * @param param
     */
    public void update(UserParam param) {
        BeanValidator.check(param);
        if (checkTelephoneExists(param.getTelephone(), param.getId())) {
            throw new ParamException("电话已被占用");
        }
        if (checkEmailExists(param.getMaill(), param.getId())) {
            throw new ParamException("邮箱已被占用");
        }

        SysUser before = sysUserMapper.selectByPrimaryKey(param.getId());
        Preconditions.checkNotNull(before, "待更新的用户不存在");
        SysUser after = SysUser.builder().id(param.getId()).username(param.getUsername())
                .telephone(param.getTelephone()).maill(param.getMaill())
                .password(before.getPassword()).dept_id(param.getDept_id()).status(param.getStatus())
                .remark(param.getRemark()).build();
        sysUserMapper.updateByPrimaryKeySelective(after);
    }

    /**
     * 检查邮箱是否存在
     * @param emaill
     * @param userId
     * @return
     */
    public boolean checkEmailExists(String emaill, Integer userId) {
        return false;
    }

    public boolean checkTelephoneExists(String telephone, Integer userId) {
        return false;
    }

}
