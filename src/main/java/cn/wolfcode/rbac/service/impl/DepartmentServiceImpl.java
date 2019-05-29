package cn.wolfcode.rbac.service.impl;

import cn.wolfcode.rbac.domain.Department;
import cn.wolfcode.rbac.mapper.DepartmentMapper;
import cn.wolfcode.rbac.query.PageResult;
import cn.wolfcode.rbac.query.QueryObject;
import cn.wolfcode.rbac.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Albert on 2019/5/27.
 */
@Service
public class DepartmentServiceImpl implements IDepartmentService {
    @Autowired
    private DepartmentMapper mapper;
    @Override
    public void saveOrUpdate(Department entity) {
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
    public Department get(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Department> listAll() {
        return mapper.selectAll();
    }

    @Override
    public PageResult<Department> query(QueryObject qo) {
        //totalpage
        System.out.println("in query");
        System.out.println(qo);
        int count =mapper.queryForCount(qo);
        System.out.println(count);

        //totalpage !=0 pageResult
        if(count == 0){
            return new PageResult<Department>(qo.getCurrentPage(),qo.getPageSize());
        }

        List<Department> list = mapper.queryForList(qo);
        return new PageResult<Department>(list,qo.getCurrentPage(),qo.getPageSize(),count);
    }
}
