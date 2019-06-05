package com.jack.test;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jack.dao.SysDeptMapper;
import com.jack.dto.DeptLevelDto;
import com.jack.model.SysDept;
import com.jack.service.SysTreeService;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/applicationContext.xml"})
public class TestRun extends TestCase {

    @Autowired
    private SysDeptMapper sysDeptMapper;
    @Autowired
    private SysTreeService sysTreeService;

    @Test
    public void test02() {
        List<DeptLevelDto> deptLevelDtos = sysTreeService.deptTree();
        System.out.println(deptLevelDtos);
    }

    @Test
    public void test01() {
        List<SysDept> deptList = sysDeptMapper.selectAll();
        Map<String, List<SysDept>> maps = Maps.newHashMap();

        List<SysDept> depts = new ArrayList<>();
        //定义一个变量用来保存前一个部门的名称
        String prefixName = "";
        Integer parentId = 0;
        for (SysDept sysDept : deptList) {
           if(sysDept.getParent_id() == 0) {
               //说明是顶部的部门
               depts.add(sysDept);
               prefixName = sysDept.getName();
               parentId = sysDept.getId();
               maps.put(sysDept.getName(),depts);
           } else if (sysDept.getParent_id() != 0 && sysDept.getParent_id() == parentId) {
               //说明属于当前顶部的名称
               maps.get(prefixName).add(sysDept);
           }

        }
        System.out.println(maps);
    }
}
