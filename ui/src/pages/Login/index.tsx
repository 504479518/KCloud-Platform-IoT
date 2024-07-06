import {
	AlipayOutlined,
	GithubOutlined,
	LockOutlined,
	MailOutlined,
	MobileOutlined,
	QqOutlined,
	SafetyCertificateOutlined,
	UserOutlined,
	WechatOutlined,
} from '@ant-design/icons';
import {LoginFormPage, ProFormCaptcha, ProFormText,} from '@ant-design/pro-components';
import {Col, Divider, Image, message, Row, Space, Tabs} from 'antd';
import {CSSProperties, useEffect, useState} from 'react';
// @ts-ignore
import {login} from '@/services/auth/authsController';
import {getCaptchaByUuidV3} from '@/services/auth/captchasV3Controller';
// @ts-ignore
import {history} from 'umi';
// @ts-ignore
import {getSecretInfoV3} from '@/services/auth/secretsV3Controller';
import {JSEncrypt} from 'jsencrypt';
// @ts-ignore
import {v7 as uuidV7} from 'uuid';

type LoginType = 'usernamePassword' | 'mobile' | 'mail';

const iconStyles: CSSProperties = {
	color: 'rgba(0, 0, 0, 0.2)',
	fontSize: '18px',
	verticalAlign: 'middle',
	cursor: 'pointer',
};

export default () => {
	const items = [
		{label: '用户名密码登录', key: 'usernamePassword'},
		{label: '手机号登录', key: 'mobile'},
		{label: '邮箱登录', key: 'mail'},
	];
	const [loginType, setLoginType] = useState<LoginType>('usernamePassword');
	const [captchaImage, setCaptchaImage] = useState<string>('');
	const [uuid, setUuid] = useState<string>('');
	const [publicKey, setPublicKey] = useState<string>('');

	const timeFix = () => {
		const time = new Date();
		const hour = time.getHours();
		return hour < 9
			? '早上好'
			: hour <= 11
				? '上午好'
				: hour <= 13
					? '中午好'
					: hour < 20
						? '下午好'
						: '晚上好';
	};

	const encrypt = new JSEncrypt();
	const getParams = (form: API.LoginParam) => {
		switch (loginType) {
			case 'usernamePassword': {
				encrypt.setPublicKey(publicKey);
				return {
					uuid: uuid,
					captcha: form.captcha,
					username: encodeURIComponent(
						encrypt.encrypt(form.username as string),
					),
					password: encodeURIComponent(
						encrypt.encrypt(form.password as string),
					),
					tenant_id: 0,
					grant_type: 'password',
				};
			}
			case 'mail':
				return {};
			case 'mobile':
				return {};
		}
	};

	const getCaptchaImage = () => {
		const uuid = uuidV7();
		getCaptchaByUuidV3({uuid: uuid}).then((res) => {
			setCaptchaImage(res.data);
		});
		setUuid(uuid);
	};

	const getPublicKey = () => {
		getSecretInfoV3().then((res) => {
			setPublicKey(res.data.publicKey);
		});
	};

	useEffect(() => {
		getPublicKey();
		getCaptchaImage();
	}, []);

	const onSubmit = async (form: API.LoginParam) => {
		const params = getParams(form);
		login({...params})
			.then((res) => {
				console.log("登录成功", res)
				// 跳转路由
				const urlParams = new URL(window.location.href).searchParams;
				history.push(urlParams.get('redirect') || '/');
				// 延迟 1 秒显示欢迎信息
				setTimeout(() => {
					message.success(`${timeFix()}，欢迎回来`);
				}, 1000);
			})
			.catch((err) => {
				message.error(err.response.data.error_description);
				getCaptchaImage();
			});
	};
	return (
		<div
			style={{
				backgroundColor: 'white',
				height: '100vh',
				width: '100vw',
			}}
		>
			<LoginFormPage
				onFinish={onSubmit}
				backgroundImageUrl="https://gw.alipayobjects.com/zos/rmsportal/FfdJeJRQWjEeGTpqgBKj.png"
				logo={<img alt="logo" src="/logo.png"/>}
				title="老寇IoT云平台"
				subTitle="企业级微服务架构云服务多租户IoT平台"
				actions={
					<div
						style={{
							display: 'flex',
							justifyContent: 'center',
							alignItems: 'center',
							flexDirection: 'column',
						}}
					>
						<Divider plain>
              <span
				  style={{color: '#CCC', fontWeight: 'normal', fontSize: 14}}
			  >
                其他登录方式
              </span>
						</Divider>
						<Space align="center" size={24}>
							<div
								style={{
									display: 'flex',
									justifyContent: 'center',
									alignItems: 'center',
									flexDirection: 'column',
									height: 40,
									width: 40,
									border: '1px solid #D4D8DD',
									borderRadius: '50%',
								}}
							>
								<GithubOutlined style={{...iconStyles, color: '#1f2328'}}/>
							</div>
							<div
								style={{
									display: 'flex',
									justifyContent: 'center',
									alignItems: 'center',
									flexDirection: 'column',
									height: 40,
									width: 40,
									border: '1px solid #D4D8DD',
									borderRadius: '50%',
								}}
							>
								<WechatOutlined style={{...iconStyles, color: '#0fcd2a'}}/>
							</div>
							<div
								style={{
									display: 'flex',
									justifyContent: 'center',
									alignItems: 'center',
									flexDirection: 'column',
									height: 40,
									width: 40,
									border: '1px solid #D4D8DD',
									borderRadius: '50%',
								}}
							>
								<QqOutlined style={{...iconStyles, color: '#1191ff'}}/>
							</div>
							<div
								style={{
									display: 'flex',
									justifyContent: 'center',
									alignItems: 'center',
									flexDirection: 'column',
									height: 40,
									width: 40,
									border: '1px solid #D4D8DD',
									borderRadius: '50%',
								}}
							>
								<AlipayOutlined style={{...iconStyles, color: '#1677FF'}}/>
							</div>
						</Space>
					</div>
				}
			>
				<Tabs
					centered
					items={items}
					activeKey={loginType}
					onChange={(activeKey) => setLoginType(activeKey as LoginType)}
				></Tabs>

				{loginType === 'usernamePassword' && (
					<>
						<ProFormText
							name="username"
							fieldProps={{
								size: 'large',
								prefix: <UserOutlined className={'prefixIcon'}/>,
								autoComplete: 'new-password',
							}}
							placeholder={'请输入用户名'}
							rules={[
								{
									required: true,
									message: '请输入用户名',
								},
							]}
						/>
						<ProFormText.Password
							name="password"
							fieldProps={{
								size: 'large',
								prefix: <LockOutlined className={'prefixIcon'}/>,
								autoComplete: 'new-password',
							}}
							placeholder={'请输入密码'}
							rules={[
								{
									required: true,
									message: '请输入密码',
								},
							]}
						/>
						<Row>
							<Col flex={3}>
								<ProFormText
									style={{
										float: 'right',
									}}
									fieldProps={{
										prefix: (
											<SafetyCertificateOutlined className={'prefixIcon'}/>
										),
										autoComplete: 'new-password',
									}}
									name="captcha"
									placeholder={'请输入验证码'}
									rules={[
										{
											required: true,
											message: '请输入验证码',
										},
									]}
								/>
							</Col>
							<Col flex={2}>
								<Image
									src={captchaImage}
									alt="验证码"
									style={{
										display: 'inline-block',
										verticalAlign: 'top',
										cursor: 'pointer',
										paddingLeft: '10px',
										width: '100px',
									}}
									preview={false}
									onClick={() => getCaptchaImage()}
								/>
							</Col>
						</Row>
					</>
				)}
				{loginType === 'mobile' && (
					<>
						<ProFormText
							fieldProps={{
								size: 'large',
								prefix: <MobileOutlined className={'prefixIcon'}/>,
								autoComplete: 'new-password',
							}}
							name="mobile"
							placeholder={'请输入手机号'}
							rules={[
								{
									required: true,
									message: '请输入手机号',
								},
								{
									pattern: /^1\d{10}$/,
									message: '手机号格式错误',
								},
							]}
						/>
						<ProFormCaptcha
							fieldProps={{
								size: 'large',
								prefix: <SafetyCertificateOutlined className={'prefixIcon'}/>,
								autoComplete: 'new-password',
							}}
							captchaProps={{
								size: 'large',
							}}
							placeholder={'请输入验证码'}
							captchaTextRender={(timing, count) => {
								if (timing) {
									return `${count} 获取验证码`;
								}
								return '获取验证码';
							}}
							name="captcha"
							rules={[
								{
									required: true,
									message: '请输入验证码',
								},
							]}
							onGetCaptcha={async () => {
								message.success('获取验证码成功！验证码为：1234');
							}}
						/>
					</>
				)}
				{loginType === 'mail' && (
					<>
						<ProFormText
							fieldProps={{
								size: 'large',
								prefix: <MailOutlined className={'prefixIcon'}/>,
								autoComplete: 'new-password',
							}}
							name="mail"
							placeholder={'请输入邮箱'}
							rules={[
								{
									required: true,
									message: '请输入邮箱',
								},
								{
									pattern: /^\w+(-+.\w+)*@\w+(-.\w+)*.\w+(-.\w+)*$/,
									message: '邮箱格式错误',
								},
							]}
						/>
						<ProFormCaptcha
							fieldProps={{
								size: 'large',
								prefix: <SafetyCertificateOutlined className={'prefixIcon'}/>,
								autoComplete: 'new-password',
							}}
							captchaProps={{
								size: 'large',
							}}
							placeholder={'请输入验证码'}
							captchaTextRender={(timing, count) => {
								if (timing) {
									return `${count} 获取验证码`;
								}
								return '获取验证码';
							}}
							name="captcha"
							rules={[
								{
									required: true,
									message: '请输入验证码',
								},
							]}
							onGetCaptcha={async () => {
								message.success('获取验证码成功！验证码为：1234');
							}}
						/>
					</>
				)}
			</LoginFormPage>
		</div>
	);
};
