package gxf.dev.biz.admin.controller;

import gxf.dev.biz.admin.config.SystemConfig;
import gxf.dev.biz.admin.dto.ResultDto;
import gxf.dev.biz.admin.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gongxufan on 2017/11/16.
 */
@Controller
public class HomeController {

    @RequestMapping(value = "/normalLogin", method = RequestMethod.POST)
    public String normalLogin(HttpServletRequest request, User user, Model model) {
        if (StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())) {
            request.setAttribute("msg", "用户名或密码不能为空！");
            return "login";
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        try {
            subject.login(token);
            return "redirect:usersPage";
        } catch (LockedAccountException lae) {
            token.clear();
            request.setAttribute("msg", "用户已经被锁定不能登录，请与管理员联系！");
            return "login";
        } catch (AuthenticationException e) {
            token.clear();
            request.setAttribute("msg", "用户或密码不正确！");
            return "login";
        }
    }


    @RequestMapping(value = "/ajaxLogin", method = RequestMethod.POST)
    @ResponseBody
    public Object ajaxLogin(HttpServletRequest request, @RequestBody User user, Model model) {
        if (StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())) {
            return ResultDto.fail("用户名或密码不能为空！");
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        try {
            subject.login(token);
            ResultDto resultDto = ResultDto.ok("登录成功，正在跳转中...");
            Map<String,String> map = new HashMap<>();
            map.put("url", SystemConfig.domian);
            resultDto.setData(map);
            return resultDto;
        } catch (LockedAccountException lae) {
            return ResultDto.fail("用户已经被锁定不能登录，请与管理员联系！");
        } catch (AuthenticationException e) {
            return ResultDto.fail("用户或密码不正确！");
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public Object logout(HttpServletRequest request, User user, Model model) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return ResultDto.ok("注销成功");
    }
    @RequestMapping(value = {"/usersPage", ""})
    public String usersPage() {
        return "user/user";
    }

    @RequestMapping("/rolesPage")
    public String rolesPage() {
        return "role/roles";
    }

    @RequestMapping("/resourcesPage")
    public String resourcesPage() {
        return "resources/resources";
    }

    @RequestMapping("/iconPage")
    public String iconPage() {
        return "iocn/iocn";
    }

}
