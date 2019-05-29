package cn.wolfcode.rbac.service;

import cn.wolfcode.rbac.domain.Department;
import cn.wolfcode.rbac.query.PageResult;
import cn.wolfcode.rbac.query.QueryObject;

import java.util.List;

/**
 * Created by Albert on 2019/5/27.
 */
public interface IDepartmentService {
    void saveOrUpdate(Department entity);
    void delete(Long id);
    Department get(Long id);
    List<Department> listAll();

    PageResult<Department> query(QueryObject qo);
}
