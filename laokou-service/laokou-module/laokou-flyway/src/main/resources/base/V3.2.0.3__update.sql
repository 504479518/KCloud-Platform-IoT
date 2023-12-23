CREATE TABLE `boot_sys_ip` (
                               `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
                               `creator` bigint NOT NULL DEFAULT '0' COMMENT '创建人',
                               `editor` bigint NOT NULL DEFAULT '0' COMMENT '编辑人',
                               `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                               `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                               `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标识 0未删除 1已删除',
                               `version` int NOT NULL DEFAULT '0' COMMENT '版本号',
                               `dept_id` bigint NOT NULL DEFAULT '0' COMMENT '部门ID',
                               `dept_path` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '部门PATH',
                               `tenant_id` bigint NOT NULL DEFAULT '0' COMMENT '租户ID',
                               `label` varchar(10) NOT NULL COMMENT '标签',
                               `value` varchar(20) NOT NULL COMMENT '值',
                               PRIMARY KEY (`id`) USING BTREE,
                               KEY `idx_label` (`label`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='IP';
INSERT INTO `boot_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1871418743863721985, 1707428076142559234, 1707428076142559234, '2023-12-22 22:56:05', '2023-12-23 00:41:46', 0, 1, 1535887940687765505, '0,1535887940687765505', 0, 1871369197532028929, 'ips:refresh-black', 1, '刷新黑名单', '/v1/ips/black/refresh', 'sync', 5, 0);
INSERT INTO `boot_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1871471844029161474, 1707428076142559234, 1707428076142559234, '2023-12-23 00:41:35', '2023-12-23 00:41:35', 0, 0, 1535887940687765505, '0,1535887940687765505', 0, 1871373525961482242, 'ips:refresh-white', 1, '刷新白名单', '/v1/ips/white/refresh', 'sync', 5, 1);
INSERT INTO `boot_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1871375139887718402, 1707428076142559234, 1707428076142559234, '2023-12-22 21:29:27', '2023-12-22 21:29:27', 0, 0, 1535887940687765505, '0,1535887940687765505', 0, 1871373525961482242, 'ips:delete-white', 1, '删除白名单', '/v1/ips/white/delete', 'delete', 10, 0);
INSERT INTO `boot_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1871374233649618946, 1707428076142559234, 1707428076142559234, '2023-12-22 21:27:39', '2023-12-22 21:27:39', 0, 0, 1535887940687765505, '0,1535887940687765505', 0, 1871373525961482242, 'ips:insert-white', 1, '新增白名单', '/v1/ips/white/insert', 'plus', 20, 0);
INSERT INTO `boot_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1871373913229959170, 1707428076142559234, 1707428076142559234, '2023-12-22 21:27:00', '2023-12-22 21:27:00', 0, 0, 1535887940687765505, '0,1535887940687765505', 0, 1871373525961482242, 'ips:white-list', 1, '查询白名单列表', '/v1/ips/white/list', 'search', 30, 0);
INSERT INTO `boot_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1871373525961482242, 1707428076142559234, 1707428076142559234, '2023-12-22 21:26:14', '2023-12-22 21:26:14', 0, 0, 1535887940687765505, '0,1535887940687765505', 0, 1871247748792229889, 'ips:white-view', 0, '白名单', '/v1/ips/white/view', 'profile', 120, 0);
INSERT INTO `boot_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1871371476096065538, 1707428076142559234, 1707428076142559234, '2023-12-22 21:22:10', '2023-12-22 21:25:10', 0, 2, 1535887940687765505, '0,1535887940687765505', 0, 1871369197532028929, 'ips:insert-black', 1, '新增黑名单', '/v1/ips/black/insert', 'plus', 20, 0);
INSERT INTO `boot_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1871372933734146050, 1707428076142559234, 1707428076142559234, '2023-12-22 21:25:04', '2023-12-22 21:25:04', 0, 0, 1535887940687765505, '0,1535887940687765505', 0, 1871369197532028929, 'ips:delete-black', 1, '删除黑名单', '/v1/ips/black/delete', 'delete', 10, 0);
INSERT INTO `boot_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1871370730088767489, 1707428076142559234, 1707428076142559234, '2023-12-22 21:20:41', '2023-12-22 21:23:57', 0, 1, 1535887940687765505, '0,1535887940687765505', 0, 1871369197532028929, 'ips:black-list', 1, '查询黑名单列表', '/v1/ips/black/list', 'search', 30, 0);
INSERT INTO `boot_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1871369197532028929, 1707428076142559234, 1707428076142559234, '2023-12-22 21:17:38', '2023-12-22 21:19:44', 0, 2, 1535887940687765505, '0,1535887940687765505', 0, 1871247748792229889, 'ips:black-view', 0, '黑名单', '/v1/ips/black/view', 'profile', 110, 0);
INSERT INTO `boot_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1871247748792229889, 1707428076142559234, 1707428076142559234, '2023-12-22 17:16:20', '2023-12-22 17:17:28', 0, 2, 1535887940687765505, '0,1535887940687765505', 0, 1535878154046939137, 'ips:view', 0, 'IP管理', '/v1/ips/view', 'ie', 502, 0);

UPDATE `boot_sys_menu` SET `creator` = 1341620898007281665, `editor` = 1341620898007281665, `create_date` = '2023-04-09 14:58:21', `update_date` = '2023-12-23 18:19:36', `del_flag` = 0, `version` = 3, `dept_id` = 0, `dept_path` = '0', `tenant_id` = 0, `pid` = 1564996817056710711, `permission` = 'users:kill-online', `type` = 1, `name` = '强踢在线用户', `url` = '/v1/users/kill-online', `icon` = 'delete', `sort` = 10, `visible` = 0 WHERE `id` = 1564996817056710712;
UPDATE `boot_sys_menu` SET `creator` = 1341620898007281665, `editor` = 1707428076142559234, `create_date` = '2022-07-07 21:34:30', `update_date` = '2023-12-23 18:17:29', `del_flag` = 0, `version` = 3, `dept_id` = 0, `dept_path` = '0', `tenant_id` = 0, `pid` = 1560528267620061186, `permission` = 'resource:task-view', `type` = 0, `name` = '资源流程', `url` = '/v1/resource/task/view', `icon` = 'timeRange', `sort` = 99, `visible` = 0 WHERE `id` = 1545037580289044482;
UPDATE `boot_sys_menu` SET `creator` = 1341620898007281665, `editor` = 1707428076142559234, `create_date` = '2022-08-19 15:34:18', `update_date` = '2023-12-23 18:16:59', `del_flag` = 0, `version` = 3, `dept_id` = 0, `dept_path` = '0', `tenant_id` = 0, `pid` = 1560528267620061186, `permission` = 'resource:heaven-view', `type` = 0, `name` = '资源天堂', `url` = '/v1/resource/heaven/view', `icon` = 'cloud', `sort` = 300, `visible` = 0 WHERE `id` = 1560530418819862529;
UPDATE `boot_sys_menu` SET `creator` = 1341620898007281665, `editor` = 1707428076142559234, `create_date` = '2022-08-29 20:27:49', `update_date` = '2023-12-23 18:16:43', `del_flag` = 0, `version` = 3, `dept_id` = 0, `dept_path` = '0', `tenant_id` = 0, `pid` = 1560528267620061186, `permission` = 'resource:search-view', `type` = 0, `name` = '资源搜索', `url` = '/v1/resource/search/view', `icon` = 'loading', `sort` = 100, `visible` = 0 WHERE `id` = 1564228322215927810;
