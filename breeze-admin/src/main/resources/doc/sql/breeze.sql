/*
 Navicat Premium Data Transfer

 Source Server         : 开发环境-MySQL
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : 192.168.0.192:3306
 Source Schema         : breeze

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 28/03/2023 15:52:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_sys_organization
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_organization`;
CREATE TABLE `t_sys_organization`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '机构编码',
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '机构名称',
  `order_num` int(0) NULL DEFAULT 0 COMMENT '排序',
  `parent_id` bigint(0) NULL DEFAULT 0 COMMENT '父级id，0表示顶级',
  `level` int(0) NOT NULL DEFAULT 0 COMMENT '层级，0表示顶级',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_code`(`code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统机构表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_organization
-- ----------------------------
INSERT INTO `t_sys_organization` VALUES (15, '1000', '中国微风集团', 0, 0, 0);
INSERT INTO `t_sys_organization` VALUES (16, '1000001', '新能源事业部', 1, 15, 1);
INSERT INTO `t_sys_organization` VALUES (17, '100000101', '研发中心', 1, 16, 2);
INSERT INTO `t_sys_organization` VALUES (18, '100000102', '运营中心', 2, 16, 2);
INSERT INTO `t_sys_organization` VALUES (19, 'customer_center', '客服中心', 3, 16, 2);

-- ----------------------------
-- Table structure for t_sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_resource`;
CREATE TABLE `t_sys_resource`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '资源编码',
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '资源名称',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资源链接',
  `type` smallint(0) NOT NULL DEFAULT 0 COMMENT '资源类型：0-目录; 1-菜单; 2-按钮',
  `order_num` int(0) NULL DEFAULT 0 COMMENT '排序',
  `parent_id` bigint(0) NULL DEFAULT 0 COMMENT '父级id，0表示当前资源是顶级',
  `level` int(0) NOT NULL DEFAULT 0 COMMENT '层级，0表示顶级',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_code`(`code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统资源表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_resource
-- ----------------------------
INSERT INTO `t_sys_resource` VALUES (1, 'root', '微风管理系统', '', 0, 0, 0, 0);
INSERT INTO `t_sys_resource` VALUES (8, 'sys_manage', '系统管理', '', 0, 100000, 1, 1);
INSERT INTO `t_sys_resource` VALUES (9, 'res_manage', '资源管理', '/static/html/sysmanage/res_manage.html', 1, 100200, 8, 2);
INSERT INTO `t_sys_resource` VALUES (10, 'org_manage', '机构管理', '/static/html/sysmanage/org_manage.html', 1, 100100, 8, 2);
INSERT INTO `t_sys_resource` VALUES (11, 'role_manage', '角色管理', '/static/html/sysmanage/role_manage.html', 1, 100300, 8, 2);
INSERT INTO `t_sys_resource` VALUES (12, 'user_manage', '用户管理', '/static/html/sysmanage/user_manage.html', 1, 100400, 8, 2);
INSERT INTO `t_sys_resource` VALUES (13, 'user_manage_add', '用户管理-新增', '/user/add', 2, 100402, 12, 3);
INSERT INTO `t_sys_resource` VALUES (14, 'user_manage_edit', '用户管理-编辑', '/user/edit', 2, 100404, 12, 3);
INSERT INTO `t_sys_resource` VALUES (17, 'res_manage_page_query', '资源管理-分页查询', '/resource/page-list', 2, 100201, 9, 3);
INSERT INTO `t_sys_resource` VALUES (18, 'res_manage_add', '资源管理-新增', '/resource/add', 2, 100202, 9, 3);
INSERT INTO `t_sys_resource` VALUES (19, 'res_manage_detail', '资源管理-明细', '/resource/detail', 2, 100203, 9, 3);
INSERT INTO `t_sys_resource` VALUES (20, 'res_manage_edit', '资源管理-编辑', '/resource/edit', 2, 100204, 9, 3);
INSERT INTO `t_sys_resource` VALUES (21, 'res_manage_delete', '资源管理-删除', '/resource/delete', 2, 100205, 9, 3);
INSERT INTO `t_sys_resource` VALUES (22, 'org_manage_page_list', '机构管理-分页查询', '/organization/page-list', 2, 100101, 10, 3);
INSERT INTO `t_sys_resource` VALUES (23, 'org_manage_add', '机构管理-新增', '/organization/add', 2, 100102, 10, 3);
INSERT INTO `t_sys_resource` VALUES (24, 'org_manage_detail', '机构管理-明细', '/organization/detail', 2, 100103, 10, 3);
INSERT INTO `t_sys_resource` VALUES (25, 'org_manage_edit', '机构管理-编辑', '/organization/edit', 2, 100104, 10, 3);
INSERT INTO `t_sys_resource` VALUES (26, 'org_manage_delete', '机构管理-删除', '/organization/delete', 2, 100105, 10, 3);
INSERT INTO `t_sys_resource` VALUES (27, 'role_manage_page_list', '角色管理-分页查询', '/role/page-list', 2, 100301, 11, 3);
INSERT INTO `t_sys_resource` VALUES (28, 'role_manage_add', '角色管理-新增', '/role/add', 2, 100302, 11, 3);
INSERT INTO `t_sys_resource` VALUES (29, 'role_manage_detail', '角色管理-明细', '/role/detail', 2, 100303, 11, 3);
INSERT INTO `t_sys_resource` VALUES (30, 'role_manage_edit', '角色管理-编辑', '/role/edit', 2, 100304, 11, 3);
INSERT INTO `t_sys_resource` VALUES (31, 'role_manage_delete', '角色管理-删除', '/role/delete', 2, 100305, 11, 3);
INSERT INTO `t_sys_resource` VALUES (32, 'role_manage_assign_resource', '角色管理-分配资源', '/role-resource/assign', 2, 100306, 11, 3);
INSERT INTO `t_sys_resource` VALUES (33, 'user_manage_page_list', '用户管理-分页查询', '/user/page-list', 2, 100401, 12, 3);
INSERT INTO `t_sys_resource` VALUES (34, 'user_manage_detail', '用户管理-明细', '/user/detail', 2, 100403, 12, 3);
INSERT INTO `t_sys_resource` VALUES (35, 'user_manage_delete', '用户管理-删除', '/user/delete', 2, 100405, 12, 3);
INSERT INTO `t_sys_resource` VALUES (36, 'user_manage_assign_role', '用户管理-分配角色', '/user-role/assign', 2, 100406, 12, 3);
INSERT INTO `t_sys_resource` VALUES (37, 'org_manage_tree', '机构管理-结构树', '/organization/tree', 2, 100106, 10, 3);
INSERT INTO `t_sys_resource` VALUES (38, 'res_manage_tree', '资源管理-结构树', '/resource/tree', 2, 100206, 9, 3);

-- ----------------------------
-- Table structure for t_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role`;
CREATE TABLE `t_sys_role`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `code` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色编码',
  `status` smallint(0) NOT NULL DEFAULT 1 COMMENT '状态: 0-停用; 1-正常',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_code`(`code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_role
-- ----------------------------
INSERT INTO `t_sys_role` VALUES (1, '超级管理员', 'super_administrator', 1);
INSERT INTO `t_sys_role` VALUES (2, '普通员工', 'employee', 1);
INSERT INTO `t_sys_role` VALUES (3, '部门经理', 'department_manager', 1);

-- ----------------------------
-- Table structure for t_sys_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role_resource`;
CREATE TABLE `t_sys_role_resource`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `role_id` bigint(0) NOT NULL COMMENT '角色id',
  `resource_id` bigint(0) NOT NULL COMMENT '资源id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_roleId`(`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色和资源关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_role_resource
-- ----------------------------
INSERT INTO `t_sys_role_resource` VALUES (15, 3, 1);
INSERT INTO `t_sys_role_resource` VALUES (16, 3, 8);
INSERT INTO `t_sys_role_resource` VALUES (17, 3, 12);
INSERT INTO `t_sys_role_resource` VALUES (18, 3, 33);

-- ----------------------------
-- Table structure for t_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user`;
CREATE TABLE `t_sys_user`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `nickname` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '昵称',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `salt` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码盐值',
  `status` smallint(0) NOT NULL DEFAULT 1 COMMENT '状态：0-停用；1-正常',
  `org_id` bigint(0) NOT NULL COMMENT '所属机构',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_user
-- ----------------------------
INSERT INTO `t_sys_user` VALUES (1, 'admin', '谢晓峰', '795302a1653725afc9c865a21a1831da', 'KafOxx', 1, 18);
INSERT INTO `t_sys_user` VALUES (2, 'zhangsanfeng', '张三丰', '795302a1653725afc9c865a21a1831da', 'KafOxx', 1, 17);

-- ----------------------------
-- Table structure for t_sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user_role`;
CREATE TABLE `t_sys_user_role`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` bigint(0) NOT NULL COMMENT '用户id',
  `role_id` bigint(0) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_userId`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户和角色关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_user_role
-- ----------------------------
INSERT INTO `t_sys_user_role` VALUES (3, 1, 1);
INSERT INTO `t_sys_user_role` VALUES (4, 1, 2);
INSERT INTO `t_sys_user_role` VALUES (7, 2, 3);

SET FOREIGN_KEY_CHECKS = 1;
