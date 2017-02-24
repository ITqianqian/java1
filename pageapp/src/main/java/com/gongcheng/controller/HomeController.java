package com.gongcheng.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Created by dell on 2017/2/17.
 */
@Controller
public class HomeController {

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String login() {

        return "login";
    }
    @RequestMapping(value = "/home",method = RequestMethod.GET)
    public String home(){
        return "home";

    }
    @RequestMapping(value = "logout",method = RequestMethod.GET)
    public String logout(RedirectAttributes redirectAttributes){

        SecurityUtils.getSubject().logout();
        redirectAttributes.addFlashAttribute("message","您已安全退出");
        return "redirect:/";
    }

    @RequestMapping(value = "/",method = RequestMethod.POST)
    public String login(String userName, String password, RedirectAttributes redirectAttributes){
        //shiro 登录
       Subject subject =  SecurityUtils.getSubject();
       try {
           subject.login(new UsernamePasswordToken(userName, password));
           return "redirect:/home";
       }catch (AuthenticationException ex){
           ex.printStackTrace();
           redirectAttributes.addFlashAttribute("messige","帐号或密码错误");
           return "redirect:/";
       }

    }



}
