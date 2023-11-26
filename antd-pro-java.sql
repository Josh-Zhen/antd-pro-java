/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50735 (5.7.35)
 Source Host           : localhost:3306
 Source Schema         : antd-pro

 Target Server Type    : MySQL
 Target Server Version : 50735 (5.7.35)
 File Encoding         : 65001

 Date: 25/11/2023 17:31:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dict_data
-- ----------------------------
DROP TABLE IF EXISTS `dict_data`;
CREATE TABLE `dict_data`
(
    `id`          int(6) UNSIGNED                                               NOT NULL AUTO_INCREMENT COMMENT '主键',
    `type_id`     int(6)                                                        NOT NULL COMMENT '字典类型id',
    `name`        text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci         NOT NULL COMMENT '字典名称',
    `value`       varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '编码',
    `sort`        int(4) UNSIGNED                                               NOT NULL COMMENT '排序',
    `remark`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '备注',
    `state`       tinyint(1) UNSIGNED                                           NOT NULL DEFAULT 1 COMMENT '状态(0:正常 1:停用)',
    `create_date` datetime                                                      NOT NULL COMMENT '创建时间',
    `update_date` datetime                                                      NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '系统字典值表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of dict_data
-- ----------------------------
INSERT INTO `dict_data`
VALUES (1, 1, '停用', '0', 1, NULL, 1, NOW(), NULL);
INSERT INTO `dict_data`
VALUES (2, 1, '正常', '1', 2, NULL, 1, NOW(), NULL);
INSERT INTO `dict_data`
VALUES (3, 2, '否', '0', 1, NULL, 1, NOW(), NULL);
INSERT INTO `dict_data`
VALUES (4, 2, '是', '1', 2, NULL, 1, NOW(), NULL);

-- ----------------------------
-- Table structure for dict_type
-- ----------------------------
DROP TABLE IF EXISTS `dict_type`;
CREATE TABLE `dict_type`
(
    `id`          int(6) UNSIGNED                                               NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name`        varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
    `code`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '编码',
    `sort`        int(4) UNSIGNED                                               NOT NULL COMMENT '排序',
    `remark`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '备注',
    `state`       tinyint(1) UNSIGNED                                           NOT NULL DEFAULT 1 COMMENT '状态(0:正常 1:停用)',
    `create_date` datetime                                                      NOT NULL COMMENT '创建时间',
    `update_date` datetime                                                      NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `code_index` (`code`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '系统字典类型表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of dict_type
-- ----------------------------
INSERT INTO `dict_type`
VALUES (1, '通用状态', 'common_status', 1, NULL, 1, NOW(), NULL);
INSERT INTO `dict_type`
VALUES (2, '是否标识', 'tf_status', 2, NULL, 1, NOW(), NULL);

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`
(
    `id`                    int(10) UNSIGNED                                              NOT NULL AUTO_INCREMENT COMMENT 'id',
    `name`                  varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '同路由中的名稱，用於保活的作用',
    `title`                 varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '标题',
    `icon`                  text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci         NULL COMMENT '图标',
    `component`             varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '组件(Iframe、RouteView、ComponentError)',
    `path`                  varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '路径',
    `redirect`              varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '重定向路径',
    `order_num`             int(2) UNSIGNED                                               NOT NULL DEFAULT 99 COMMENT '显示顺序',
    `affix`                 tinyint(1) UNSIGNED                                           NOT NULL DEFAULT 0 COMMENT '固定页签(0:否，1:是)',
    `parent_id`             int(10) UNSIGNED                                              NOT NULL DEFAULT 0 COMMENT '父级菜单id',
    `hide_in_menu`          tinyint(1) UNSIGNED                                           NOT NULL DEFAULT 0 COMMENT '是否隐藏菜单(0:否，1:是)',
    `parent_keys`           varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '如果使用了隐藏，那么点击当前菜单时，可以使用父级的key',
    `url`                   text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci         NULL COMMENT '超链接',
    `hide_in_breadcrumb`    tinyint(1) UNSIGNED                                           NOT NULL DEFAULT 0 COMMENT '是否存在面包屑(0:否，1:是)',
    `hide_children_in_menu` tinyint(1) UNSIGNED                                           NOT NULL DEFAULT 0 COMMENT '是否隐藏所有子菜单(0:否，1:是)',
    `keep_alive`            tinyint(1) UNSIGNED                                           NOT NULL DEFAULT 0 COMMENT '是否保活(0:否，1：是)',
    `target`                varchar(7) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci   NULL     DEFAULT NULL COMMENT '全连接跳转模式(\'_blank\' | \'_self\' | \'_parent\')',
    `remark`                varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '备注',
    `createDate`            datetime                                                      NOT NULL COMMENT '创建时间',
    `updateDate`            datetime                                                      NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 44
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '菜单表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu`
VALUES (1, 'Dashboard', '仪表盘', 'DashboardOutlined', 'RouteView', '/dashboard', '/dashboard/analysis', 0, 0, 0, 0, NULL,
        NULL, 0, 0, 0, NULL, NULL, NOW(), NULL);
INSERT INTO `menu`
VALUES (2, 'DashboardAnalysis', '分析页', NULL, '/dashboard/analysis', '/dashboard/analysis', NULL, 99, 0, 1, 0, NULL,
        NULL, 0, 0, 1, NULL, NULL, NOW(), NULL);
INSERT INTO `menu`
VALUES (3, 'DashboardMonitor', '监控页', NULL, '/dashboard/monitor', '/dashboard/monitor', NULL, 99, 0, 1, 0, NULL, NULL,
        0, 0, 1, NULL, NULL, NOW(), NULL);
INSERT INTO `menu`
VALUES (4, 'DashboardWorkplace', '工作台', NULL, '/dashboard/workplace', '/dashboard/workplace', NULL, 99, 0, 1, 0, NULL,
        NULL, 0, 0, 0, NULL, NULL, NOW(), NULL);
INSERT INTO `menu`
VALUES (5, 'Form', '表单页', 'FormOutlined', 'RouteView', '/form', '/form/basic', 99, 0, 0, 0, NULL, NULL, 0, 0, 0, NULL,
        NULL, NOW(), NULL);
INSERT INTO `menu`
VALUES (6, 'FormBasic', '基础表单', NULL, '/form/basic-form/index', '/form/basic-form', NULL, 99, 0, 5, 0, NULL, NULL, 0, 0,
        0, NULL, NULL, NOW(), NULL);
INSERT INTO `menu`
VALUES (7, 'FormStep', '分步表单', NULL, '/form/step-form/index', '/form/step-form', NULL, 99, 0, 5, 0, NULL, NULL, 0, 0, 0,
        NULL, NULL, NOW(), NULL);
INSERT INTO `menu`
VALUES (8, 'FormAdvanced', '高级表单', NULL, '/form/advanced-form/index', '/form/advanced-form', NULL, 99, 0, 5, 0, NULL,
        NULL, 0, 0, 0, NULL, NULL, NOW(), NULL);
INSERT INTO `menu`
VALUES (9, 'Link', '链接', 'LinkOutlined', 'RouteView', '/link', '/link/iframe', 99, 0, 0, 0, NULL, NULL, 0, 0, 0, NULL,
        NULL, NOW(), NULL);
INSERT INTO `menu`
VALUES (10, 'LinkIframe', 'AntDesign', 'Iframe', 'Iframe', '/link/iframe', NULL, 99, 0, 9, 0, NULL,
        'https://ant.design/', 0, 0, 0, NULL, NULL, NOW(), NULL);
INSERT INTO `menu`
VALUES (11, 'LinkAntdv', 'AntDesignVue', NULL, 'Iframe', '/link/antdv', NULL, 99, 0, 9, 0, NULL, 'https://antdv.com/',
        0, 0, 1, NULL, NULL, NOW(), NULL);
INSERT INTO `menu`
VALUES (12, 'LinkExternal', '跳转百度', NULL, NULL, 'https://www.baidu.com', NULL, 99, 0, 9, 0, NULL, '', 0, 0, 0, NULL,
        NULL, NOW(), NULL);
INSERT INTO `menu`
VALUES (13, 'Menu', '菜单', 'BarsOutlined', 'RouteView', '/menu', '/menu/menu1', 99, 0, 0, 0, NULL, NULL, 0, 0, 0, NULL,
        NULL, NOW(), NULL);
INSERT INTO `menu`
VALUES (14, 'MenuMenu1', '菜单1', NULL, '/menu/menu1', '/menu/menu1', NULL, 99, 0, 13, 0, NULL, NULL, 0, 0, 1, NULL, NULL,
        NOW(), NULL);
INSERT INTO `menu`
VALUES (15, 'MenuMenu2', '菜单2', NULL, '/menu/menu2', '/menu/menu2', NULL, 99, 0, 13, 0, NULL, NULL, 0, 0, 1, NULL, NULL,
        NOW(), NUll);
INSERT INTO `menu`
VALUES (16, 'MenuMenu3', '菜单1-1', NULL, 'RouteView', '/menu/menu3', '/menu/menu3/menu1', 99, 0, 13, 0, NULL, NULL, 0, 0,
        0, NULL, NULL, NOW(), NULL);
INSERT INTO `menu`
VALUES (17, 'MenuMenu4', '菜单1-1-1', NULL, '/menu/menu-1-1/menu1', '/menu/menu3/menu1', NULL, 99, 0, 16, 0, NULL, NULL,
        0, 0, 1, NULL, NULL, NOW(), NULL);
INSERT INTO `menu`
VALUES (18, 'MenuMenu5', '菜单1-1-2', NULL, '/menu/menu-1-1/menu2', '/menu/menu3/menu2', NULL, 99, 0, 16, 0, NULL, NULL,
        0, 0, 1, NULL, NULL, NOW(), NULL);
INSERT INTO `menu`
VALUES (19, 'Access', '权限模块', 'ClusterOutlined', 'RouteView', '/access', '/access/common', 99, 0, 0, 0, NULL, NULL, 0,
        0, 0, NULL, NULL, NOW(), NULL);
INSERT INTO `menu`
VALUES (20, 'AccessCommon', '通用权限', NULL, '/access/common', '/access/common', NULL, 99, 0, 19, 0, NULL, NULL, 0, 0, 0,
        NULL, NULL, NOW(), NULL);
INSERT INTO `menu`
VALUES (21, 'AccessUser', '普通用户', NULL, '/access/user', '/access/user', NULL, 99, 0, 19, 0, NULL, NULL, 0, 0, 0, NULL,
        NULL, NOW(), NULL);
INSERT INTO `menu`
VALUES (22, 'AccessAdmin', '管理员', NULL, '/access/admin', '/access/admin', NULL, 99, 0, 19, 0, NULL, NULL, 0, 0, 0, NULL,
        NULL, NOW(), NULL);
INSERT INTO `menu`
VALUES (23, 'Exception', '异常页', 'WarningOutlined', 'RouteView', '/exception', '/exception/403', 99, 0, 0, 0, NULL, NULL,
        0, 0, 0, NULL, NULL, NOW(), NULL);
INSERT INTO `menu`
VALUES (24, '403', '403', NULL, '/exception/403', '/exception/403', NULL, 99, 0, 23, 0, NULL, NULL, 0, 0, 0, NULL, NULL,
        NOW(), NULL);
INSERT INTO `menu`
VALUES (25, '404', '404', NULL, '/exception/404', '/exception/404', NULL, 99, 0, 23, 0, NULL, NULL, 0, 0, 0, NULL, NULL,
        NOW(), NULL);
INSERT INTO `menu`
VALUES (26, '500', '500', NULL, '/exception/500', '/exception/500', NULL, 99, 0, 23, 0, NULL, NULL, 0, 0, 0, NULL, NULL,
        NOW(), NULL);
INSERT INTO `menu`
VALUES (27, 'Result', '结果页', 'CheckCircleOutlined', 'RouteView', '/result', '/result/success', 99, 0, 0, 0, NULL, NULL,
        0, 0, 0, NULL, NULL, NOW(), NULL);
INSERT INTO `menu`
VALUES (28, 'ResultSuccess', '成功页', NULL, '/result/success', '/result/success', NULL, 99, 0, 27, 0, NULL, NULL, 0, 0, 0,
        NULL, NULL, NOW(), NULL);
INSERT INTO `menu`
VALUES (29, 'ResultFail', '失败页', NULL, '/result/fail', '/result/fail', NULL, 99, 0, 27, 0, NULL, NULL, 0, 0, 0, NULL,
        NULL, NOW(), NULL);
INSERT INTO `menu`
VALUES (30, 'List', '列表页', 'TableOutlined', 'RouteView', '/list', '/list/card-list', 99, 0, 0, 0, NULL, NULL, 0, 0, 0,
        NULL, NULL, NOW(), NULL);
INSERT INTO `menu`
VALUES (31, 'ListCard', '卡片列表', NULL, '/list/card-list', '/list/card-list', NULL, 99, 0, 30, 0, NULL, NULL, 0, 0, 0,
        NULL, NULL, NOW(), NULL);
INSERT INTO `menu`
VALUES (32, 'SearchList', '搜索列表', NULL, '/list/search-list', '/list/search-list', NULL, 99, 0, 30, 0, NULL, NULL, 0, 0,
        0, NULL, NULL, NOW(), NULL);
INSERT INTO `menu`
VALUES (33, 'SearchListArticles', '搜索列表（文章）', NULL, '/list/search-list/articles', '/list/search-list/articles', NULL,
        99, 0, 32, 0, NULL, NULL, 0, 0, 0, NULL, NULL, NOW(), NULL);
INSERT INTO `menu`
VALUES (34, 'SearchListProjects', '搜索列表（项目）', NULL, '/list/search-list/projects', '/list/search-list/projects', NULL,
        99, 0, 32, 0, NULL, NULL, 0, 0, 0, NULL, NULL, NOW(), NULL);
INSERT INTO `menu`
VALUES (35, 'SearchListApplications', '搜索列表（应用）', NULL, '/list/search-list/applications',
        '/list/search-list/applications', NULL, 99, 0, 32, 0, NULL, NULL, 0, 0, 0, NULL, NULL, NOW(), NULL);
INSERT INTO `menu`
VALUES (36, 'BasicCard', '标准列表', NULL, '/list/basic-list', '/list/basic-list', NULL, 99, 0, 30, 0, NULL, NULL, 0, 0, 0,
        NULL, NULL, NOW(), NULL);
INSERT INTO `menu`
VALUES (37, 'ConsultTable', '查询表格', NULL, '/list/table-list', '/list/table-list', NULL, 99, 0, 30, 0, NULL, NULL, 0, 0,
        0, NULL, NULL, NOW(), NULL);
INSERT INTO `menu`
VALUES (38, 'Profile', '详情页', 'ProfileOutlined', 'RouteView', '/profile', '/profile/basic', 99, 0, 0, 0, NULL, NULL, 0,
        0, 0, NULL, NULL, NOW(), NULL);
INSERT INTO `menu`
VALUES (39, 'ProfileBasic', '基础详情页', NULL, '/profile/basic', '/profile/basic/index', NULL, 99, 0, 38, 0, NULL, NULL, 0,
        0, 0, NULL, NULL, NOW(), NULL);
INSERT INTO `menu`
VALUES (40, 'ProfileAdvanced', '高级详细页', NULL, '/profile/advanced', '/profile/advanced/index', NULL, 99, 0, 38, 0, NULL,
        NULL, 0, 0, 0, NULL, NULL, NOW(), NULL);
INSERT INTO `menu`
VALUES (41, 'Account', '个人页', 'UserOutlined', 'RouteView', '/account', '/account/center', 99, 0, 0, 0, NULL, NULL, 0, 0,
        0, NULL, NULL, NOW(), NULL);
INSERT INTO `menu`
VALUES (42, 'AccountCenter', '个人中心', NULL, '/account/center', '/account/center', NULL, 99, 0, 41, 0, NULL, NULL, 0, 0,
        0, NULL, NULL, NOW(), NULL);
INSERT INTO `menu`
VALUES (43, 'AccountSettings', '个人设置', NULL, '/account/settings', '/account/settings', NULL, 99, 0, 41, 0, NULL, NULL,
        0, 0, 0, NULL, NULL, NOW(), NULL);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`
(
    `id`          int(10) UNSIGNED                                             NOT NULL AUTO_INCREMENT COMMENT 'id',
    `name`        varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
    `role_key`    varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限标识',
    `state`       tinyint(1) UNSIGNED                                          NOT NULL DEFAULT 1 COMMENT '状态(0:正常 1:停用)',
    `create_date` datetime                                                     NOT NULL COMMENT '创建时间',
    `update_date` datetime                                                     NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '角色表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role`
VALUES (1, '管理员', 'ADMIN', 1, NOW(), NULL);
INSERT INTO `role`
VALUES (2, '用户', 'USER', 1, NOW(), NULL);
INSERT INTO `role`
VALUES (3, '生成', 'GENERATE', 1, NOW(), NULL);

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu`
(
    `id`      int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `role_id` int(10) UNSIGNED NOT NULL COMMENT '角色id',
    `menu_id` int(10) UNSIGNED NOT NULL COMMENT '目录id',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 86
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '角色菜单中间表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu`
VALUES (1, 1, 1);
INSERT INTO `role_menu`
VALUES (2, 1, 2);
INSERT INTO `role_menu`
VALUES (3, 1, 3);
INSERT INTO `role_menu`
VALUES (4, 1, 4);
INSERT INTO `role_menu`
VALUES (5, 1, 5);
INSERT INTO `role_menu`
VALUES (6, 1, 6);
INSERT INTO `role_menu`
VALUES (7, 1, 7);
INSERT INTO `role_menu`
VALUES (8, 1, 8);
INSERT INTO `role_menu`
VALUES (9, 1, 9);
INSERT INTO `role_menu`
VALUES (10, 1, 10);
INSERT INTO `role_menu`
VALUES (11, 1, 11);
INSERT INTO `role_menu`
VALUES (12, 1, 12);
INSERT INTO `role_menu`
VALUES (13, 1, 13);
INSERT INTO `role_menu`
VALUES (14, 1, 14);
INSERT INTO `role_menu`
VALUES (15, 1, 15);
INSERT INTO `role_menu`
VALUES (16, 1, 16);
INSERT INTO `role_menu`
VALUES (17, 1, 17);
INSERT INTO `role_menu`
VALUES (18, 1, 18);
INSERT INTO `role_menu`
VALUES (19, 1, 19);
INSERT INTO `role_menu`
VALUES (20, 1, 20);
INSERT INTO `role_menu`
VALUES (21, 1, 21);
INSERT INTO `role_menu`
VALUES (22, 1, 22);
INSERT INTO `role_menu`
VALUES (23, 1, 23);
INSERT INTO `role_menu`
VALUES (24, 1, 24);
INSERT INTO `role_menu`
VALUES (25, 1, 25);
INSERT INTO `role_menu`
VALUES (26, 1, 26);
INSERT INTO `role_menu`
VALUES (27, 1, 27);
INSERT INTO `role_menu`
VALUES (28, 1, 28);
INSERT INTO `role_menu`
VALUES (29, 1, 29);
INSERT INTO `role_menu`
VALUES (30, 1, 30);
INSERT INTO `role_menu`
VALUES (31, 1, 31);
INSERT INTO `role_menu`
VALUES (32, 1, 32);
INSERT INTO `role_menu`
VALUES (33, 1, 33);
INSERT INTO `role_menu`
VALUES (34, 1, 34);
INSERT INTO `role_menu`
VALUES (35, 1, 35);
INSERT INTO `role_menu`
VALUES (36, 1, 36);
INSERT INTO `role_menu`
VALUES (37, 1, 37);
INSERT INTO `role_menu`
VALUES (38, 1, 38);
INSERT INTO `role_menu`
VALUES (39, 1, 39);
INSERT INTO `role_menu`
VALUES (40, 1, 40);
INSERT INTO `role_menu`
VALUES (41, 1, 41);
INSERT INTO `role_menu`
VALUES (42, 1, 42);
INSERT INTO `role_menu`
VALUES (43, 1, 43);
INSERT INTO `role_menu`
VALUES (44, 2, 1);
INSERT INTO `role_menu`
VALUES (45, 2, 2);
INSERT INTO `role_menu`
VALUES (46, 2, 3);
INSERT INTO `role_menu`
VALUES (47, 2, 4);
INSERT INTO `role_menu`
VALUES (48, 2, 5);
INSERT INTO `role_menu`
VALUES (49, 2, 6);
INSERT INTO `role_menu`
VALUES (50, 2, 7);
INSERT INTO `role_menu`
VALUES (51, 2, 8);
INSERT INTO `role_menu`
VALUES (52, 2, 9);
INSERT INTO `role_menu`
VALUES (53, 2, 10);
INSERT INTO `role_menu`
VALUES (54, 2, 11);
INSERT INTO `role_menu`
VALUES (55, 2, 12);
INSERT INTO `role_menu`
VALUES (56, 2, 13);
INSERT INTO `role_menu`
VALUES (57, 2, 14);
INSERT INTO `role_menu`
VALUES (58, 2, 15);
INSERT INTO `role_menu`
VALUES (59, 2, 16);
INSERT INTO `role_menu`
VALUES (60, 2, 17);
INSERT INTO `role_menu`
VALUES (61, 2, 18);
INSERT INTO `role_menu`
VALUES (62, 2, 19);
INSERT INTO `role_menu`
VALUES (63, 2, 20);
INSERT INTO `role_menu`
VALUES (64, 2, 21);
INSERT INTO `role_menu`
VALUES (65, 2, 23);
INSERT INTO `role_menu`
VALUES (66, 2, 24);
INSERT INTO `role_menu`
VALUES (67, 2, 25);
INSERT INTO `role_menu`
VALUES (68, 2, 26);
INSERT INTO `role_menu`
VALUES (69, 2, 27);
INSERT INTO `role_menu`
VALUES (70, 2, 28);
INSERT INTO `role_menu`
VALUES (71, 2, 29);
INSERT INTO `role_menu`
VALUES (72, 2, 30);
INSERT INTO `role_menu`
VALUES (73, 2, 31);
INSERT INTO `role_menu`
VALUES (74, 2, 32);
INSERT INTO `role_menu`
VALUES (75, 2, 33);
INSERT INTO `role_menu`
VALUES (76, 2, 34);
INSERT INTO `role_menu`
VALUES (77, 2, 35);
INSERT INTO `role_menu`
VALUES (78, 2, 36);
INSERT INTO `role_menu`
VALUES (79, 2, 37);
INSERT INTO `role_menu`
VALUES (80, 2, 38);
INSERT INTO `role_menu`
VALUES (81, 2, 39);
INSERT INTO `role_menu`
VALUES (82, 2, 40);
INSERT INTO `role_menu`
VALUES (83, 2, 41);
INSERT INTO `role_menu`
VALUES (84, 2, 42);
INSERT INTO `role_menu`
VALUES (85, 2, 43);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`          int(10) UNSIGNED                                             NOT NULL AUTO_INCREMENT COMMENT 'id',
    `user_name`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
    `password`    varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
    `number`      varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号码',
    `nick_name`   varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '昵称',
    `state`       tinyint(1) UNSIGNED                                          NULL DEFAULT 1 COMMENT '状态(0:正常 1:停用)',
    `avatar`      text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci        NULL COMMENT '头像',
    `create_date` datetime                                                     NOT NULL COMMENT '创建时间',
    `update_date` datetime                                                     NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '用户表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user`
VALUES (1, 'admin', '$2a$10$9/tGfDxBSKmG7agjMudJ.u3gBW/k/MhZiaW2atE0fMqtYlL2BIUWa', '13566662228', '超级管理员', 1,
        'https://gw.alipayobjects.com/zos/rmsportal/BiazfanxmamNRoxxVxka.png', NOW(), NULL);
INSERT INTO `user`
VALUES (2, 'user', '$2a$10$XrRGT/h0tyF4JLdrXa/1guKBBbr8Yv7B/NyLMUd/HCJJ2VDav1kAS', NULL, 'user', 1,
        'https://gw.alipayobjects.com/zos/rmsportal/BiazfanxmamNRoxxVxka.png', NOW(), NULL);
INSERT INTO `user`
VALUES (3, 'Joshua', '$2a$10$N/czkjf53pgcR8scpAal1.y9.BeUHxLmJ/VKW9BPMtgZD/g1gjmTO', NULL, 'Joshua', 1,
        'https://gw.alipayobjects.com/zos/rmsportal/BiazfanxmamNRoxxVxka.png', NOW(), NULL);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`
(
    `id`      int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `user_id` int(10) UNSIGNED NOT NULL COMMENT '用户id',
    `role_id` int(10) UNSIGNED NOT NULL DEFAULT 2 COMMENT '角色id',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '用户角色中间表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role`
VALUES (1, 1, 1);
INSERT INTO `user_role`
VALUES (2, 2, 2);
INSERT INTO `user_role`
VALUES (3, 1, 2);

SET FOREIGN_KEY_CHECKS = 1;
