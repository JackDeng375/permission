package com.jack.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table
@Getter
@Setter
public class SysLog implements Serializable {
    @Id
    private Integer id;
    @Column
    private Integer type;
    @Column
    private Integer target_id;
    @Column
    private String operator;
    @Column
    private Date operate_time;
    @Column
    private String operate_ip;
    @Column
    private Integer status;

    @Column
    private String old_value;
    @Column
    private String new_value;

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
}