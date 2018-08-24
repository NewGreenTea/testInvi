package com.shiro.Realm;

import com.example.Service.ShiroService.StudentService;
import com.example.Service.ShiroService.permissionService;
import com.example.Service.ShiroService.roleService;
import com.example.model.student;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

//Realm验证

public class MyRealm extends AuthorizingRealm{
    @Autowired    //有可能会报错，spring加载顺序（注意）
    private StudentService studentService;
    //@Autowired
    //private roleService roleservice;

    //获取授权信息
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("————权限认证方法————");
        String userName=(String)SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();

        String roleName=studentService.getRoleName(userName);
        //Integer useID=studentService.getUseID(username);   //先从用户名得到用户的id
        //List<String> roleList=studentService.getAllRole(userName的id);  //查询用户和角色表联合表（关系表）

        //String permission= //数据库取得权限，从角色和权限联合表查
        Set<String> set=new HashSet<>();
        set.add(roleName);
        info.setRoles(set);
        System.out.println("***********"+info.getRoles());
        //info.setStringPermissions(set);  //权限集合

        return info;
    }

    /*获取身份证信息
     * Shiro中，最终是通过 Realm 来获取应用程序中的用户、角色及权限信息的。
     *
     * @param authenticationToken 用户身份信息 token
     * @return 返回封装了用户信息的 AuthenticationInfo 实例
     * 实际上这个authcToken是从LoginController里面currentUser.login(token)传过来的
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("————身份认证方法————");
        UsernamePasswordToken token=(UsernamePasswordToken)authenticationToken;

        String password=studentService.getPassword(token.getUsername());

        if(null == password){
            System.out.println("用户名不正确！");
            //抛出自定义错误
            //todo
        }else if(!password.equals(new String((char[])token.getCredentials()))){
            System.out.println(password+"密码不正确！");
            //抛出自定义错误
            //todo
        }

        SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(
            token.getPrincipal(), password, getName()  //三个参数
                );
        return info;
    }

    /*
        principal 身份(用户名，邮箱，手机号)，credential 证明（密码，数字证书）
        token就像一个通行证，已经有你的信息
        token.getUsername()  //获得用户名 String
        token.getPrincipal() //获得用户名 Object
        token.getPassword()  //获得密码 char[]
        token.getCredentials() //获得密码 Object
     */
}
