package gxf.dev.biz.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import gxf.dev.biz.admin.mapper.UserRoleMapper;
import gxf.dev.biz.admin.model.User;
import gxf.dev.biz.admin.model.UserRole;
import gxf.dev.biz.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.Arrays;
import java.util.List;

/**
 * Created by gongxufan on 2017/11/16.
 */
@Service("userService")
public class UserServiceImpl extends BaseService<User> implements UserService {
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public PageInfo<User> selectByPage(User user, int start, int length) {
        int page = start / length + 1;
        Example example = new Example(User.class);
        example.setOrderByClause("id desc");
        Example.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotEmpty(user.getUsername())) {
            criteria.andLike("username", "%" + user.getUsername() + "%");
        }
        if (user.getId() != null) {
            criteria.andEqualTo("id", user.getId());
        }
        if (user.getEnable() != null) {
            criteria.andEqualTo("enable", user.getEnable());
        }
        //分页查询
        PageHelper.startPage(page, length);
        List<User> userList = selectByExample(example);
        return new PageInfo<>(userList);
    }

    @Override
    public User selectByUsername(String username) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username", username);
        List<User> userList = selectByExample(example);
        if (userList.size() > 0) {
            return userList.get(0);
        }
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = {Exception.class})
    public void delUser(String userid) {
        Arrays.stream(userid.split(",")).forEach(e -> {
            Integer id = Integer.valueOf(e);
            //删除用户表
            mapper.deleteByPrimaryKey(id);
            //删除用户角色表
            Example example = new Example(UserRole.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("userid", id);
            userRoleMapper.deleteByExample(example);
        });
    }
}
