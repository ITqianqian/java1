package com.zqn.test;


import com.zqn.proxy.*;
import com.zqn.service.UserService;
import net.sf.cglib.proxy.Enhancer;
import org.junit.Test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.Proxy;

/**
 * Created by dell on 2017/1/9.
 */

public class SpringTestCase {
 /*   @Test
    public void load() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        UserService userService = (UserService) applicationContext.getBean("userServiceImpl");
        userService.save();
    }
   @Test
   public void save(){
       ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

       UserService userService = (UserService) applicationContext.getBean("userService");

       userService.save();

   }
   @Test
   public void jdkProxy(){
       SubjectImpl subjectImpl = new SubjectImpl();

       SubjectInvocationHandler subjectInvocationHandler= new SubjectInvocationHandler(subjectImpl);

       Subject subject = (Subject) Proxy.newProxyInstance(
               subjectImpl.getClass().getClassLoader(),//目标对象的classloader
               subjectImpl.getClass().getInterfaces(),//目标对象所拥有的接口
               subjectInvocationHandler);//代理对象的模版
       subject.save();
   }
   @Test
   public void cglibProxy(){
       Enhancer enhancer = new Enhancer();
       enhancer.setCallback(new TargetM());

       enhancer.setSuperclass(Target.class);
       Target target = (Target) enhancer.create();
       target.save();
       target.update();

   }*/


}