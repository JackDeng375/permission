package com.jack.service;

import com.google.common.base.Preconditions;
import com.jack.bean.PageQuery;
import com.jack.bean.PageResult;
import com.jack.common.RequestHolder;
import com.jack.dao.SysUserMapper;
import com.jack.exception.ParamException;
import com.jack.model.SysUser;
import com.jack.param.UserParam;
import com.jack.util.BeanValidator;
import com.jack.util.IpUtil;
import com.jack.util.MailUtil;
import com.jack.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

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
        //当前用户
        sysUser.setOperator(RequestHolder.getCurrentUser().getUsername());
        //ip地址
        sysUser.setOperate_ip(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        sysUser.setOperate_time(new Date());

        //TODO:发送邮箱，将用户的密码发送给用户
        //封装Mail对象，调用MailUtil.send(mail对象);
        //MailUtil.send();
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
        after.setOperator(RequestHolder.getCurrentUser().getUsername());
        after.setOperate_ip(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        after.setOperate_time(new Date());

        sysUserMapper.updateByPrimaryKeySelective(after);
    }

    /**
     * 检查邮箱是否存在
     * @param maill
     * @param userId
     * @return
     */
    public boolean checkEmailExists(String maill, Integer userId) {
        return sysUserMapper.countByMail(maill, userId) > 0;
    }

    public boolean checkTelephoneExists(String telephone, Integer userId) {
        return sysUserMapper.countByTelephone(telephone, userId) > 0;
    }


    public SysUser findByKeyword(String keyword) {
        return sysUserMapper.findByKeyword(keyword);
    }


    public PageResult<SysUser> getPageByDeptId(int deptId, PageQuery page) {
        BeanValidator.check(page);
        int count = sysUserMapper.countByDeptId(deptId);
        if (count > 0) {
            //获取实际的列表
            List<SysUser> userList = sysUserMapper.getPageByDeptId(deptId, page);
            return PageResult.<SysUser>builder().total(count).data(userList).build();
        }
        return PageResult.<SysUser>builder().build();
    }

}
