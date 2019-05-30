package cn.wolfcode.rbac.service;

import cn.wolfcode.rbac.domain.Employee;
import cn.wolfcode.rbac.query.PageResult;
import cn.wolfcode.rbac.query.QueryObject;
import cn.wolfcode.rbac.query.RoleRelation;

import java.util.List;

/**
 * Created by Albert on 2019/5/27.
 */
public interface IEmployeeService {
    void saveOrUpdate(Employee entity);
    void delete(Long id);
    Employee get(Long id);
    List<Employee> listAll();

    PageResult<Employee> query(QueryObject qo);

    int insertIntoEmployeeRole(RoleRelation rl);

    int deletFromEmployeeRole(Long id);

    void login(String username, String password);
}
