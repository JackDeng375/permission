package com.jack.param;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Getter
@Setter
@ToString
public class AclParam {

    private Integer id;
    @NotBlank(message = "权限点名称不能为空")
    @Length(min = 2, max = 20, message = "权限点名称长度需要在2~20个字之间")
    private String name;

    private Integer acl_module_id;
    private String url;
    private Integer type;
    private Integer status;
    private Integer seq;
    private String remark;
}
