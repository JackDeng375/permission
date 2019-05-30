package com.jack.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table
public class SysAcl implements Serializable {
    @Id
    private Integer id;
    @Column
    private String code;
    @Column
    private String name;
    @Column
    private Integer acl_module_id;
    @Column
    private String url;
    @Column
    private Integer type;
    @Column
    private Integer status;
    @Column
    private Integer seq;
    @Column
    private String remark;
    @Column
    private String operator;
    @Column
    private Date operate_time;
    @Column
    private String operate_ip;

    public SysAcl(Integer id, String code, String name, Integer acl_module_id, String url, Integer type, Integer status, Integer seq, String remark, String operator, Date operate_time, String operate_ip) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.acl_module_id = acl_module_id;
        this.url = url;
        this.type = type;
        this.status = status;
        this.seq = seq;
        this.remark = remark;
        this.operator = operator;
        this.operate_time = operate_time;
        this.operate_ip = operate_ip;
    }

    public SysAcl() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAcl_module_id() {
        return acl_module_id;
    }

    public void setAcl_module_id(Integer acl_module_id) {
        this.acl_module_id = acl_module_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
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

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
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