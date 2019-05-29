package cn.wolfcode.rbac.mapper;

import cn.wolfcode.rbac.domain.Department;
import cn.wolfcode.rbac.domain.Role;
import cn.wolfcode.rbac.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    Role selectByPrimaryKey(Long id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

    Integer queryForCount(QueryObject qo);

    List<Role> queryForList(QueryObject qo);

    int deleteFromRolePermissionByRoleId(Long id);

    int insetIntoRolePermission(@Param("rid") Long rid,@Param("pid") Long pid);
}