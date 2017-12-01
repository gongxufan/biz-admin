package gxf.dev.biz.admin.mapper;

import gxf.dev.biz.admin.model.Role;
import gxf.dev.biz.admin.util.MyMapper;

import java.util.List;

public interface RoleMapper extends MyMapper<Role> {
    public List<Role> queryRoleListWithSelected(Integer id);
}