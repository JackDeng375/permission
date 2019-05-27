package com.jack.controller;

import com.jack.common.ApplicationContextHelper;
import com.jack.common.JsonData;
import com.jack.dao.SysUserMapper;
import com.jack.exception.ParamException;
import com.jack.exception.PermissionException;
import com.jack.model.SysUser;
import com.jack.param.TestVo;
import com.jack.util.BeanValidator;
import com.jack.util.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class TestController {

    @RequestMapping("/hello.json")
    @ResponseBody
    public JsonData hello() {
        //return JsonData.success("hello json");
        throw new RuntimeException("hello exception");
    }

    @RequestMapping("/validate.json")
    @ResponseBody
    public JsonData validate(TestVo vo) throws ParamException {
        //使用自定义的上下文获取对象
        SysUserMapper sysUserMapper = ApplicationContextHelper.popBean(SysUserMapper.class);
        log.info("validate");
        BeanValidator.check(vo);
        return JsonData.success("test validate");
    }
}
