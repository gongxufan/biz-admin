package gxf.dev.biz.admin.service;

import com.github.pagehelper.PageInfo;
import gxf.dev.biz.admin.model.User;

/**
 * Created by gongxufan on 2017/11/16.
 */
public interface UserService extends IService<User> {
    PageInfo<User> selectByPage(User user, int start, int length);

    User selectByUsername(String username);

    void delUser(String userid);

}
