package com.jack.service;

import com.google.common.base.Preconditions;
import com.jack.dao.SysDeptMapper;
import com.jack.exception.ParamException;
import com.jack.model.SysDept;
import com.jack.param.DeptParam;
import com.jack.util.BeanValidator;
import com.jack.util.LevelUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class SysDeptService {
    @Autowired
    private SysDeptMapper sysDeptMapper;

    public void save(DeptParam param) {
        BeanValidator.check(param);
        if (checkExist(param.getParentId(), param.getName(), param.getId())) {
            throw new ParamException("同一层级下存在相同的名称的部门");
        }

        //在当前类中编写@Builder注解
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

    public void update(DeptParam param) {
        BeanValidator.check(param);
        if (checkExist(param.getParentId(), param.getName(), param.getId())) {
            throw new ParamException("同一层级下存在相同名称的部门");
        }
        SysDept before = sysDeptMapper.selectByPrimaryKey(param.getId());
        Preconditions.checkNotNull(before, "待更新的部门不存在");
        SysDept after = SysDept.builder().id(param.getId()).name(param.getName()).parent_id(param.getParentId())
                .seq(param.getSeq()).remark(param.getRemark()).build();
        after.setLevel(LevelUtil.calculateLevel(getLevel(param.getParentId()),param.getParentId()));
        after.setOperator("system");
        after.setOperate_ip("127.0.0.1");
        after.setOperate_time(new Date());

        updateWithChild(before, after);
    }

    @Transactional
    private void updateWithChild(SysDept before, SysDept after) {
        String newLevelPrefix = after.getLevel();
        String oldLevelPrefix = before.getLevel();
        if (!after.getLevel().equals(before.getLevel())) {
            //子部门的更新
            List<SysDept> deptList = sysDeptMapper.getChildDeptListByLevel(before.getLevel());
            if (CollectionUtils.isNotEmpty(deptList)) {
                for (SysDept dept : deptList) {
                    String level = dept.getLevel();
                    if (level.indexOf(oldLevelPrefix) == 0) {
                        level = newLevelPrefix + level.substring(oldLevelPrefix.length());
                        dept.setLevel(level);
                    }
                    sysDeptMapper.updateByPrimaryKey(dept);
                }
                //sysDeptMapper.batchUpdateLevel(deptList);
            }
        }
        sysDeptMapper.updateByPrimaryKey(after);
    }

    //判断部门名不能相同
    private boolean checkExist(Integer parentId, String deptName, Integer deptId) {
        //TODO:
        return sysDeptMapper.countByNameAndParentId(parentId, deptName, deptId) > 0;
    }

    private String getLevel(Integer deptId) {
        SysDept dept = sysDeptMapper.selectByPrimaryKey(deptId);
        if (dept == null) {
            return null;
        }
        return dept.getLevel();
    }
}
