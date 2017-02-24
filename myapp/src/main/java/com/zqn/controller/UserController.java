package com.zqn.controller;

import com.zqn.pojo.Roles;
import com.zqn.pojo.User;
import com.zqn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.List;

/**
 * Created by dell on 2017/1/12.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/home")
    public String home(Model model){
        return "user/list";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model){
        List<User> userList = userService.findAllUser();
        model.addAttribute("userList",userList);
        return "user/list";

    }
    @RequestMapping(value = "/new",method = RequestMethod.GET)
    public String newUser(Model model){
        List<Roles> rolesList = userService.findAllRoles();
        model.addAttribute("rolesList",rolesList);
        return "user/new";
    }
    @RequestMapping(value = "/new",method = RequestMethod.POST)
    public  String newUser(User user, Integer[] rolesIds, RedirectAttributes redirectAttributes){
        userService.save(user);
        redirectAttributes.addAttribute("message","操作成功");
        return "redirect:/user";
    }



}
