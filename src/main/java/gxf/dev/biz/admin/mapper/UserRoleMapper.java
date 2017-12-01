package gxf.dev.biz.admin.mapper;

import gxf.dev.biz.admin.model.UserRole;
import gxf.dev.biz.admin.util.MyMapper;

import java.util.List;

public interface UserRoleMapper extends MyMapper<UserRole> {
    public List<Integer> findUserIdByRoleId(Integer roleId);
}