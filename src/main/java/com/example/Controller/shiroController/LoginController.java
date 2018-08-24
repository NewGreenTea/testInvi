package com.example.Controller.shiroController;

import com.example.Service.ShiroService.StudentService;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {
    @Autowired
    private StudentService ss;

//    @PostMapping("/login")
//    public String  login(@RequestParam("username")String uName,@RequestParam("password")String password){
//        Subject subject= SecurityUtils.getSubject();
//        //System.out.println(uName+"********"+password);
//        UsernamePasswordToken token=new UsernamePasswordToken(uName,password);
//        subject.login(token);
//        String role=ss.getRoleName(uName);
//        if("teacher".equals(role)){
//            return "欢迎登陆";
//        }else if("admin".equals(role)){
//            return "欢迎管理员登陆";
//        }
//        return "权限错误";
//    }

    //测试登录后跳转
    @PostMapping("/login")
    public String  login(@RequestParam("username")String uName,@RequestParam("password")String password){
        Subject subject= SecurityUtils.getSubject();
        //System.out.println(uName+"********"+password);
        Session session=subject.getSession();
        session.getTimeout();//获得会话的过期时间，单位毫秒
        System.out.println(session.getId()+"*******"+session.getHost()+"*****");

        UsernamePasswordToken token=new UsernamePasswordToken(uName,password);
        subject.login(token);
        //String role=ss.getRoleName(uName);
        return "manger";
    }

    @RequestMapping(value = "/notCount", method = RequestMethod.GET)
    @ResponseBody
    public String notLogin() {
        return "您尚未登陆！";
    }

    @RequestMapping(value = "/notRole", method = RequestMethod.GET)
    @ResponseBody
    public String notRole() {
        return "您没有权限！";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        //注销
        subject.logout();
        return "成功注销！";
    }

//    @RequiresAuthentication     //表示当前 Subject 已经通过 login 进行了身份验证；即 Subject.isAuthenticated() 返回 true。
//    @RequiresGuest              //表示当前 Subject 没有身份验证或通过记住我登录过，即是游客身份。
//    @RequiresPermissions()      //表示当前 Subject 需要权限 user：a 或 user：b
//    @RequiresRoles()            //表示当前 Subject 需要角色 admin 和 user
//    @RequiresUser               //表示当前 Subject 已经身份验证或者通过记住我登录的

    @RequiresRoles(value = "admin")
    @RequestMapping("/adminRole")
    @ResponseBody
    public String adminrole(){
        return "确认过眼神，是管理员！";
    }


}
