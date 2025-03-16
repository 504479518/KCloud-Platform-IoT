/*
 Navicat Premium Dump SQL

 Source Server         : 127.0.0.1
 Source Server Type    : PostgreSQL
 Source Server Version : 160003 (160003)
 Source Host           : 127.0.0.1:5432
 Source Catalog        : kcloud_platform_domain
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 160003 (160003)
 File Encoding         : 65001

 Date: 29/12/2024 15:29:27
*/


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
  "error_message" varchar(2000) COLLATE "pg_catalog"."default"
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
  "service_address" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "service_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "address" varchar(200) COLLATE "pg_catalog"."default" NOT NULL,
  "status" int2 NOT NULL DEFAULT 0,
  "operator" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "error_message" varchar(2000) COLLATE "pg_catalog"."default",
  "profile" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "stack_trace" text COLLATE "pg_catalog"."default",
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
COMMENT ON COLUMN "public"."boot_sys_operate_log"."uri" IS '操作的请求路径';
COMMENT ON COLUMN "public"."boot_sys_operate_log"."method_name" IS '操作的方法名';
COMMENT ON COLUMN "public"."boot_sys_operate_log"."request_type" IS '操作的请求类型';
COMMENT ON COLUMN "public"."boot_sys_operate_log"."request_params" IS '操作的请求参数';
COMMENT ON COLUMN "public"."boot_sys_operate_log"."user_agent" IS '操作的浏览器';
COMMENT ON COLUMN "public"."boot_sys_operate_log"."ip" IS '操作的IP地址';
COMMENT ON COLUMN "public"."boot_sys_operate_log"."address" IS '操作的归属地';
COMMENT ON COLUMN "public"."boot_sys_operate_log"."status" IS '操作状态 0成功 1失败';
COMMENT ON COLUMN "public"."boot_sys_operate_log"."profile" IS '操作的服务环境';
COMMENT ON COLUMN "public"."boot_sys_operate_log"."stack_trace" IS '操作的堆栈信息';
COMMENT ON COLUMN "public"."boot_sys_operate_log"."service_id" IS '操作的服务ID';
COMMENT ON COLUMN "public"."boot_sys_operate_log"."service_address" IS '操作的服务地址';
COMMENT ON COLUMN "public"."boot_sys_operate_log"."operator" IS '操作人';
COMMENT ON COLUMN "public"."boot_sys_operate_log"."error_message" IS '错误信息';
COMMENT ON COLUMN "public"."boot_sys_operate_log"."cost_time" IS '操作的消耗时间(毫秒)';
COMMENT ON TABLE "public"."boot_sys_operate_log" IS '操作日志';

-- ----------------------------
-- Table structure for boot_sys_notice_log
-- ----------------------------
DROP TABLE IF EXISTS "public"."boot_sys_notice_log";
CREATE TABLE "public"."boot_sys_notice_log" (
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
  "code" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "name" varchar(200) COLLATE "pg_catalog"."default" NOT NULL,
  "status" int2 NOT NULL DEFAULT 0,
  "error_message" varchar(2000) COLLATE "pg_catalog"."default",
  "param" json NOT NULL
)
;
COMMENT ON COLUMN "public"."boot_sys_notice_log"."id" IS 'ID';
COMMENT ON COLUMN "public"."boot_sys_notice_log"."creator" IS '创建人';
COMMENT ON COLUMN "public"."boot_sys_notice_log"."editor" IS '编辑人';
COMMENT ON COLUMN "public"."boot_sys_notice_log"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."boot_sys_notice_log"."update_time" IS '修改时间';
COMMENT ON COLUMN "public"."boot_sys_notice_log"."del_flag" IS '删除标识 0未删除 1已删除';
COMMENT ON COLUMN "public"."boot_sys_notice_log"."version" IS '版本号';
COMMENT ON COLUMN "public"."boot_sys_notice_log"."tenant_id" IS '租户ID';
COMMENT ON COLUMN "public"."boot_sys_notice_log"."code" IS '通知编码';
COMMENT ON COLUMN "public"."boot_sys_notice_log"."name" IS '通知名称';
COMMENT ON COLUMN "public"."boot_sys_notice_log"."status" IS '通知状态 0成功 1失败';
COMMENT ON COLUMN "public"."boot_sys_notice_log"."error_message" IS '错误信息';
COMMENT ON COLUMN "public"."boot_sys_notice_log"."param" IS '发送参数';
COMMENT ON TABLE "public"."boot_sys_notice_log" IS '通知日志';

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
  "error_message" varchar(2000) COLLATE "pg_catalog"."default",
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
-- Table structure for boot_iot_device_property_log
-- ----------------------------
DROP TABLE IF EXISTS "public"."boot_iot_device_property_log";
CREATE TABLE "public"."boot_iot_device_property_log" (
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
  "tenant_id" int8 NOT NULL DEFAULT 0
)
;
COMMENT ON COLUMN "public"."boot_iot_device_property_log"."id" IS 'ID';
COMMENT ON COLUMN "public"."boot_iot_device_property_log"."creator" IS '创建人';
COMMENT ON COLUMN "public"."boot_iot_device_property_log"."editor" IS '编辑人';
COMMENT ON COLUMN "public"."boot_iot_device_property_log"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."boot_iot_device_property_log"."update_time" IS '修改时间';
COMMENT ON COLUMN "public"."boot_iot_device_property_log"."del_flag" IS '删除标识 0未删除 1已删除';
COMMENT ON COLUMN "public"."boot_iot_device_property_log"."version" IS '版本号';
COMMENT ON COLUMN "public"."boot_iot_device_property_log"."tenant_id" IS '租户ID';
COMMENT ON TABLE "public"."boot_iot_device_property_log" IS '设备属性日志';

-- ----------------------------
-- Table structure for boot_iot_device_event_log
-- ----------------------------
DROP TABLE IF EXISTS "public"."boot_iot_device_event_log";
CREATE TABLE "public"."boot_iot_device_event_log" (
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
  "tenant_id" int8 NOT NULL DEFAULT 0
)
;
COMMENT ON COLUMN "public"."boot_iot_device_event_log"."id" IS 'ID';
COMMENT ON COLUMN "public"."boot_iot_device_event_log"."creator" IS '创建人';
COMMENT ON COLUMN "public"."boot_iot_device_event_log"."editor" IS '编辑人';
COMMENT ON COLUMN "public"."boot_iot_device_event_log"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."boot_iot_device_event_log"."update_time" IS '修改时间';
COMMENT ON COLUMN "public"."boot_iot_device_event_log"."del_flag" IS '删除标识 0未删除 1已删除';
COMMENT ON COLUMN "public"."boot_iot_device_event_log"."version" IS '版本号';
COMMENT ON COLUMN "public"."boot_iot_device_event_log"."tenant_id" IS '租户ID';
COMMENT ON TABLE "public"."boot_iot_device_event_log" IS '设备事件日志';

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
  "type" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "topic" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "source_prefix" varchar(20) COLLATE "pg_catalog"."default" NOT NULL,
  "payload" json,
  "tag" varchar(50) COLLATE "pg_catalog"."default",
  "status" int2 NOT NULL DEFAULT 0
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
COMMENT ON COLUMN "public"."boot_domain_event"."type" IS '事件类型';
COMMENT ON COLUMN "public"."boot_domain_event"."topic" IS 'MQ主题';
COMMENT ON COLUMN "public"."boot_domain_event"."source_prefix" IS '数据源前缀';
COMMENT ON COLUMN "public"."boot_domain_event"."payload" IS '内容';
COMMENT ON COLUMN "public"."boot_domain_event"."tag" IS 'MQ标签';
COMMENT ON COLUMN "public"."boot_domain_event"."status" IS '事件状态 0已创建 1已消费';
COMMENT ON TABLE "public"."boot_domain_event" IS '领域事件';

-- ----------------------------
-- Primary Key structure for table boot_sys_sql_log
-- ----------------------------
ALTER TABLE "public"."boot_sys_sql_log" ADD CONSTRAINT "boot_sys_sql_log_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table boot_sys_oss_log
-- ----------------------------
ALTER TABLE "public"."boot_sys_oss_log" ADD CONSTRAINT "boot_sys_oss_log_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table boot_sys_operate_log
-- ----------------------------
ALTER TABLE "public"."boot_sys_operate_log" ADD CONSTRAINT "boot_sys_operate_log_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table boot_sys_notice_log
-- ----------------------------
ALTER TABLE "public"."boot_sys_notice_log" ADD CONSTRAINT "boot_sys_notice_log_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table boot_sys_login_log
-- ----------------------------
ALTER TABLE "public"."boot_sys_login_log" ADD CONSTRAINT "boot_sys_login_log_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table boot_iot_device_property_log
-- ----------------------------
ALTER TABLE "public"."boot_iot_device_property_log" ADD CONSTRAINT "boot_iot_device_property_log_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table boot_iot_device_event_log
-- ----------------------------
ALTER TABLE "public"."boot_iot_device_event_log" ADD CONSTRAINT "boot_iot_device_event_log_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table boot_domain_event
-- ----------------------------
ALTER TABLE "public"."boot_domain_event" ADD CONSTRAINT "boot_domain_event_pkey" PRIMARY KEY ("id");
