package cn.wolfcode.rbac.web.controller;

import cn.wolfcode.rbac.domain.Role;
import cn.wolfcode.rbac.query.QueryObject;
import cn.wolfcode.rbac.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Albert on 2019/5/27.
 */
@Controller
@RequestMapping("/role")
public class PermissionController {
    @Autowired
    private IRoleService service;

    @RequestMapping("/list")
    public String list(Model model, QueryObject qo){
        System.out.println(qo);
        model.addAttribute("result",service.query(qo));
        return "role/list";
    }

    @RequestMapping("/delete")
    public String delete(Model model,Long id){
        if (id != null){
            service.delete(id);
        }
        return "redirect:/role/list.do";
    }

    @RequestMapping("/input")
    public String input(Model model,Long id){
        if(id != null){
            Role role = service.get(id);
            model.addAttribute("d",role);
        }
        return "role/input";
    }

    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Model model,Role role){
       service.saveOrUpdate(role);
       return "redirect:/role/list.do";
    }
}
