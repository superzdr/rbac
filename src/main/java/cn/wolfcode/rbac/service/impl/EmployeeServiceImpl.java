package cn.wolfcode.rbac.service.impl;

import cn.wolfcode.rbac.domain.Employee;
import cn.wolfcode.rbac.mapper.EmployeeMapper;
import cn.wolfcode.rbac.query.PageResult;
import cn.wolfcode.rbac.query.QueryObject;
import cn.wolfcode.rbac.query.RoleRelation;
import cn.wolfcode.rbac.service.IEmployeeService;
import cn.wolfcode.rbac.service.IEmployeeService;
import cn.wolfcode.rbac.util.LogicException;
import cn.wolfcode.rbac.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Albert on 2019/5/27.
 */
@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    private EmployeeMapper mapper;
    @Override
    public void saveOrUpdate(Employee entity) {
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
    public Employee get(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Employee> listAll() {
        return mapper.selectAll();
    }

    @Override
    public PageResult<Employee> query(QueryObject qo) {
        //totalpage
        System.out.println("in query");
        System.out.println(qo);
        int count =mapper.queryForCount(qo);
        System.out.println(count);

        //totalpage !=0 pageResult
        if(count == 0){
            return new PageResult<Employee>(qo.getCurrentPage(),qo.getPageSize());
        }

        List<Employee> list = mapper.queryForList(qo);
        return new PageResult<Employee>(list,qo.getCurrentPage(),qo.getPageSize(),count);
    }

    @Override
    public int insertIntoEmployeeRole(RoleRelation rl) {
        return mapper.insertIntoEmployeeRole(rl);
    }

    @Override
    public int deletFromEmployeeRole(Long id) {
        return mapper.deletFromEmployeeRole(id);
    }

    @Override
    public void login(String username, String password) {
        Employee employee = mapper.selectByUsenameAndPassword(username, password);
        //System.out.println(mapper.selectExpressionsByEmployeeId(employee.getId()));
        if(employee == null){
            throw new LogicException("用户名或密码错误");
        }
       /* ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpSession session = requestAttributes.getRequest().getSession();*/
        HttpSession session = UserContext.getSession(); //使用重构后的方法
        //session.setAttribute("EMPLOYEE_IN_SESSION",employee);
        UserContext.setEmployeeSession(employee);
        //session.setAttribute("EXPRESSION_IN_SESSION",mapper.selectExpressionsByEmployeeId(employee.getId()));
        UserContext.setExpressionSession(mapper,employee);
    }
}
