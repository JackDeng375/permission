package com.jack.model;

import java.util.Date;

public class SysLogWithBLOBs extends SysLog {
    private String old_value;

    private String new_value;

    public SysLogWithBLOBs(Integer id, Integer type, Integer target_id, String operator, Date operate_time, String operate_ip, Integer status, String old_value, String new_value) {
        super(id, type, target_id, operator, operate_time, operate_ip, status);
        this.old_value = old_value;
        this.new_value = new_value;
    }

    public SysLogWithBLOBs() {
        super();
    }

    public String getOld_value() {
        return old_value;
    }

    public void setOld_value(String old_value) {
        this.old_value = old_value == null ? null : old_value.trim();
    }

    public String getNew_value() {
        return new_value;
    }

    public void setNew_value(String new_value) {
        this.new_value = new_value == null ? null : new_value.trim();
    }
}