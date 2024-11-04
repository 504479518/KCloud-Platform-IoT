/*
 Navicat Premium Dump SQL

 Source Server         : 127.0.0.1
 Source Server Type    : PostgreSQL
 Source Server Version : 160002 (160002)
 Source Host           : 127.0.0.1:5432
 Source Catalog        : kcloud_platform_domain
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 160002 (160002)
 File Encoding         : 65001

 Date: 04/11/2024 09:46:59
*/


-- ----------------------------
-- Sequence structure for boot_domain_event_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."boot_domain_event_id_seq";
CREATE SEQUENCE "public"."boot_domain_event_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for boot_sys_api_log_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."boot_sys_api_log_id_seq";
CREATE SEQUENCE "public"."boot_sys_api_log_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for boot_sys_api_log_id_seq1
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."boot_sys_api_log_id_seq1";
CREATE SEQUENCE "public"."boot_sys_api_log_id_seq1" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for boot_sys_api_log_status_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."boot_sys_api_log_status_seq";
CREATE SEQUENCE "public"."boot_sys_api_log_status_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 32767
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for boot_sys_login_log_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."boot_sys_login_log_id_seq";
CREATE SEQUENCE "public"."boot_sys_login_log_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for boot_sys_operate_log_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."boot_sys_operate_log_id_seq";
CREATE SEQUENCE "public"."boot_sys_operate_log_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for boot_sys_oss_log_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."boot_sys_oss_log_id_seq";
CREATE SEQUENCE "public"."boot_sys_oss_log_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for boot_sys_sql_log_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."boot_sys_sql_log_id_seq";
CREATE SEQUENCE "public"."boot_sys_sql_log_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Table structure for boot_domain_event
-- ----------------------------
DROP TABLE IF EXISTS "public"."boot_domain_event";
CREATE TABLE "public"."boot_domain_event" (
  "id" int8 NOT NULL GENERATED BY DEFAULT AS IDENTITY (
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1
),
  "creator" int8 DEFAULT 0,
  "editor" int8 DEFAULT 0,
  "create_time" timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  "update_time" timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  "del_flag" int2 NOT NULL DEFAULT 0,
  "version" int4 NOT NULL DEFAULT 0,
  "tenant_id" int8 DEFAULT 0,
  "aggregate_id" int8,
  "event_type" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "topic" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "source_name" varchar(20) COLLATE "pg_catalog"."default" NOT NULL,
  "attribute" json,
  "service_id" varchar(20) COLLATE "pg_catalog"."default" NOT NULL,
  "tag" varchar(50) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."boot_domain_event"."id" IS 'ID';
COMMENT ON COLUMN "public"."boot_domain_event"."creator" IS '创建人';
COMMENT ON COLUMN "public"."boot_domain_event"."editor" IS '编辑人';
COMMENT ON COLUMN "public"."boot_domain_event"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."boot_domain_event"."update_time" IS '修改时间';
COMMENT ON COLUMN "public"."boot_domain_event"."del_flag" IS '删除标识 0未删除 1已删除';
COMMENT ON COLUMN "public"."boot_domain_event"."version" IS '版本号';
COMMENT ON COLUMN "public"."boot_domain_event"."tenant_id" IS '租户ID';
COMMENT ON COLUMN "public"."boot_domain_event"."aggregate_id" IS '聚合根ID';
COMMENT ON COLUMN "public"."boot_domain_event"."event_type" IS '事件类型';
COMMENT ON COLUMN "public"."boot_domain_event"."topic" IS 'MQ主题';
COMMENT ON COLUMN "public"."boot_domain_event"."source_name" IS '数据源名称';
COMMENT ON COLUMN "public"."boot_domain_event"."attribute" IS '扩展属性';
COMMENT ON COLUMN "public"."boot_domain_event"."service_id" IS '服务ID';
COMMENT ON COLUMN "public"."boot_domain_event"."tag" IS '标签';
COMMENT ON TABLE "public"."boot_domain_event" IS '领域事件';

-- ----------------------------
-- Records of boot_domain_event
-- ----------------------------
INSERT INTO "public"."boot_domain_event" VALUES (2089475301879955458, 1, 1, '2024-10-18 19:35:32.140931', '2024-10-18 19:35:32.141449', 0, 0, 0, 2089475295882100738, 'LOGIN', 'laokou_log_topic', 'master', '{"id":2089475301879955458,"aggregateId":2089475295882100738,"eventType":"LOGIN","topic":"laokou_log_topic","tag":"loginLog","sourceName":"master","serviceId":"laokou-auth","creator":1,"editor":1,"tenantId":0,"createTime":"2024-10-18T11:35:31.895779900Z","updateTime":"2024-10-18T11:35:31.895779900Z","username":"2413176044@qq.com","ip":"127.0.0.1","address":"内网IP","browser":"Edge","os":"Win10","status":0,"errorMessage":"","type":"mail"}', 'laokou-auth', 'loginLog');
INSERT INTO "public"."boot_domain_event" VALUES (2089475337263104002, 1, 1, '2024-10-18 19:35:36.14336', '2024-10-18 19:35:36.14336', 0, 0, 0, 2089475335686045697, 'LOGIN', 'laokou_log_topic', 'master', '{"id":2089475337263104002,"aggregateId":2089475335686045697,"eventType":"LOGIN","topic":"laokou_log_topic","tag":"loginLog","sourceName":"master","serviceId":"laokou-auth","creator":1,"editor":1,"tenantId":0,"createTime":"2024-10-18T11:35:36.120169100Z","updateTime":"2024-10-18T11:35:36.120169100Z","username":"admin","ip":"127.0.0.1","address":"内网IP","browser":"Edge","os":"Win10","status":0,"errorMessage":"","type":"password"}', 'laokou-auth', 'loginLog');
INSERT INTO "public"."boot_domain_event" VALUES (2092804706513518594, 1, 1, '2024-10-23 09:50:28.064167', '2024-10-23 09:50:28.064167', 0, 0, 0, 2092804700977037314, 'LOGIN', 'laokou_log_topic', 'master', '{"id":2092804706513518594,"aggregateId":2092804700977037314,"eventType":"LOGIN","topic":"laokou_log_topic","tag":"loginLog","sourceName":"master","serviceId":"laokou-auth","creator":1,"editor":1,"tenantId":0,"createTime":"2024-10-23T01:50:27.856897900Z","updateTime":"2024-10-23T01:50:27.856897900Z","username":"2413176044@qq.com","ip":"127.0.0.1","address":"内网IP","browser":"Edge","os":"Win10","status":0,"errorMessage":"","type":"mail"}', 'laokou-auth', 'loginLog');
INSERT INTO "public"."boot_domain_event" VALUES (2092804735798149121, 1, 1, '2024-10-23 09:50:31.581652', '2024-10-23 09:50:31.581652', 0, 0, 0, 2092804731234746370, 'LOGIN', 'laokou_log_topic', 'master', '{"id":2092804735798149121,"aggregateId":2092804731234746370,"eventType":"LOGIN","topic":"laokou_log_topic","tag":"loginLog","sourceName":"master","serviceId":"laokou-auth","creator":1,"editor":1,"tenantId":0,"createTime":"2024-10-23T01:50:31.362522800Z","updateTime":"2024-10-23T01:50:31.362522800Z","username":"admin","ip":"127.0.0.1","address":"内网IP","browser":"Edge","os":"Win10","status":0,"errorMessage":"","type":"password"}', 'laokou-auth', 'loginLog');
INSERT INTO "public"."boot_domain_event" VALUES (2092862424246960130, 1, 1, '2024-10-23 11:45:08.541562', '2024-10-23 11:45:08.541562', 0, 0, 0, 2092862420295925762, 'LOGIN', 'laokou_log_topic', 'master', '{"id":2092862424246960130,"aggregateId":2092862420295925762,"eventType":"LOGIN","topic":"laokou_log_topic","tag":"loginLog","sourceName":"master","serviceId":"laokou-auth","creator":1,"editor":1,"tenantId":0,"createTime":"2024-10-23T03:45:08.343500200Z","updateTime":"2024-10-23T03:45:08.343500200Z","username":"2413176044@qq.com","ip":"127.0.0.1","address":"内网IP","browser":"Edge","os":"Win10","status":0,"errorMessage":"","type":"mail"}', 'laokou-auth', 'loginLog');
INSERT INTO "public"."boot_domain_event" VALUES (2092862457205800961, 1, 1, '2024-10-23 11:45:12.300803', '2024-10-23 11:45:12.301361', 0, 0, 0, 2092862455880400897, 'LOGIN', 'laokou_log_topic', 'master', '{"id":2092862457205800961,"aggregateId":2092862455880400897,"eventType":"LOGIN","topic":"laokou_log_topic","tag":"loginLog","sourceName":"master","serviceId":"laokou-auth","creator":1,"editor":1,"tenantId":0,"createTime":"2024-10-23T03:45:12.282713600Z","updateTime":"2024-10-23T03:45:12.282713600Z","username":"admin","ip":"127.0.0.1","address":"内网IP","browser":"Edge","os":"Win10","status":0,"errorMessage":"","type":"password"}', 'laokou-auth', 'loginLog');
INSERT INTO "public"."boot_domain_event" VALUES (2092869653608005634, 1, 1, '2024-10-23 11:59:30.287448', '2024-10-23 11:59:30.287448', 0, 0, 0, 2092869650076401665, 'LOGIN', 'laokou_log_topic', 'master', '{"id":2092869653608005634,"aggregateId":2092869650076401665,"eventType":"LOGIN","topic":"laokou_log_topic","tag":"loginLog","sourceName":"master","serviceId":"laokou-auth","creator":1,"editor":1,"tenantId":0,"createTime":"2024-10-23T03:59:30.154541700Z","updateTime":"2024-10-23T03:59:30.154541700Z","username":"2413176044@qq.com","ip":"127.0.0.1","address":"内网IP","browser":"Edge","os":"Win10","status":0,"errorMessage":"","type":"mail"}', 'laokou-auth', 'loginLog');
INSERT INTO "public"."boot_domain_event" VALUES (2092869673136685058, 1, 1, '2024-10-23 11:59:32.520518', '2024-10-23 11:59:32.520518', 0, 0, 0, 2092869671517683714, 'LOGIN', 'laokou_log_topic', 'master', '{"id":2092869673136685058,"aggregateId":2092869671517683714,"eventType":"LOGIN","topic":"laokou_log_topic","tag":"loginLog","sourceName":"master","serviceId":"laokou-auth","creator":1,"editor":1,"tenantId":0,"createTime":"2024-10-23T03:59:32.490585600Z","updateTime":"2024-10-23T03:59:32.490585600Z","username":"admin","ip":"127.0.0.1","address":"内网IP","browser":"Edge","os":"Win10","status":0,"errorMessage":"","type":"password"}', 'laokou-auth', 'loginLog');
INSERT INTO "public"."boot_domain_event" VALUES (2097319050219618305, NULL, NULL, '2024-10-29 15:20:04.825897', '2024-10-29 15:20:04.825897', 0, 0, 0, 2097318826252173314, 'API', 'laokou_log_topic', 'master', '{"id":2097319050219618305,"aggregateId":2097318826252173314,"eventType":"API","topic":"laokou_log_topic","tag":"apiLog","sourceName":"master","serviceId":"laokou-auth","creator":null,"editor":null,"tenantId":0,"createTime":"2024-10-29T07:19:39.569101200Z","updateTime":"2024-10-29T07:19:39.569101200Z","code":"sendMailCaptcha","name":"发送邮箱验证码（QQ邮箱）","status":0,"errorMessage":"","param":"{\"mail\":\"2413176044@qq.com\",\"content\":\"验证码：189440，5分钟内容有效，您正在登录，若非本人操作，请勿泄露。\"}"}', 'laokou-auth', 'apiLog');

-- ----------------------------
-- Table structure for boot_sys_api_log
-- ----------------------------
DROP TABLE IF EXISTS "public"."boot_sys_api_log";
CREATE TABLE "public"."boot_sys_api_log" (
  "id" int8 NOT NULL GENERATED BY DEFAULT AS IDENTITY (
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1
),
  "creator" int8 DEFAULT 0,
  "editor" int8 DEFAULT 0,
  "create_time" timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  "update_time" timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  "del_flag" int2 NOT NULL DEFAULT 0,
  "version" int4 NOT NULL DEFAULT 0,
  "tenant_id" int8 DEFAULT 0,
  "code" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "name" varchar(200) COLLATE "pg_catalog"."default" NOT NULL,
  "status" int2 NOT NULL DEFAULT nextval('boot_sys_api_log_status_seq'::regclass),
  "error_message" varchar(500) COLLATE "pg_catalog"."default" NOT NULL,
  "param" json
)
;
COMMENT ON COLUMN "public"."boot_sys_api_log"."id" IS 'ID';
COMMENT ON COLUMN "public"."boot_sys_api_log"."creator" IS '创建人';
COMMENT ON COLUMN "public"."boot_sys_api_log"."editor" IS '编辑人';
COMMENT ON COLUMN "public"."boot_sys_api_log"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."boot_sys_api_log"."update_time" IS '修改时间';
COMMENT ON COLUMN "public"."boot_sys_api_log"."del_flag" IS '删除标识 0未删除 1已删除';
COMMENT ON COLUMN "public"."boot_sys_api_log"."version" IS '版本号';
COMMENT ON COLUMN "public"."boot_sys_api_log"."tenant_id" IS '租户ID';
COMMENT ON COLUMN "public"."boot_sys_api_log"."code" IS 'Api编码';
COMMENT ON COLUMN "public"."boot_sys_api_log"."name" IS 'Api名称';
COMMENT ON COLUMN "public"."boot_sys_api_log"."status" IS 'Api状态 0成功 1失败';
COMMENT ON COLUMN "public"."boot_sys_api_log"."error_message" IS '错误信息';
COMMENT ON COLUMN "public"."boot_sys_api_log"."param" IS 'Api参数';
COMMENT ON TABLE "public"."boot_sys_api_log" IS 'API日志';

-- ----------------------------
-- Records of boot_sys_api_log
-- ----------------------------

-- ----------------------------
-- Table structure for boot_sys_login_log
-- ----------------------------
DROP TABLE IF EXISTS "public"."boot_sys_login_log";
CREATE TABLE "public"."boot_sys_login_log" (
  "id" int8 NOT NULL GENERATED BY DEFAULT AS IDENTITY (
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1
),
  "creator" int8 DEFAULT 0,
  "editor" int8 DEFAULT 0,
  "create_time" timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  "update_time" timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  "del_flag" int2 NOT NULL DEFAULT 0,
  "version" int4 NOT NULL DEFAULT 0,
  "tenant_id" int8 NOT NULL DEFAULT 0,
  "username" varchar(200) COLLATE "pg_catalog"."default" NOT NULL,
  "ip" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "address" varchar(200) COLLATE "pg_catalog"."default" NOT NULL,
  "browser" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "os" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "status" int2 NOT NULL DEFAULT 0,
  "error_message" varchar(500) COLLATE "pg_catalog"."default" NOT NULL,
  "type" varchar(50) COLLATE "pg_catalog"."default" NOT NULL
)
;
COMMENT ON COLUMN "public"."boot_sys_login_log"."id" IS 'ID';
COMMENT ON COLUMN "public"."boot_sys_login_log"."creator" IS '创建人';
COMMENT ON COLUMN "public"."boot_sys_login_log"."editor" IS '编辑人';
COMMENT ON COLUMN "public"."boot_sys_login_log"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."boot_sys_login_log"."update_time" IS '修改时间';
COMMENT ON COLUMN "public"."boot_sys_login_log"."del_flag" IS '删除标识 0未删除 1已删除';
COMMENT ON COLUMN "public"."boot_sys_login_log"."version" IS '版本号';
COMMENT ON COLUMN "public"."boot_sys_login_log"."tenant_id" IS '租户ID';
COMMENT ON COLUMN "public"."boot_sys_login_log"."username" IS '登录的用户名';
COMMENT ON COLUMN "public"."boot_sys_login_log"."ip" IS '登录的IP地址';
COMMENT ON COLUMN "public"."boot_sys_login_log"."address" IS '登录的归属地';
COMMENT ON COLUMN "public"."boot_sys_login_log"."browser" IS '登录的浏览器';
COMMENT ON COLUMN "public"."boot_sys_login_log"."os" IS '登录的操作系统';
COMMENT ON COLUMN "public"."boot_sys_login_log"."status" IS '登录状态 0登录成功 1登录失败';
COMMENT ON COLUMN "public"."boot_sys_login_log"."error_message" IS '错误信息';
COMMENT ON COLUMN "public"."boot_sys_login_log"."type" IS '登录类型';
COMMENT ON TABLE "public"."boot_sys_login_log" IS '登录日志';

-- ----------------------------
-- Records of boot_sys_login_log
-- ----------------------------

-- ----------------------------
-- Table structure for boot_sys_operate_log
-- ----------------------------
DROP TABLE IF EXISTS "public"."boot_sys_operate_log";
CREATE TABLE "public"."boot_sys_operate_log" (
  "id" int8 NOT NULL GENERATED BY DEFAULT AS IDENTITY (
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1
),
  "creator" int8 NOT NULL DEFAULT 0,
  "editor" int8 NOT NULL DEFAULT 0,
  "create_time" timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  "update_time" timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  "del_flag" int2 NOT NULL DEFAULT 0,
  "version" int4 NOT NULL DEFAULT 0,
  "tenant_id" int8 NOT NULL DEFAULT 0,
  "name" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "module_name" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "uri" varchar(200) COLLATE "pg_catalog"."default" NOT NULL,
  "method_name" varchar(1000) COLLATE "pg_catalog"."default" NOT NULL,
  "request_type" varchar(20) COLLATE "pg_catalog"."default" NOT NULL,
  "request_params" text COLLATE "pg_catalog"."default",
  "user_agent" varchar(500) COLLATE "pg_catalog"."default" NOT NULL,
  "ip" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "address" varchar(200) COLLATE "pg_catalog"."default" NOT NULL,
  "status" int2 NOT NULL DEFAULT 0,
  "operator" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "error_message" varchar(500) COLLATE "pg_catalog"."default",
  "cost_time" int8 NOT NULL
)
;
COMMENT ON COLUMN "public"."boot_sys_operate_log"."id" IS 'ID';
COMMENT ON COLUMN "public"."boot_sys_operate_log"."creator" IS '创建人';
COMMENT ON COLUMN "public"."boot_sys_operate_log"."editor" IS '编辑人';
COMMENT ON COLUMN "public"."boot_sys_operate_log"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."boot_sys_operate_log"."update_time" IS '修改时间';
COMMENT ON COLUMN "public"."boot_sys_operate_log"."del_flag" IS '删除标识 0未删除 1已删除';
COMMENT ON COLUMN "public"."boot_sys_operate_log"."version" IS '版本号';
COMMENT ON COLUMN "public"."boot_sys_operate_log"."tenant_id" IS '租户ID';
COMMENT ON COLUMN "public"."boot_sys_operate_log"."name" IS '操作名称';
COMMENT ON COLUMN "public"."boot_sys_operate_log"."module_name" IS '操作的模块名称';
COMMENT ON COLUMN "public"."boot_sys_operate_log"."uri" IS '操作的URI';
COMMENT ON COLUMN "public"."boot_sys_operate_log"."method_name" IS '操作的方法名';
COMMENT ON COLUMN "public"."boot_sys_operate_log"."request_type" IS '操作的请求类型';
COMMENT ON COLUMN "public"."boot_sys_operate_log"."request_params" IS '操作的请求参数';
COMMENT ON COLUMN "public"."boot_sys_operate_log"."user_agent" IS '操作的浏览器';
COMMENT ON COLUMN "public"."boot_sys_operate_log"."ip" IS '操作的IP地址';
COMMENT ON COLUMN "public"."boot_sys_operate_log"."address" IS '操作的归属地';
COMMENT ON COLUMN "public"."boot_sys_operate_log"."status" IS '操作状态 0成功 1失败';
COMMENT ON COLUMN "public"."boot_sys_operate_log"."operator" IS '操作人';
COMMENT ON COLUMN "public"."boot_sys_operate_log"."error_message" IS '错误信息';
COMMENT ON COLUMN "public"."boot_sys_operate_log"."cost_time" IS '操作的消耗时间(毫秒)';
COMMENT ON TABLE "public"."boot_sys_operate_log" IS '操作日志';

-- ----------------------------
-- Records of boot_sys_operate_log
-- ----------------------------

-- ----------------------------
-- Table structure for boot_sys_oss_log
-- ----------------------------
DROP TABLE IF EXISTS "public"."boot_sys_oss_log";
CREATE TABLE "public"."boot_sys_oss_log" (
  "id" int8 NOT NULL GENERATED BY DEFAULT AS IDENTITY (
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1
),
  "creator" int8 NOT NULL DEFAULT 0,
  "editor" int8 NOT NULL DEFAULT 0,
  "create_time" timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  "update_time" timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  "del_flag" int2 NOT NULL DEFAULT 0,
  "version" int4 NOT NULL DEFAULT 0,
  "tenant_id" int8 NOT NULL DEFAULT 0,
  "name" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "md5" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "url" varchar(200) COLLATE "pg_catalog"."default" NOT NULL,
  "size" int8 NOT NULL,
  "status" int2 NOT NULL DEFAULT 0,
  "error_message" varchar(500) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."boot_sys_oss_log"."id" IS 'ID';
COMMENT ON COLUMN "public"."boot_sys_oss_log"."creator" IS '创建人';
COMMENT ON COLUMN "public"."boot_sys_oss_log"."editor" IS '编辑人';
COMMENT ON COLUMN "public"."boot_sys_oss_log"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."boot_sys_oss_log"."update_time" IS '修改时间';
COMMENT ON COLUMN "public"."boot_sys_oss_log"."del_flag" IS '删除标识 0未删除 1已删除';
COMMENT ON COLUMN "public"."boot_sys_oss_log"."version" IS '版本号';
COMMENT ON COLUMN "public"."boot_sys_oss_log"."tenant_id" IS '租户ID';
COMMENT ON COLUMN "public"."boot_sys_oss_log"."name" IS '文件名称';
COMMENT ON COLUMN "public"."boot_sys_oss_log"."md5" IS '文件的MD5标识';
COMMENT ON COLUMN "public"."boot_sys_oss_log"."url" IS '文件的URL';
COMMENT ON COLUMN "public"."boot_sys_oss_log"."size" IS '文件大小';
COMMENT ON COLUMN "public"."boot_sys_oss_log"."status" IS '上传状态 0成功 1失败';
COMMENT ON COLUMN "public"."boot_sys_oss_log"."error_message" IS '错误信息';
COMMENT ON TABLE "public"."boot_sys_oss_log" IS 'OSS日志';

-- ----------------------------
-- Records of boot_sys_oss_log
-- ----------------------------

-- ----------------------------
-- Table structure for boot_sys_sql_log
-- ----------------------------
DROP TABLE IF EXISTS "public"."boot_sys_sql_log";
CREATE TABLE "public"."boot_sys_sql_log" (
  "id" int8 NOT NULL GENERATED BY DEFAULT AS IDENTITY (
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1
),
  "creator" int8 DEFAULT 0,
  "editor" int8 DEFAULT 0,
  "create_time" timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  "update_time" timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  "del_flag" int2 NOT NULL DEFAULT 0,
  "version" int4 NOT NULL DEFAULT 0,
  "tenant_id" int8 DEFAULT 0,
  "service_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "sql" text COLLATE "pg_catalog"."default" NOT NULL,
  "cost_time" int8 NOT NULL DEFAULT 0
)
;
COMMENT ON COLUMN "public"."boot_sys_sql_log"."id" IS 'ID';
COMMENT ON COLUMN "public"."boot_sys_sql_log"."creator" IS '创建人';
COMMENT ON COLUMN "public"."boot_sys_sql_log"."editor" IS '编辑人';
COMMENT ON COLUMN "public"."boot_sys_sql_log"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."boot_sys_sql_log"."update_time" IS '修改时间';
COMMENT ON COLUMN "public"."boot_sys_sql_log"."del_flag" IS '删除标识 0未删除 1已删除';
COMMENT ON COLUMN "public"."boot_sys_sql_log"."version" IS '版本号';
COMMENT ON COLUMN "public"."boot_sys_sql_log"."tenant_id" IS '租户ID';
COMMENT ON COLUMN "public"."boot_sys_sql_log"."service_id" IS '服务ID';
COMMENT ON COLUMN "public"."boot_sys_sql_log"."sql" IS 'SQL';
COMMENT ON COLUMN "public"."boot_sys_sql_log"."cost_time" IS '消耗时间';
COMMENT ON TABLE "public"."boot_sys_sql_log" IS 'SQL日志';

-- ----------------------------
-- Records of boot_sys_sql_log
-- ----------------------------

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."boot_domain_event_id_seq"
OWNED BY "public"."boot_domain_event"."id";
SELECT setval('"public"."boot_domain_event_id_seq"', 1, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."boot_sys_api_log_id_seq"
OWNED BY "public"."boot_sys_api_log"."id";
SELECT setval('"public"."boot_sys_api_log_id_seq"', 1, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."boot_sys_api_log_id_seq1"
OWNED BY "public"."boot_sys_api_log"."id";
SELECT setval('"public"."boot_sys_api_log_id_seq1"', 1, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."boot_sys_api_log_status_seq"
OWNED BY "public"."boot_sys_api_log"."status";
SELECT setval('"public"."boot_sys_api_log_status_seq"', 1, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."boot_sys_login_log_id_seq"
OWNED BY "public"."boot_sys_login_log"."id";
SELECT setval('"public"."boot_sys_login_log_id_seq"', 1, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."boot_sys_operate_log_id_seq"
OWNED BY "public"."boot_sys_operate_log"."id";
SELECT setval('"public"."boot_sys_operate_log_id_seq"', 1, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."boot_sys_oss_log_id_seq"
OWNED BY "public"."boot_sys_oss_log"."id";
SELECT setval('"public"."boot_sys_oss_log_id_seq"', 1, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."boot_sys_sql_log_id_seq"
OWNED BY "public"."boot_sys_sql_log"."id";
SELECT setval('"public"."boot_sys_sql_log_id_seq"', 1, false);

-- ----------------------------
-- Auto increment value for boot_domain_event
-- ----------------------------
SELECT setval('"public"."boot_domain_event_id_seq"', 1, false);

-- ----------------------------
-- Primary Key structure for table boot_domain_event
-- ----------------------------
ALTER TABLE "public"."boot_domain_event" ADD CONSTRAINT "boot_domain_event_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Auto increment value for boot_sys_api_log
-- ----------------------------
SELECT setval('"public"."boot_sys_api_log_id_seq1"', 1, false);

-- ----------------------------
-- Auto increment value for boot_sys_login_log
-- ----------------------------
SELECT setval('"public"."boot_sys_login_log_id_seq"', 1, false);

-- ----------------------------
-- Auto increment value for boot_sys_operate_log
-- ----------------------------
SELECT setval('"public"."boot_sys_operate_log_id_seq"', 1, false);

-- ----------------------------
-- Auto increment value for boot_sys_oss_log
-- ----------------------------
SELECT setval('"public"."boot_sys_oss_log_id_seq"', 1, false);

-- ----------------------------
-- Auto increment value for boot_sys_sql_log
-- ----------------------------
SELECT setval('"public"."boot_sys_sql_log_id_seq"', 1, false);
