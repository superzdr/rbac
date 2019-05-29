package cn.wolfcode.rbac.service.impl;

import cn.wolfcode.rbac.domain.Role;
import cn.wolfcode.rbac.mapper.RoleMapper;
import cn.wolfcode.rbac.mapper.RoleMapper;
import cn.wolfcode.rbac.query.PageResult;
import cn.wolfcode.rbac.query.QueryObject;
import cn.wolfcode.rbac.service.IRoleService;
import cn.wolfcode.rbac.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Albert on 2019/5/27.
 */
@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleMapper mapper;
    @Override
    public void saveOrUpdate(Role entity) {
        if(entity.getId() == null){
            mapper.insert(entity);
        }else{
            mapper.updateByPrimaryKey(entity);
        }
    }

    @Override
    public void delete(Long id) {
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public Role get(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Role> listAll() {
        return mapper.selectAll();
    }

    @Override
    public PageResult<Role> query(QueryObject qo) {
        //totalpage
        System.out.println("in query");
        System.out.println(qo);
        int count =mapper.queryForCount(qo);
        System.out.println(count);

        //totalpage !=0 pageResult
        if(count == 0){
            return new PageResult<Role>(qo.getCurrentPage(),qo.getPageSize());
        }

        List<Role> list = mapper.queryForList(qo);
        return new PageResult<Role>(list,qo.getCurrentPage(),qo.getPageSize(),count);
    }
}
