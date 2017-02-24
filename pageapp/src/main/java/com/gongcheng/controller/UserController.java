package com.gongcheng.controller;

import com.gongcheng.exception.NotFoundException;
import com.gongcheng.pojo.Role;
import com.gongcheng.pojo.User;
import com.gongcheng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Created by dell on 2017/2/16.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;




    @RequestMapping(method = RequestMethod.GET)
    public String list(/*@RequestParam(required = false,defaultValue = "1") Integer p */Model model){

        List<User> userlist=userService.findAll();
        for(User user:userlist){
            List<Role> roleList = userService.findRoleByUserId(user.getId());
            user.setRoleList(roleList);
        }

        model.addAttribute("userlist",userlist);

        return "user/list";
    }
    @RequestMapping(value = "/new",method = RequestMethod.GET)
    public String newUser(Model model){

        List<Role> roleList=userService.findAllRole();
        model.addAttribute("roleList",roleList);
        return "user/new";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String newUser(User user,Integer[] roleIds, RedirectAttributes redirectAttributes) {
        userService.saveNewUser(user,roleIds);
        redirectAttributes.addFlashAttribute("mesige","操作成功");
        return "redirect:/user";
    }
    @RequestMapping(value = "/{id:\\d+}/edit",method = RequestMethod.GET)
    public String editUser(@PathVariable Integer id,Model model){

        User user = userService.findUserById(id);
        List<Role> roleList = userService.findRoleByUserId(id);
        user.setRoleList(roleList);

        if(user==null){
            throw new NotFoundException();
        }else{
            List<Role> roles = userService.findAllRole();

            model.addAttribute("user",user);
            model.addAttribute("roles",roles);

        }


        return "user/edit";
    }

    @RequestMapping(value = "/{id:\\d+}/edit",method = RequestMethod.POST)
    public String editUser(User user,Integer[] roleIds,RedirectAttributes redirectAttributes) {
        userService.editUser(user,roleIds);
        redirectAttributes.addFlashAttribute("message","操作成功");
        return "redirect:/user";
    }


    @RequestMapping(value = "/{id:\\d+}/del",method = RequestMethod.GET)
    public String delUser(@PathVariable Integer id,Model model){
        userService.delUserById(id);
        return "redirect:/user";
    }

}

