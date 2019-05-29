package cn.wolfcode.rbac.web.controller;

import cn.wolfcode.rbac.domain.Department;
import cn.wolfcode.rbac.query.QueryObject;
import cn.wolfcode.rbac.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Albert on 2019/5/27.
 */
@Controller
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private IDepartmentService service;

    @RequestMapping("/list")
    public String list(Model model, QueryObject qo){
        System.out.println(qo);
        model.addAttribute("result",service.query(qo));
        return "department/list";
    }

    @RequestMapping("/delete")
    public String delete(Model model,Long id){
        if (id != null){
            service.delete(id);
        }
        return "redirect:/department/list.do";
    }

    @RequestMapping("/input")
    public String input(Model model,Long id){
        if(id != null){
            Department department = service.get(id);
            model.addAttribute("d",department);
        }
        return "department/input";
    }

    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Model model,Department department){
       service.saveOrUpdate(department);
       return "redirect:/department/list.do";
    }
}
