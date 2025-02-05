import {
	DrawerForm,
	ProColumns,
	ProFormText,
} from '@ant-design/pro-components';
import {ProTable} from '@ant-design/pro-components';
import {pageV3, removeV3, saveV3, getByIdV3, modifyV3} from "@/services/admin/user";
import {useRef, useState} from "react";
import {TableRowSelection} from "antd/es/table/interface";
import {Button, message, Modal, Space, Switch, Tag} from 'antd';
import { DeleteOutlined, PlusOutlined } from '@ant-design/icons';
import {v7 as uuidV7} from 'uuid';
import {trim} from "@/utils/format";

export default () => {

	type TableColumns = {
		id: number;
		username: string | undefined;
		status: number | undefined;
		password: string | undefined;
		mail: string | undefined;
		mobile: string | undefined;
		createTime: string | undefined;
		superAdmin: number | undefined;
		avatar: string | undefined;
	};

	const [readOnly, setReadOnly] = useState(false)
	const [modalVisit, setModalVisit] = useState(false);
	const actionRef = useRef();
	const [dataSource, setDataSource] = useState({})
	const [ids, setIds] = useState<number[]>([])
	const [title, setTitle] = useState("")

	const getPageQuery = (params: any) => {
		return {
			username: trim(params?.username),
			params: {
				startTime: params?.startDate ? `${params.startDate} 00:00:00` : undefined,
				endTime: params?.endDate ? `${params.endDate} 23:59:59` : undefined
			}
		}
	}

	const rowSelection: TableRowSelection<TableColumns> = {
		onChange: (selectedRowKeys) => {
			const ids: number[] = []
			selectedRowKeys.forEach(item => {
				ids.push(item as number)
			})
			setIds(ids)
		}
	};

	const columns: ProColumns<TableColumns>[] = [
		{
			title: '用户名',
			dataIndex: 'username',
			tooltip: "仅支持四个字符的模糊查询",
		},
		{
			title: '邮箱',
			dataIndex: 'mail',
			tooltip: "仅支持四个字符的模糊查询",
		},
		{
			title: '手机号',
			dataIndex: 'mobile',
			tooltip: "仅支持部分字符的模糊查询",
		},
		{
			title: '超级管理员',
			dataIndex: 'superAdmin',
			hideInTable: true,
			valueEnum: {
				0: {text: '否', status: 'Processing'},
				1: {text: '是', status: 'Default'},
			},
			ellipsis: true
		},
		{
			disable: true,
			title: '超级管理员',
			dataIndex: 'superAdmin',
			search: false,
			renderFormItem: (_, { defaultRender }) => {
				return defaultRender(_);
			},
			render: (_, record) => (
				<Space>
					{record?.superAdmin === 0 && (
						<Tag color={'rgb(51 114 253)'} key={'menu'}>
							否
						</Tag>
					)}
					{record?.superAdmin === 1 && (
						<Tag color={'#fd5251'} key={'button'}>
							是
						</Tag>
					)}
				</Space>
			),
		},
		{
			title: '状态',
			dataIndex: 'status',
			hideInTable: true,
			valueEnum: {
				0: {text: '启用', status: 'Success'},
				1: {text: '禁用', status: 'Error'},
			},
			ellipsis: true
		},
		{
			title: '状态',
			dataIndex: 'status',
			search: false,
			render: (_, record) => (
				<Switch checkedChildren="启用" unCheckedChildren="禁用" disabled={true} checked={record?.status === 0} />
			),
		},
		{
			title: '创建时间',
			key: 'createTime',
			dataIndex: 'createTime',
			valueType: 'dateTime',
			hideInSearch: true,
			width: 160,
			ellipsis: true
		},
		{
			title: '创建时间',
			dataIndex: 'createTime',
			valueType: 'dateRange',
			hideInTable: true,
			search: {
				transform: (value) => {
					return {
						startDate: value[0],
						endDate: value[1],
					};
				},
			}
		},
		{
			title: '操作',
			valueType: 'option',
			key: 'option',
			render: (_, record) => [
				<a key="get"
				   onClick={() => {
					   getByIdV3({id: record?.id}).then(res => {
						   setTitle('查看用户')
						   setModalVisit(true)
						   setReadOnly(true)
						   setDataSource(res?.data)
					   })
				   }}
				>
					查看
				</a>,
				<a key="modify"
				   onClick={() => {
					   getByIdV3({id: record?.id}).then(res => {
						   setTitle('修改用户')
						   setModalVisit(true)
						   setReadOnly(false)
						   setDataSource(res?.data)
					   })
				   }}
				>
					修改
				</a>,
				<a key="remove" onClick={() => {
					Modal.confirm({
						title: '确认删除?',
						content: '您确定要删除吗?',
						okText: '确认',
						cancelText: '取消',
						onOk: () => {
							removeV3([record?.id]).then(res => {
								if (res.code === 'OK') {
									message.success("删除成功").then()
									// @ts-ignore
									actionRef?.current?.reload();
								}
							})
						}
					})
				}}>
					删除
				</a>
			],
		},
	];

	return (
		<>
			<DrawerForm<TableColumns>
				open={modalVisit}
				title={title}
				drawerProps={{
					destroyOnClose: true,
					closable: true,
					maskClosable: true
				}}
				initialValues={dataSource}
				onOpenChange={setModalVisit}
				autoFocusFirstInput
				submitter={{
					submitButtonProps: {
						style: {
							display: readOnly ? 'none' : 'inline-block',
						},
					}
				}}
				onFinish={ async (value) => {
					if (value.id === undefined) {
						saveV3({co: value}, uuidV7()).then(res => {
							if (res.code === 'OK') {
								message.success("新增成功").then()
								setModalVisit(false)
								// @ts-ignore
								actionRef?.current?.reload();
							}
						})
					} else {
						// @ts-ignore
						modifyV3({co: value}).then(res => {
							if (res.code === 'OK') {
								message.success("修改成功").then()
								setModalVisit(false)
								// @ts-ignore
								actionRef?.current?.reload();
							}
						})
					}
				}}>

				<ProFormText
					name="id"
					label="ID"
					hidden={true}
				/>

				<ProFormText
					name="username"
					label="名称"
					tooltip={"用于用户名密码登录【不允许重复】"}
					readonly={readOnly}
					placeholder={'请输入用户名'}
					rules={[{ required: true, message: '请输入用户名' }]}
				/>

				<ProFormText
					name="mail"
					label="邮箱"
					tooltip={"用于邮箱登录【不允许重复】"}
					readonly={readOnly}
					placeholder={'请输入邮箱'}
				/>

				<ProFormText
					name="mobile"
					label="手机号"
					tooltip={"用于手机号登录【不允许重复】"}
					readonly={readOnly}
					placeholder={'请输入手机号'}
				/>

			</DrawerForm>
			<ProTable<TableColumns>
				actionRef={actionRef}
				columns={columns}
				request={ async (params) => {
					// 表单搜索项会从 params 传入，传递给后端接口。
					return pageV3(getPageQuery(params)).then(res => {
						return Promise.resolve({
							data: res?.data?.records,
							total: parseInt(res.data.total),
							success: true,
						});
					})
				}}
				rowSelection={{ ...rowSelection }}
				rowKey="id"
				search={{
					layout: 'vertical',
					defaultCollapsed: true,
				}}
				toolBarRender={
					() => [
						<Button key="save" type="primary" icon={<PlusOutlined />} onClick={() => {
							setTitle('新增用户')
							setReadOnly(false)
							setModalVisit(true)
							setDataSource({
								id: undefined,
								username: '',
								superAdmin: 0,
								status: 0,
							})
						}}>
							新增
						</Button>,
						<Button key="remove" type="primary" danger icon={<DeleteOutlined />} onClick={() => {
							Modal.confirm({
								title: '确认删除?',
								content: '您确定要删除吗?',
								okText: '确认',
								cancelText: '取消',
								onOk: async () => {
									if (ids.length === 0) {
										message.warning("请至少选择一条数据").then()
										return;
									}
									// @ts-ignore
									removeV3(ids).then(res => {
										if (res.code === 'OK') {
											message.success("删除成功").then()
											// @ts-ignore
											actionRef?.current?.reload();
										}
									})
								},
							});
						}}>
							删除
						</Button>
					]
				}
				dateFormatter="string"
				toolbar={{
					title: '用户',
					tooltip: '用户',
				}}
			/>
		</>
	);
};
