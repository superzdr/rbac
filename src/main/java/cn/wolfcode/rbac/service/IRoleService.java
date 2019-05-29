package cn.wolfcode.rbac.service;

import cn.wolfcode.rbac.domain.Role;
import cn.wolfcode.rbac.query.PageResult;
import cn.wolfcode.rbac.query.QueryObject;

import java.util.List;

/**
 * Created by Albert on 2019/5/27.
 */
public interface IRoleService {
    void saveOrUpdate(Role entity);
    void delete(Long id);
    Role get(Long id);
    List<Role> listAll();

    PageResult<Role> query(QueryObject qo);
}
