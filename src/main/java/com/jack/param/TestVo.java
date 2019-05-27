package com.jack.param;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@ToString
public class TestVo {

    //字符串判断为空
    @NotBlank(message = "msg不能为空")
    private String msg;

    //Integer判断为null
    @NotNull(message = "id不能为Null")
    private Integer id;
}
