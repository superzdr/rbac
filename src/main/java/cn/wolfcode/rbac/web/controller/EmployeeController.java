package cn.wolfcode.rbac.web.controller;

import cn.wolfcode.rbac.domain.Employee;
import cn.wolfcode.rbac.query.EmployeeQueryObject;
import cn.wolfcode.rbac.query.RoleRelation;
import cn.wolfcode.rbac.service.IDepartmentService;
import cn.wolfcode.rbac.service.IEmployeeService;
import cn.wolfcode.rbac.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

/**
 * Created by Albert on 2019/5/27.
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private IEmployeeService service;

    @Autowired
    private IDepartmentService departmentService;

    @Autowired
    private IRoleService roleService;

    @RequestMapping("/list")
    public String list(Model model,@ModelAttribute("qo") EmployeeQueryObject qo){
        //System.out.println(qo);
        //System.out.println(departmentService.listAll());
        model.addAttribute("depts",departmentService.listAll());
        model.addAttribute("result",service.query(qo));
        return "employee/list";
    }

    @RequestMapping("/delete")
    public String delete(Model model,Long id){
        if (id != null){
            service.deletFromEmployeeRole(id);
            service.delete(id);
        }
        return "redirect:/employee/list.do";
    }

    @RequestMapping("/input")
    public String input(Model model,Long id){
        if(id != null){
            Employee employee = service.get(id);
            System.out.println(employee.getRoles());
            System.out.println("-------------------------------------");
            model.addAttribute("entity",employee);
        }
        model.addAttribute("depts",departmentService.listAll());
        model.addAttribute("roles",roleService.listAll());
        return "employee/input";
    }

    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Model model,Employee employee,Long[] ids){
        System.out.println(employee);
        System.out.println(Arrays.toString(ids));
        service.saveOrUpdate(employee);

        if(employee.isAdmin()){
            service.deletFromEmployeeRole(employee.getId());
            return "redirect:/employee/list.do";
        }

        if(employee.getId() != null){
            service.deletFromEmployeeRole(employee.getId());
            for (Long rid:ids) {
                service.insertIntoEmployeeRole(new RoleRelation(employee.getId(),rid));
            }
        }else{
            for (Long rid:ids) {
                service.insertIntoEmployeeRole(new RoleRelation(employee.getId(),rid));
            }
        }

        return "redirect:/employee/list.do";
    }
}
