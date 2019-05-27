package com.jack.model;

import java.io.Serializable;
import java.util.Date;

public class SysUser implements Serializable {
    private Integer id;

    private String username;

    private String telephone;

    private String maill;

    private String password;

    private Integer dept_id;

    private Integer status;

    private String remark;

    private String operator;

    private Date operate_time;

    private String operate_ip;

    public SysUser(Integer id, String username, String telephone, String maill, String password, Integer dept_id, Integer status, String remark, String operator, Date operate_time, String operate_ip) {
        this.id = id;
        this.username = username;
        this.telephone = telephone;
        this.maill = maill;
        this.password = password;
        this.dept_id = dept_id;
        this.status = status;
        this.remark = remark;
        this.operator = operator;
        this.operate_time = operate_time;
        this.operate_ip = operate_ip;
    }

    public SysUser() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getMaill() {
        return maill;
    }

    public void setMaill(String maill) {
        this.maill = maill == null ? null : maill.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getDept_id() {
        return dept_id;
    }

    public void setDept_id(Integer dept_id) {
        this.dept_id = dept_id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public Date getOperate_time() {
        return operate_time;
    }

    public void setOperate_time(Date operate_time) {
        this.operate_time = operate_time;
    }

    public String getOperate_ip() {
        return operate_ip;
    }

    public void setOperate_ip(String operate_ip) {
        this.operate_ip = operate_ip == null ? null : operate_ip.trim();
    }
}