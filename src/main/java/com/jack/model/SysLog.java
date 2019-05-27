package com.jack.model;

import java.io.Serializable;
import java.util.Date;

public class SysLog implements Serializable {
    private Integer id;

    private Integer type;

    private Integer target_id;

    private String operator;

    private Date operate_time;

    private String operate_ip;

    private Integer status;

    public SysLog(Integer id, Integer type, Integer target_id, String operator, Date operate_time, String operate_ip, Integer status) {
        this.id = id;
        this.type = type;
        this.target_id = target_id;
        this.operator = operator;
        this.operate_time = operate_time;
        this.operate_ip = operate_ip;
        this.status = status;
    }

    public SysLog() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getTarget_id() {
        return target_id;
    }

    public void setTarget_id(Integer target_id) {
        this.target_id = target_id;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}