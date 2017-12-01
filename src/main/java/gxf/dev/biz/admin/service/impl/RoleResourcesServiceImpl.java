package gxf.dev.biz.admin.service.impl;

import gxf.dev.biz.admin.mapper.UserRoleMapper;
import gxf.dev.biz.admin.model.RoleResources;
import gxf.dev.biz.admin.service.RoleResourcesService;
import gxf.dev.biz.admin.shiro.MyShiroRealm;
import gxf.dev.biz.admin.shiro.ShiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Created by gongxufan on 2017/11/16.
 */
@Service("roleResourcesService")
public class RoleResourcesServiceImpl extends BaseService<RoleResources> implements RoleResourcesService {
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private ShiroService shiroService;

    @Autowired
    private MyShiroRealm myShiroRealm;

    @Override
    //更新权限
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = {Exception.class})
    public void addRoleResources(RoleResources roleResources) {
        //删除
        Example example = new Example(RoleResources.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("roleid", roleResources.getRoleid());
        mapper.deleteByExample(example);
        //添加
        if (!StringUtils.isEmpty(roleResources.getResourcesid())) {
            String[] resourcesArr = roleResources.getResourcesid().split(",");
            for (String resourcesId : resourcesArr) {
                RoleResources r = new RoleResources();
                r.setRoleid(roleResources.getRoleid());
                r.setResourcesid(resourcesId);
                mapper.insert(r);
            }
        }

        List<Integer> userIds = userRoleMapper.findUserIdByRoleId(roleResources.getRoleid());
        //更新当前登录的用户的权限缓存
        myShiroRealm.clearUserAuthByUserId(userIds);


    }
}
