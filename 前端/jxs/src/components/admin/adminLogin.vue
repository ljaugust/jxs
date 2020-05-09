<template>
	<div class="container">
		<div class="loginTop"></div>
		<div class="login_background">
			<div class="login_container">
				<div class="lo">
					<el-form ref="loginForm" :model="loginMesg" :rules="loginMesg" label-width="80px" label-position="right" class="login_from">
						<span class="title">管理员登录</span>

						<el-form-item prop="username" label="账号">
							<el-input class="login_input" type="text" v-model="loginMesg.username" auto-complete="off" placeholder="账号"></el-input>
						</el-form-item>

						<el-form-item prop="password" label="密码">
							<el-input class="login_input" type="password" v-model="loginMesg.password" auto-complete="off" placeholder="密码"></el-input>
						</el-form-item>

						<el-form-item>
							<el-checkbox v-model="checked" checked class="remember">记住密码</el-checkbox>
							<el-link href="/login" target="_self" class="forget">会员登录</el-link>
						</el-form-item>

						<el-form-item><el-button class="login_button" type="primary" @click.native.prevent="userLogin" :loading="loading">登录</el-button></el-form-item>
					</el-form>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import API from '../../api/api_admin';
export default {
	data() {
		return {
			loading: false,
			loginMesg: {
				username: 'admin',
				password: 'admin'
			},
			rules: {
				username: [{ required: true, message: '请输入账号', trigger: 'blur' }],
				password: [{ required: true, message: '请输入密码', trigger: 'blur' }]
			},
			checked: true
		};
	},

	methods: {
		findMesg() {
			API.findMesg().then(res => {
				if (res) {
					this.$store.commit('addUser', res.userId);
					this.$store.commit('addUserName', res.username);
					this.$router.push({ path: '/admincenter' });
				}
			});
		},

		userLogin() {
			let that = this;
			this.$refs.loginForm.validate(valid => {
				if (valid) {
					this.loading = true;
					let loginParams = new FormData();
					loginParams.append('client_id', 'jxs');
					loginParams.append('client_secret', 'secret');
					loginParams.append('grant_type', 'password');
					loginParams.append('scope', 'ROLE_USER');
					loginParams.append('username', this.loginMesg.username);
					loginParams.append('password', this.loginMesg.password);
					API.login(loginParams)
						.then(res => {
							that.loading = false;
							console.log(res);
							that.$message({
								message: '登录成功！',
								type: 'success'
							});
							if (res) {
								that.$store.commit('addToken', 'Bearer ' + res.access_token);
								console.log('查看token', that.$store.state.token);
								that.findMesg();
							} else {
								that.$message.error({
									showClose: true,
									message: res.msg || '登录失败',
									duration: 2000
								});
							}
						})
						.catch(error => {
							that.loading = false;
							console.log('error');
							that.$message.error({
								showClose: true,
								message: '请求出现异常',
								duration: 2000
							});
						});
				}
			});
		}
	}
};
</script>

<style scoped lang="scss">
.container {
	position: absolute;
	top: 0px;
	bottom: 0px;
	width: 100%;

	.loginTop {
		width: 1030px;
		height: 116px;
		margin: 5px auto;
		margin-bottom: 0px;
	}

	.login_background {
		width: 100%;
		height: 510px;
		background: url(../../assets/login.png) center no-repeat;
		background-size: auto 100%;

		.login_container {
			width: 1333px;
			margin: 0 auto;

			.lo {
				width: 500px;
				height: 350px;
				border: 1px solid #c6c6c6;
				border-top: 1px solid #c6c6c6;
				float: right;
				margin: 80px auto 0;
				background: #f5f5dc;

				.title {
					margin: 15px auto 40px auto;
					text-align: center;
					color: #505458;
					font-size: 22px;
					display: block;
				}

				.login_input {
					width: 380px;
					float: left;
				}

				.remember {
					float: left;
					font-size: 14px;
					line-height: 20px;
				}

				.forget {
					float: right;
					font-size: 14px;
					margin: 0 50px 0 0;
					line-height: 20px;
				}

				.login_button {
					width: 350px;
					cursor: pointer;
					font-size: 20px;
					margin: 0 80px 0 0;
				}
			}
		}
	}
}
</style>
