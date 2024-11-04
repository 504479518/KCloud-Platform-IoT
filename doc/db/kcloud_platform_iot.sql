/*
 Navicat Premium Dump SQL

 Source Server         : 127.0.0.1
 Source Server Type    : PostgreSQL
 Source Server Version : 160003 (160003)
 Source Host           : 127.0.0.1:5432
 Source Catalog        : kcloud_platform_iot
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 160003 (160003)
 File Encoding         : 65001

 Date: 04/11/2024 22:32:09
*/


-- ----------------------------
-- Sequence structure for boot_iot_device_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."boot_iot_device_id_seq";
CREATE SEQUENCE "public"."boot_iot_device_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for boot_iot_device_id_seq1
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."boot_iot_device_id_seq1";
CREATE SEQUENCE "public"."boot_iot_device_id_seq1" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for boot_iot_model_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."boot_iot_model_id_seq";
CREATE SEQUENCE "public"."boot_iot_model_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for boot_iot_network_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."boot_iot_network_id_seq";
CREATE SEQUENCE "public"."boot_iot_network_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for boot_iot_product_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."boot_iot_product_id_seq";
CREATE SEQUENCE "public"."boot_iot_product_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for boot_iot_product_type_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."boot_iot_product_type_id_seq";
CREATE SEQUENCE "public"."boot_iot_product_type_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for boot_iot_protocol_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."boot_iot_protocol_id_seq";
CREATE SEQUENCE "public"."boot_iot_protocol_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Table structure for boot_iot_device
-- ----------------------------
DROP TABLE IF EXISTS "public"."boot_iot_device";
CREATE TABLE "public"."boot_iot_device" (
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
  "sn" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "name" varchar(20) COLLATE "pg_catalog"."default" NOT NULL,
  "status" int2 NOT NULL DEFAULT 0,
  "longitude" float8,
  "latitude" float8,
  "img_url" varchar(400) COLLATE "pg_catalog"."default",
  "address" varchar(200) COLLATE "pg_catalog"."default",
  "remark" varchar(400) COLLATE "pg_catalog"."default",
  "product_id" int8 NOT NULL
)
;
COMMENT ON COLUMN "public"."boot_iot_device"."id" IS 'ID';
COMMENT ON COLUMN "public"."boot_iot_device"."creator" IS '创建人';
COMMENT ON COLUMN "public"."boot_iot_device"."editor" IS '编辑人';
COMMENT ON COLUMN "public"."boot_iot_device"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."boot_iot_device"."update_time" IS '修改时间';
COMMENT ON COLUMN "public"."boot_iot_device"."del_flag" IS '删除标识 0未删除 1已删除';
COMMENT ON COLUMN "public"."boot_iot_device"."version" IS '版本号';
COMMENT ON COLUMN "public"."boot_iot_device"."tenant_id" IS '租户ID';
COMMENT ON COLUMN "public"."boot_iot_device"."sn" IS '设备序列号';
COMMENT ON COLUMN "public"."boot_iot_device"."name" IS '设备名称';
COMMENT ON COLUMN "public"."boot_iot_device"."status" IS '设备状态 0在线 1离线';
COMMENT ON COLUMN "public"."boot_iot_device"."longitude" IS '设备经度';
COMMENT ON COLUMN "public"."boot_iot_device"."latitude" IS '设备纬度';
COMMENT ON COLUMN "public"."boot_iot_device"."img_url" IS '设备图片URL';
COMMENT ON COLUMN "public"."boot_iot_device"."address" IS '设备地址';
COMMENT ON COLUMN "public"."boot_iot_device"."remark" IS '设备备注';
COMMENT ON COLUMN "public"."boot_iot_device"."product_id" IS '产品ID';
COMMENT ON TABLE "public"."boot_iot_device" IS '设备';

-- ----------------------------
-- Records of boot_iot_device
-- ----------------------------
INSERT INTO "public"."boot_iot_device" VALUES (1, 1341620898007281665, 1341620898007281665, '2024-05-11 03:56:15.821857', '2024-05-11 03:56:15.821857', 0, 0, 0, '139c5556-8494-5753-ac97-de09f2a6a929', 'HFCL设备', 0, NULL, NULL, NULL, NULL, NULL, 1);

-- ----------------------------
-- Table structure for boot_iot_model
-- ----------------------------
DROP TABLE IF EXISTS "public"."boot_iot_model";
CREATE TABLE "public"."boot_iot_model" (
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
COMMENT ON COLUMN "public"."boot_iot_model"."id" IS 'ID';
COMMENT ON COLUMN "public"."boot_iot_model"."creator" IS '创建人';
COMMENT ON COLUMN "public"."boot_iot_model"."editor" IS '编辑人';
COMMENT ON COLUMN "public"."boot_iot_model"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."boot_iot_model"."update_time" IS '修改时间';
COMMENT ON COLUMN "public"."boot_iot_model"."del_flag" IS '删除标识 0未删除 1已删除';
COMMENT ON COLUMN "public"."boot_iot_model"."version" IS '版本号';
COMMENT ON COLUMN "public"."boot_iot_model"."tenant_id" IS '租户ID';
COMMENT ON TABLE "public"."boot_iot_model" IS '模型';

-- ----------------------------
-- Records of boot_iot_model
-- ----------------------------

-- ----------------------------
-- Table structure for boot_iot_network
-- ----------------------------
DROP TABLE IF EXISTS "public"."boot_iot_network";
CREATE TABLE "public"."boot_iot_network" (
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
COMMENT ON COLUMN "public"."boot_iot_network"."id" IS 'ID';
COMMENT ON COLUMN "public"."boot_iot_network"."creator" IS '创建人';
COMMENT ON COLUMN "public"."boot_iot_network"."editor" IS '编辑人';
COMMENT ON COLUMN "public"."boot_iot_network"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."boot_iot_network"."update_time" IS '修改时间';
COMMENT ON COLUMN "public"."boot_iot_network"."del_flag" IS '删除标识 0未删除 1已删除';
COMMENT ON COLUMN "public"."boot_iot_network"."version" IS '版本号';
COMMENT ON COLUMN "public"."boot_iot_network"."tenant_id" IS '租户ID';
COMMENT ON TABLE "public"."boot_iot_network" IS '网络';

-- ----------------------------
-- Records of boot_iot_network
-- ----------------------------

-- ----------------------------
-- Table structure for boot_iot_product
-- ----------------------------
DROP TABLE IF EXISTS "public"."boot_iot_product";
CREATE TABLE "public"."boot_iot_product" (
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
COMMENT ON COLUMN "public"."boot_iot_product"."id" IS 'ID';
COMMENT ON COLUMN "public"."boot_iot_product"."creator" IS '创建人';
COMMENT ON COLUMN "public"."boot_iot_product"."editor" IS '编辑人';
COMMENT ON COLUMN "public"."boot_iot_product"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."boot_iot_product"."update_time" IS '修改时间';
COMMENT ON COLUMN "public"."boot_iot_product"."del_flag" IS '删除标识 0未删除 1已删除';
COMMENT ON COLUMN "public"."boot_iot_product"."version" IS '版本号';
COMMENT ON COLUMN "public"."boot_iot_product"."tenant_id" IS '租户ID';
COMMENT ON TABLE "public"."boot_iot_product" IS '产品';

-- ----------------------------
-- Records of boot_iot_product
-- ----------------------------

-- ----------------------------
-- Table structure for boot_iot_product_type
-- ----------------------------
DROP TABLE IF EXISTS "public"."boot_iot_product_type";
CREATE TABLE "public"."boot_iot_product_type" (
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
COMMENT ON COLUMN "public"."boot_iot_product_type"."id" IS 'ID';
COMMENT ON COLUMN "public"."boot_iot_product_type"."creator" IS '创建人';
COMMENT ON COLUMN "public"."boot_iot_product_type"."editor" IS '编辑人';
COMMENT ON COLUMN "public"."boot_iot_product_type"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."boot_iot_product_type"."update_time" IS '修改时间';
COMMENT ON COLUMN "public"."boot_iot_product_type"."del_flag" IS '删除标识 0未删除 1已删除';
COMMENT ON COLUMN "public"."boot_iot_product_type"."version" IS '版本号';
COMMENT ON COLUMN "public"."boot_iot_product_type"."tenant_id" IS '租户ID';
COMMENT ON TABLE "public"."boot_iot_product_type" IS '产品类型';

-- ----------------------------
-- Records of boot_iot_product_type
-- ----------------------------

-- ----------------------------
-- Table structure for boot_iot_protocol
-- ----------------------------
DROP TABLE IF EXISTS "public"."boot_iot_protocol";
CREATE TABLE "public"."boot_iot_protocol" (
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
COMMENT ON COLUMN "public"."boot_iot_protocol"."id" IS 'ID';
COMMENT ON COLUMN "public"."boot_iot_protocol"."creator" IS '创建人';
COMMENT ON COLUMN "public"."boot_iot_protocol"."editor" IS '编辑人';
COMMENT ON COLUMN "public"."boot_iot_protocol"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."boot_iot_protocol"."update_time" IS '修改时间';
COMMENT ON COLUMN "public"."boot_iot_protocol"."del_flag" IS '删除标识 0未删除 1已删除';
COMMENT ON COLUMN "public"."boot_iot_protocol"."version" IS '版本号';
COMMENT ON COLUMN "public"."boot_iot_protocol"."tenant_id" IS '租户ID';
COMMENT ON TABLE "public"."boot_iot_protocol" IS '协议';

-- ----------------------------
-- Records of boot_iot_protocol
-- ----------------------------

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."boot_iot_device_id_seq"
OWNED BY "public"."boot_iot_device"."id";
SELECT setval('"public"."boot_iot_device_id_seq"', 1, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."boot_iot_device_id_seq1"
OWNED BY "public"."boot_iot_device"."id";
SELECT setval('"public"."boot_iot_device_id_seq1"', 1, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."boot_iot_model_id_seq"
OWNED BY "public"."boot_iot_model"."id";
SELECT setval('"public"."boot_iot_model_id_seq"', 1, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."boot_iot_network_id_seq"
OWNED BY "public"."boot_iot_network"."id";
SELECT setval('"public"."boot_iot_network_id_seq"', 1, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."boot_iot_product_id_seq"
OWNED BY "public"."boot_iot_product"."id";
SELECT setval('"public"."boot_iot_product_id_seq"', 1, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."boot_iot_product_type_id_seq"
OWNED BY "public"."boot_iot_product_type"."id";
SELECT setval('"public"."boot_iot_product_type_id_seq"', 1, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."boot_iot_protocol_id_seq"
OWNED BY "public"."boot_iot_protocol"."id";
SELECT setval('"public"."boot_iot_protocol_id_seq"', 1, false);

-- ----------------------------
-- Auto increment value for boot_iot_device
-- ----------------------------
SELECT setval('"public"."boot_iot_device_id_seq1"', 1, false);

-- ----------------------------
-- Primary Key structure for table boot_iot_device
-- ----------------------------
ALTER TABLE "public"."boot_iot_device" ADD CONSTRAINT "boot_iot_device_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Auto increment value for boot_iot_model
-- ----------------------------
SELECT setval('"public"."boot_iot_model_id_seq"', 1, false);

-- ----------------------------
-- Primary Key structure for table boot_iot_model
-- ----------------------------
ALTER TABLE "public"."boot_iot_model" ADD CONSTRAINT "boot_iot_model_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Auto increment value for boot_iot_network
-- ----------------------------
SELECT setval('"public"."boot_iot_network_id_seq"', 1, false);

-- ----------------------------
-- Primary Key structure for table boot_iot_network
-- ----------------------------
ALTER TABLE "public"."boot_iot_network" ADD CONSTRAINT "boot_iot_network_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Auto increment value for boot_iot_product
-- ----------------------------
SELECT setval('"public"."boot_iot_product_id_seq"', 1, false);

-- ----------------------------
-- Primary Key structure for table boot_iot_product
-- ----------------------------
ALTER TABLE "public"."boot_iot_product" ADD CONSTRAINT "boot_iot_product_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Auto increment value for boot_iot_product_type
-- ----------------------------
SELECT setval('"public"."boot_iot_product_type_id_seq"', 1, false);

-- ----------------------------
-- Primary Key structure for table boot_iot_product_type
-- ----------------------------
ALTER TABLE "public"."boot_iot_product_type" ADD CONSTRAINT "boot_iot_product_type_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Auto increment value for boot_iot_protocol
-- ----------------------------
SELECT setval('"public"."boot_iot_protocol_id_seq"', 1, false);

-- ----------------------------
-- Primary Key structure for table boot_iot_protocol
-- ----------------------------
ALTER TABLE "public"."boot_iot_protocol" ADD CONSTRAINT "boot_iot_protocol_pkey" PRIMARY KEY ("id");
