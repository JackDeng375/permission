package com.jack.service;

import com.jack.dao.SysDeptMapper;
import com.jack.exception.ParamException;
import com.jack.model.SysDept;
import com.jack.param.DeptParam;
import com.jack.util.BeanValidator;
import com.jack.util.LevelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SysDeptService {
    @Autowired
    private SysDeptMapper sysDeptMapper;

    public void save(DeptParam param) {
        BeanValidator.check(param);
        if (checkExist(param.getParentId(), param.getName(), param.getId())) {
            throw new ParamException("同一层级下存在相同的名称的部门");
        }
        SysDept dept = SysDept.builder().name(param.getName()).parent_id(param.getParentId())
                .seq(param.getSeq()).remark(param.getRemark()).build();
        dept.setLevel(LevelUtil.calculateLevel(getLevel(param.getParentId()), param.getParentId()));
        //TODO
        dept.setOperator("system");
        //TODO
        dept.setOperate_ip("127.0.0.1");
        dept.setOperate_time(new Date());

        sysDeptMapper.insert(dept);
    }

    //判断部门名不能相同
    private boolean checkExist(Integer parentId, String deptName, Integer deptId) {
        //TODO:
        return true;
    }

    private String getLevel(Integer deptId) {
        SysDept dept = sysDeptMapper.selectByPrimaryKey(deptId);
        if (dept == null) {
            return null;
        }
        return dept.getLevel();
    }
}
