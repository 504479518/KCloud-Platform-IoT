create table if not exists public.boot_iot_device
(
	id          bigint generated by default as identity
		primary key,
	creator     bigint        default 0                      not null,
	editor      bigint        default 0                      not null,
	create_date timestamp(6)  default CURRENT_TIMESTAMP      not null,
	update_date timestamp(6)  default CURRENT_TIMESTAMP      not null,
	del_flag    smallint      default 0                      not null,
	version     integer       default 0                      not null,
	dept_id     bigint        default 0                      not null,
	dept_path   varchar(1000) default '0'::character varying not null,
	tenant_id   bigint        default 0                      not null,
	key         varchar(64)                                  not null,
	name        varchar(20)                                  not null,
	status      smallint      default 0                      not null
);

comment on table public.boot_iot_device is '设备';

comment on column public.boot_iot_device.id is 'ID';

comment on column public.boot_iot_device.creator is '创建人';

comment on column public.boot_iot_device.editor is '编辑人';

comment on column public.boot_iot_device.create_date is '创建时间';

comment on column public.boot_iot_device.update_date is '修改时间';

comment on column public.boot_iot_device.del_flag is '删除标识 0未删除 1已删除';

comment on column public.boot_iot_device.version is '版本号';

comment on column public.boot_iot_device.dept_id is '部门ID';

comment on column public.boot_iot_device.dept_path is '部门PATH';

comment on column public.boot_iot_device.tenant_id is '租户ID';

comment on column public.boot_iot_device.key is '设备标识';

comment on column public.boot_iot_device.name is '设备名称';

comment on column public.boot_iot_device.status is '设备状态 0在线 1离线';

alter table public.boot_iot_device
	owner to root;

INSERT INTO public.boot_iot_device (id, creator, editor, create_date, update_date, del_flag, version, dept_id, dept_path, tenant_id, key, name, status) VALUES (1, 1341620898007281665, 1341620898007281665, '2024-05-11 03:56:15.821857', '2024-05-11 03:56:15.821857', 0, 0, 1535887940687765505, '0,1535887940687765505', 0, '139c5556-8494-5753-ac97-de09f2a6a929', 'HFCL设备', 0);
