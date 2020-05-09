<template>
	<div class="container">
		<div class="container_1">
			<el-col :span="24" class="header">
				<div class="header_dropdown">
					<el-dropdown trigger="click">
						<span class="el-dropdown-link">
							<i class="el-icon-user"></i>
							{{ username }}
							<i class="el-icon-arrow-down"></i>
						</span>
						<el-dropdown-menu slot="dropdown">
							<el-dropdown-item @click.native="dialogMesg = true">个人信息</el-dropdown-item>
							<el-dropdown-item @click.native="dialogPawd = true">修改密码</el-dropdown-item>
							<el-dropdown-item divided @click.native="logout">退出登录</el-dropdown-item>
						</el-dropdown-menu>
					</el-dropdown>
				</div>
			</el-col>
		</div>

		<el-container class="container_2">
			<el-aside class="aside_container">
				<el-menu
					:default-openeds="['1', '2', '3']"
					default-active="2"
					class="el-menu-vertical-demo"
					@open="handleOpen"
					@close="handleClose"
					background-color="#545c64"
					text-color="#fff"
					active-text-color="#ffd04b"
				>
					<el-submenu index="1">
						<template slot="title">
							<i class="el-icon-location"></i>
							<span>导航一</span>
						</template>
						<el-menu-item-group>
							<template slot="title">
								分组一
							</template>
							<el-menu-item index="1-1">选项1</el-menu-item>
							<el-menu-item index="1-2">选项2</el-menu-item>
						</el-menu-item-group>
						<el-menu-item-group title="分组2"><el-menu-item index="1-3">选项3</el-menu-item></el-menu-item-group>
						<el-submenu index="1-4">
							<template slot="title">
								选项4
							</template>
							<el-menu-item index="1-4-1">选项1</el-menu-item>
						</el-submenu>
					</el-submenu>
					<el-menu-item index="2">
						<i class="el-icon-menu"></i>
						<span slot="title">导航二</span>
					</el-menu-item>
					<el-menu-item index="3" disabled>
						<i class="el-icon-document"></i>
						<span slot="title">导航三</span>
					</el-menu-item>
					<el-menu-item index="4">
						<i class="el-icon-setting"></i>
						<span slot="title">导航四</span>
					</el-menu-item>
				</el-menu>
			</el-aside>
			<el-container class="container_3">
				<el-main class="main_container">
					<div class="fruitform">
						<el-form ref="form" :model="form" label-width="80px">
							<el-form-item label="水果名称" class="fruit_name"><el-input v-model="form.name" clearable placeholder="请输入水果名称"></el-input></el-form-item>
							<div class="fruit_img">
								<label class="el-form-item__label" style="width: 80px;">上传图片</label>
								<el-upload action="" list-type="picture-card" :auto-upload="false" :on-change="handleLimit" :class="{ hide: hideUpload }" class="img_upload">
									<i slot="default" class="el-icon-plus"></i>
									<div slot="file" slot-scope="{ file }">
										<img class="el-upload-list__item-thumbnail" :src="file.url" alt="" />
										<span class="el-upload-list__item-actions">
											<span class="el-upload-list__item-preview" @click="handlePictureCardPreview(file)"><i class="el-icon-zoom-in"></i></span>
											<span v-if="!disabled" class="el-upload-list__item-delete" @click="handleRemove(file)"><i class="el-icon-delete"></i></span>
										</span>
									</div>
								</el-upload>
								<el-dialog :visible.sync="dialogVisible"><img width="100%" :src="dialogImageUrl" alt="" /></el-dialog>
							</div>
							<el-form-item label="水果种类" class="fruit_cla">
								<el-select v-model="form.seriesvalue" placeholder="请选择所属系列" v-on:change="getSeries($event)" :popper-append-to-body="false">
									<el-option v-for="item in form.options" :key="item.seriesvalue" :label="item.label" :value="item.label"></el-option>
								</el-select>
								<div class="kong"></div>
								<el-select v-model="form.seriesvalue2" placeholder="请选择子系列" @change="getList($event)" :popper-append-to-body="false">
									<el-option v-for="items in form.options2" :key="items.id" :label="items.serlabel" :value="items.serlabel"></el-option>
								</el-select>
							</el-form-item>
							<el-form-item label="数量" class="fruit_account">
								<el-input v-model="form.account" clearable placeholder="请输入水果库存数量（单位：份）"></el-input>
							</el-form-item>
							<el-form-item label="市场价" class="fruit_pri1">
								<el-input v-model="form.marketprice" clearable placeholder="请输入水果市场价（单位：元）"></el-input>
							</el-form-item>
							<el-form-item label="本站价" class="fruit_pri2">
								<el-input v-model="form.siteprice" clearable placeholder="请输入水果本站价（单位：元）"></el-input>
							</el-form-item>
							<el-form-item class="fruit_sub">
								<el-button type="primary" @click="onSubmit">立即提交</el-button>
								<el-button>取消</el-button>
							</el-form-item>
						</el-form>
					</div>
				</el-main>
			</el-container>
		</el-container>
	</div>
</template>

<script>
import API from '../../api/api_admin';

export default {
	created() {
		this.username = this.$store.getters.username;
	},
	data() {
		return {
			username: '',
			hideUpload: false,
			limitCount: 1,
			dialogImageUrl: '',
			dialogVisible: false,
			disabled: false,
			param: '',
			form: {
				name: '',
				options2: [],
				seriesvalue: '',
				seriesvalue2: '',
				marketprice: '',
				siteprice: '',
				account: '',
				options: [
					{
						seriesvalue: 'A',
						label: '进口水果'
					},
					{
						seriesvalue: 'B',
						label: '国产水果'
					},
					{
						seriesvalue: 'C',
						label: '水果礼盒'
					}
				]
			},
			allChild: [
				{
					val: '进口水果',
					sel: '南非水果',
					sno: 'A1'
				},
				{
					val: '进口水果',
					sel: '泰国水果',
					sno: 'A2'
				},
				{
					val: '进口水果',
					sel: '越南水果',
					sno: 'A3'
				},
				{
					val: '进口水果',
					sel: '菲律宾水果',
					sno: 'A4'
				},
				{
					val: '进口水果',
					sel: '新西兰水果',
					sno: 'A5'
				},
				{
					val: '国产水果',
					sel: '时令水果',
					sno: 'B1'
				},
				{
					val: '国产水果',
					sel: '新品上市',
					sno: 'B2'
				},
				{
					val: '国产水果',
					sel: '推荐水果',
					sno: 'B3'
				},
				{
					val: '国产水果',
					sel: '国产热卖水果',
					sno: 'B4'
				},
				{
					val: '水果礼盒',
					sel: '时令水果礼盒',
					sno: 'C1'
				}
			]
		};
	},
	mounted() {
		document.getElementsByTagName('body')[0].className = 'admin_body';
	},
	beforeDestroy() {
		document.body.removeAttribute('class', 'admin_body');
	},
	methods: {
		handleOpen(key, keyPath) {
			console.log(key, keyPath);
		},
		handleClose(key, keyPath) {
			console.log(key, keyPath);
		},
		handleRemove(file) {
			this.hideUpload = fileList.length >= this.limitCount;
			console.log(file);
		},
		handlePictureCardPreview(file) {
			this.dialogImageUrl = file.url;
			this.dialogVisible = true;
		},
		handleLimit(file, fileList) {
			if (fileList.length >= 1) {
				this.hideUpload = true;
			}

			console.log('5!');

			this.param = new FormData();
			this.param.append('file', file.raw, file.name);

			console.log('6!');
		},
		logout() {
			this.$confirm('确认退出吗?', '提示', {
				confirmButtonText: '确定',
				cancelButtonText: '取消',
				type: 'warning'
			})
				.then(() => {
					this.loading = true;
					this.$store.commit('removeToken');
					this.$store.commit('removeUser');
					this.$store.commit('removeUserName');
					this.$router.go('/login/admin');
				})
				.catch(() => {
					this.$message({
						type: 'info',
						message: '已取消'
					});
				});
		},
		getSeries(serval) {
			console.log(serval);
			let roles = [];
			this.form.options2 = [];
			for (var v of this.allChild) {
				if (serval === v.val) {
					console.log(v);
					roles.push({ sername: v.val, serlabel: v.sel, serno: v.sno });
				}
				this.form.options2 = roles;
			}
		},
		getList(opt) {
			console.log(opt);
		},
		onSubmit() {
			console.log('submit!');

			var name1 = this.form.name;
			var cal1 = this.form.seriesvalue;
			var cal2 = this.form.seriesvalue2;
			var account1 = this.form.account;
			var marketprice1 = this.form.marketprice;
			var siteprice1 = this.form.siteprice;

			console.log('1!');

			//下面append的东西就会到form表单数据的fields中；
			this.param.append('fruitname', name1);
			this.param.append('series', cal1);
			this.param.append('childseries', cal2);
			this.param.append('account', account1);
			this.param.append('marketprice', marketprice1);
			this.param.append('siteprice', siteprice1);

			console.log('2!');

			let config = {
				headers: {
					'Content-Type': 'multipart/form-data'
				}
			};

			console.log('3!');

			API.upfru(this.param).then(res => {
				if (res == 'success') {
					this.loading = false;
					this.$message({
						message: '登录成功！',
						type: 'success'
					});
					this.$router.go(0);
				}
			});

			/* this.$axios
				.post('/api-a/fruit', this.param)
				.then(response => {
					//这里使用了ES6的语法
					console.log(response); //请求成功返回的数据
				})
				.catch(error => {
					console.log(error); //请求失败返回的数据
				}); */

			//然后通过下面的方式把内容通过axios来传到后台
			//下面的this.$reqs 是在主js中通过Vue.prototype.$reqs = axios 来把axios赋给它;
			/* this.$reqs.post('/api-a/fruit', this.param, config).then(function(result) {
				console.log(result);
			}); */

			console.log('4!');
		}
	},
	watch: {
		'form.seriesvalue': function(newValue) {
			this.form.seriesvalue = newValue;
			this.form.seriesvalue2 = null;
		}
	}
};
</script>

<style lang="scss">
.admin_body {
	position: absolute;
	top: 60px;
	bottom: 0;
	width: 100%;
	overflow: hidden;

	.container {
		height: 100%;

		.container_1 {
			position: fixed;
			top: 0;
			width: 100%;
			height: 60px;
			background: #00557f;

			.header {
				height: 60px;
				line-height: 50px;
				background: #00557f;
				padding: 0px;

				.header_dropdown {
					float: right;
					padding-right: 30px;
					color: #fff;
					height: 50px;

					.el-dropdown-link {
						cursor: pointer;
						color: #fff;
						line-height: 50px;
						padding-left: 10px;
						border: none;
						font-size: 16px;

						.el-dropdown {
							height: 50px;
							padding: 0;
						}

						.el-icon-arrow-down {
							font-size: 14px;
							padding-left: 2px;
						}

						.el-icon-user {
							font-size: 24px;
							padding-right: 5px;
						}
					}

					.el-dropdown-item span {
						color: #555;
						font-size: 20px;
					}
				}
			}
		}

		.container_2 {
			position: relative;
			bottom: 0;
			width: 100%;
			height: 100%;
			overflow: hidden;

			.aside_container {
				border-right: 2px solid #d5d5d5;
				background-color: #545c64;
			}

			.container_3 {
				.main_container {
					.fruitform {
						width: 100%;
						display: flex;
						align-items: center;

						.el-form {
							margin: auto;
							width: 600px;

							.el-form-item {
								margin: auto;
								margin-bottom: 22px;
							}

							.fruit_img {
								margin: auto;
								margin-bottom: 22px;
								display: flex;
								align-items: center;

								.img_upload {
									margin: auto;
								}

								.hide .el-upload--picture-card {
									display: none;
								}
							}

							.fruit_cla {
								.kong {
									width: 25px;
									display: inline-block;
								}
								.el-select-dropdown__item span {
									float: left;
								}
							}
						}
					}
				}
			}
		}
	}
}
</style>
