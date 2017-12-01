package gxf.dev.biz.admin.controller;

import com.github.pagehelper.PageInfo;
import gxf.dev.biz.admin.dto.ResultDto;
import gxf.dev.biz.admin.model.User;
import gxf.dev.biz.admin.model.UserRole;
import gxf.dev.biz.admin.service.UserRoleService;
import gxf.dev.biz.admin.service.UserService;
import gxf.dev.biz.admin.util.PasswordHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gongxufan 2017/4/22.
 */
@RestController
@RequestMapping("/users")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;

    @RequestMapping
    public ResultDto getAll(User user, String draw,
                            @RequestParam(required = false, defaultValue = "1") int page,
                            @RequestParam(required = false, defaultValue = "10") int limit) {
        ResultDto resultDto = ResultDto.ok();
        Map<String, Object> map = new HashMap<>();
        int start = (page - 1) * limit + 1;
        PageInfo<User> pageInfo = userService.selectByPage(user, start, limit);
        logger.debug("pageInfo.getTotal():" + pageInfo.getTotal());
        map.put("total", pageInfo.getTotal());
        map.put("userList", pageInfo.getList());
        resultDto.setData(map);
        return resultDto;
    }


    /**
     * 保存用户角色
     *
     * @param userRole 用户角色
     *                 此处获取的参数的角色id是以 “,” 分隔的字符串
     * @return
     */
    @RequestMapping("/saveUserRoles")
    public String saveUserRoles(UserRole userRole) {
        if (StringUtils.isEmpty(userRole.getUserid()))
            return "error";
        try {
            userRoleService.addUserRole(userRole);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(User user) {
        User u = userService.selectByUsername(user.getUsername());
        if (u != null)
            return ResultDto.fail("名户名已存在");
        try {
            PasswordHelper passwordHelper = new PasswordHelper();
            passwordHelper.encryptPassword(user);
            userService.save(user);
            return ResultDto.ok("用户添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultDto.fail("添加用户失败");
        }
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String ids) {
        try {
            userService.delUser(ids);
            return ResultDto.ok("删除用户成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultDto.fail("删除用户失败");
        }
    }

}
