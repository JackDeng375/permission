package com.jack.model;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table
public class SysRole implements Serializable {
    @Column
    private Integer id;
    @Column
    private String name;
    @Column
    private Integer type;
    @Column
    private Integer status;
    @Column
    private String remark;
    @Column
    private String operator;
    @Column
    private Date operate_time;
    @Column
    private String operate_ip;

    public SysRole(Integer id, String name, Integer type, Integer status, String remark, String operator, Date operate_time, String operate_ip) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.status = status;
        this.remark = remark;
        this.operator = operator;
        this.operate_time = operate_time;
        this.operate_ip = operate_ip;
    }

    public SysRole() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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