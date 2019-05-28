package com.jack.dao;

import com.jack.model.SysDept;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysDeptMapper extends Mapper<SysDept> {


    List<SysDept> getChildDeptListByLevel(@Param("level") String level);

    void batchUpdateLevel(@Param("sysDeptList") List<SysDept> sysDeptList);

    int countByNameAndParentId(@Param("parentId") Integer parentId, @Param("name") String name,@Param("id") Integer id);
}