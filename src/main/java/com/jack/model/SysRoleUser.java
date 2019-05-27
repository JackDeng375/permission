package com.jack.model;

import java.io.Serializable;
import java.util.Date;

public class SysRoleUser implements Serializable {
    private Integer id;

    private Integer role_id;

    private Integer user_id;

    private String operator;

    private Date operate_time;

    private String operate_ip;

    public SysRoleUser(Integer id, Integer role_id, Integer user_id, String operator, Date operate_time, String operate_ip) {
        this.id = id;
        this.role_id = role_id;
        this.user_id = user_id;
        this.operator = operator;
        this.operate_time = operate_time;
        this.operate_ip = operate_ip;
    }

    public SysRoleUser() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
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