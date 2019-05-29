package cn.wolfcode.rbac.service;

import cn.wolfcode.rbac.domain.Permission;
import cn.wolfcode.rbac.query.PageResult;
import cn.wolfcode.rbac.query.QueryObject;

import java.util.List;

/**
 * Created by Albert on 2019/5/27.
 */
public interface IPermissionService {

    void delete(Long id);

    List<Permission> listAll();

    PageResult<Permission> query(QueryObject qo);
}
