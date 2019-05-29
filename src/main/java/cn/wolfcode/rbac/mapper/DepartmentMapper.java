package cn.wolfcode.rbac.mapper;

import cn.wolfcode.rbac.domain.Department;
import cn.wolfcode.rbac.query.QueryObject;

import java.util.List;

public interface DepartmentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Department record);

    Department selectByPrimaryKey(Long id);

    List<Department> selectAll();

    int updateByPrimaryKey(Department record);

    Integer queryForCount(QueryObject qo);

    List<Department> queryForList(QueryObject qo);
}