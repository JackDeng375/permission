package com.jack.controller;

import com.jack.bean.PageQuery;
import com.jack.bean.PageResult;
import com.jack.common.JsonData;
import com.jack.model.SysUser;
import com.jack.param.UserParam;
import com.jack.service.SysUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

//TODO: 8-3
@Controller
@RequestMapping("/sys/user")
public class SysUserController {
    @Resource
    private SysUserService userService;


    @RequestMapping("/save.json")
    @ResponseBody
    public JsonData saveUser(UserParam userParam) {
        userService.save(userParam);
        return JsonData.success();
    }

    @RequestMapping("/update.json")
    @ResponseBody
    public JsonData updateUser(UserParam userParam) {
        userService.update(userParam);
        return JsonData.success();
    }

    @RequestMapping("/page.json")
    @ResponseBody
    public JsonData page(@RequestParam("deptId") int deptId, PageQuery pageQuery) {
        PageResult<SysUser> result = userService.getPageByDeptId(deptId, pageQuery);
        return JsonData.success(result);
    }


}
