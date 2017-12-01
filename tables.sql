--MYSQL SCRIPTS--
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `resources`
-- ----------------------------
DROP TABLE IF EXISTS `resources`;
CREATE TABLE `resources` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(255) default NULL COMMENT '资源名称',
  `resUrl` varchar(255) default NULL COMMENT '资源url',
  `type` int(11) default NULL COMMENT '资源类型   1:菜单    2：按钮',
  `parentId` int(11) default NULL COMMENT '父资源',
  `sort` int(11) default NULL COMMENT '排序',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of resources
-- ----------------------------
INSERT INTO `resources` VALUES ('1', '系统设置', '/system', '0', '0', '1');
INSERT INTO `resources` VALUES ('2', '用户管理', '/usersPage', '1', '1', '2');
INSERT INTO `resources` VALUES ('3', '角色管理', '/rolesPage', '1', '1', '3');
INSERT INTO `resources` VALUES ('4', '资源管理', '/resourcesPage', '1', '1', '4');
INSERT INTO `resources` VALUES ('5', '添加用户', '/users/add', '2', '2', '5');
INSERT INTO `resources` VALUES ('6', '删除用户', '/users/delete', '2', '2', '6');
INSERT INTO `resources` VALUES ('7', '添加角色', '/roles/add', '2', '3', '7');
INSERT INTO `resources` VALUES ('8', '删除角色', '/roles/delete', '2', '3', '8');
INSERT INTO `resources` VALUES ('9', '添加资源', '/resources/add', '2', '4', '9');
INSERT INTO `resources` VALUES ('10', '删除资源', '/resources/delete', '2', '4', '10');
INSERT INTO `resources` VALUES ('11', '分配角色', '/users/saveUserRoles', '2', '2', '11');
INSERT INTO `resources` VALUES ('13', '分配权限', '/roles/saveRoleResources', '2', '3', '12');

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL auto_increment,
  `roleDesc` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '管理员');
INSERT INTO `role` VALUES ('2', '普通用户');
INSERT INTO `role` VALUES ('3', '超级管理员');

-- ----------------------------
-- Table structure for `role_resources`
-- ----------------------------
DROP TABLE IF EXISTS `role_resources`;
CREATE TABLE `role_resources` (
  `roleId` int(11) NOT NULL,
  `resourcesId` int(11) NOT NULL,
  PRIMARY KEY  (`roleId`,`resourcesId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_resources
-- ----------------------------
INSERT INTO `role_resources` VALUES ('1', '2');
INSERT INTO `role_resources` VALUES ('1', '3');
INSERT INTO `role_resources` VALUES ('1', '4');
INSERT INTO `role_resources` VALUES ('1', '5');
INSERT INTO `role_resources` VALUES ('1', '6');
INSERT INTO `role_resources` VALUES ('1', '7');
INSERT INTO `role_resources` VALUES ('1', '8');
INSERT INTO `role_resources` VALUES ('1', '9');
INSERT INTO `role_resources` VALUES ('1', '10');
INSERT INTO `role_resources` VALUES ('1', '11');
INSERT INTO `role_resources` VALUES ('1', '13');
INSERT INTO `role_resources` VALUES ('2', '2');
INSERT INTO `role_resources` VALUES ('2', '3');
INSERT INTO `role_resources` VALUES ('2', '4');
INSERT INTO `role_resources` VALUES ('2', '9');
INSERT INTO `role_resources` VALUES ('3', '2');
INSERT INTO `role_resources` VALUES ('3', '3');
INSERT INTO `role_resources` VALUES ('3', '4');
INSERT INTO `role_resources` VALUES ('3', '5');
INSERT INTO `role_resources` VALUES ('3', '7');
INSERT INTO `role_resources` VALUES ('3', '8');
INSERT INTO `role_resources` VALUES ('3', '9');
INSERT INTO `role_resources` VALUES ('3', '10');
INSERT INTO `role_resources` VALUES ('9', '9');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL auto_increment,
  `username` varchar(33) default NULL,
  `password` varchar(33) default NULL,
  `email` varchar(33) default NULL,
  `tel` varchar(33) default NULL,
  `gender` int(1) default '1' COMMENT '性别',
  `enable` int(10) default '1' COMMENT '是否启用',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '3ef7164d1f6167cb9f2658c07d3c2f0a', 'gongxufan@126.com','13907488484','1','1');
INSERT INTO `user` VALUES ('2', 'user', '3ef7164d1f6167cb9f2658c07d3c2f0a', '381332153@aa.com','15978648596','0','1');
-- ----------------------------
-- Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `userId` int(11) default NULL,
  `roleId` int(11) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1');
INSERT INTO `user_role` VALUES ('2', '2');

