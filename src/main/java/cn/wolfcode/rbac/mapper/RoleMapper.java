package cn.wolfcode.rbac.mapper;

import cn.wolfcode.rbac.domain.Department;
import cn.wolfcode.rbac.domain.Role;
import cn.wolfcode.rbac.query.QueryObject;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    Role selectByPrimaryKey(Long id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

    Integer queryForCount(QueryObject qo);

    List<Role> queryForList(QueryObject qo);
}