package cn.wolfcode.rbac.mapper;

import cn.wolfcode.rbac.domain.Employee;
import cn.wolfcode.rbac.query.QueryObject;
import cn.wolfcode.rbac.query.RoleRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee record);

    Integer queryForCount(QueryObject qo);

    List<Employee> queryForList(QueryObject qo);

    int insertIntoEmployeeRole(RoleRelation rl);

    int deletFromEmployeeRole(Long id);

    int deleteFromEmployeeRoleByRole(Long id);

    Employee selectByUsenameAndPassword(@Param("username") String username, @Param("password") String password);

    List<String> selectExpressionsByEmployeeId(Long id);
}