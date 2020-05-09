<template>
	<div class="container">
		<div class="top_container">
			<div class="top">
				<div class="top_left">
					<el-button type="text" @click="logout">退出</el-button>
					<el-divider direction="vertical"></el-divider>
					<el-link href="" target="_blank">欢迎你 {{ username }}</el-link>
				</div>
				<div class="top_right"><span>水果订购热线：15521508095（林先生）</span></div>
			</div>
		</div>
		<div class="search">
			<div class="search_total">
				<div class="search_left">
					<div class="logo"><img src="../../assets/jxs.png" class="logo_image" /></div>
					<div class="find">
						<el-input class="search_input" placeholder="" prefix-icon="el-icon-search"></el-input>
						<el-button class="search_button" type="primary" icon="el-icon-search">搜索</el-button>
					</div>
				</div>
				<div class="search_right">
					<div class="navigation">
						<el-menu :default-active="'/sort'" class="navigation_menu" router mode="horizontal">
							<el-menu-item class="navigation_menu_item" v-for="(item, i) in navList" :key="i" :index="item.name">{{ item.navItem }}</el-menu-item>
						</el-menu>
					</div>
				</div>
			</div>
		</div>
		<div class="exhibition">
			<div class="rotation">
				<el-carousel height="200px" indicator-position="none" arrow="never">
					<el-carousel-item v-for="(ban, i) in imgURL" :key="i"><img v-bind:src="ban.url" class="fruit_image" /></el-carousel-item>
				</el-carousel>
			</div>
		</div>
		<div class="show_main">
			<el-container class="show_main_container">
				<el-aside class="elaside_0" width="250px">
					<div class="elaside_total">
						<div class="sort_title">
							<span class="span_1">CLASSIFICATION</span>
							<div class="span_2">· 水 果 分 类 ·</div>
						</div>
						<el-menu :default-openeds="['1', '2', '3']" class="elmenu_total" @open="handleOpen" @close="handleClose">
							<el-submenu class="elmenu_submenu" index="1">
								<template slot="title">
									<span class="span_0">进口水果</span>
								</template>
								<el-menu-item-group>
									<el-menu-item index="1-1">南非水果</el-menu-item>
									<el-menu-item index="1-2">泰国水果</el-menu-item>
									<el-menu-item index="1-3">越南水果</el-menu-item>
									<el-menu-item index="1-4">菲律宾水果</el-menu-item>
									<el-menu-item index="1-5">新西兰水果</el-menu-item>
									<div class="el-menu-item-group__title" style="padding-left: 40px;"></div>
								</el-menu-item-group>
							</el-submenu>
							<el-submenu class="elmenu_submenu" index="2">
								<template slot="title">
									<span class="span_0">国产水果</span>
								</template>
								<el-menu-item-group>
									<el-menu-item index="2-1">时令水果</el-menu-item>
									<el-menu-item index="2-2">新品上市</el-menu-item>
									<el-menu-item index="2-3">推荐水果</el-menu-item>
									<el-menu-item index="2-4">国产热卖水果</el-menu-item>
									<div class="el-menu-item-group__title" style="padding-left: 40px;"></div>
								</el-menu-item-group>
							</el-submenu>
							<el-submenu class="elmenu_submenu" index="3">
								<template slot="title">
									<span class="span_0">水果礼盒</span>
								</template>
								<el-menu-item-group>
									<el-menu-item index="3-1">时令水果礼盒</el-menu-item>
									<div class="el-menu-item-group__title" style="padding-left: 40px;"></div>
								</el-menu-item-group>
							</el-submenu>
						</el-menu>
					</div>
					<div class="elaside_total_bottom"></div>
				</el-aside>
				<el-aside class="elaside_1" width="50px"></el-aside>
				<el-container class="show_main_container_children">
					<el-header>
						<div class="elheader_left"><span class="elheader_span">您当前的位置：</span></div>
						<div class="elheader_right">
							<el-breadcrumb class="bread_crumb" separator-class="el-icon-arrow-right">
								<el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
								<el-breadcrumb-item :to="{ path: '/sort' }">水果分类</el-breadcrumb-item>
								<el-breadcrumb-item>进口水果</el-breadcrumb-item>
								<el-breadcrumb-item>芒果</el-breadcrumb-item>
							</el-breadcrumb>
						</div>
					</el-header>
					<el-main style="padding: 10px;">
						<div class="fruit_details">
							<div class="fruit_details_picture"><img :src="fruit.base64Str" @click="handlePictureCardPreview" class="fruit_details_img" /></div>
							<el-dialog :visible.sync="dialogVisible"><img width="100%" :src="dialogImageUrl" alt="" /></el-dialog>
							<div class="fruit_details_info">
								<div class="info_top">
									<ul class="info_ul">
										<li class="info_li">
											<div class="info_li_left">所属系列：</div>
											<div class="info_li_right">
												<span>{{ fruit.series }}</span>
											</div>
										</li>
										<li class="info_li">
											<div class="info_li_left">所属子系列：</div>
											<div class="info_li_right">
												<span>{{ fruit.childseries }}</span>
											</div>
										</li>
										<li class="info_li">
											<div class="info_li_left">商品编号：</div>
											<div class="info_li_right">
												<span>{{ fruit.fruitnum }}</span>
											</div>
										</li>
										<li class="info_li">
											<div class="info_li_left">商品名称：</div>
											<div class="info_li_right">
												<span>{{ fruit.fruitname }}</span>
											</div>
										</li>
										<li class="info_li">
											<div class="info_li_left">市场价：</div>
											<div class="info_li_right">
												<span>{{ fruit.marketprice }}</span>
											</div>
										</li>
										<li class="info_li">
											<div class="info_li_left">本站价：</div>
											<div class="info_li_right">
												<span>{{ fruit.siteprice }}</span>
											</div>
										</li>
									</ul>
								</div>
								<div class="info_bottom">
									<div class="add_cart">
										<a href="javascript:void(0)" @click="addCart"><img src="../../assets/gw.jpg" class="cart_img" /></a>
									</div>
									<div class="add_love">
										<a href="/home"><img src="../../assets/jw.jpg" class="love_img" /></a>
									</div>
								</div>
							</div>
						</div>
						<div class="fruit_details_add">
							<div class="fruit_details_add_img"><img src="../../assets/product_desc.gif" /></div>
							<div class="fruit_details_add_info"></div>
						</div>
						<div class="divider_2"><span>水果运送</span></div>
						<div class="send_info">
							<div class="send_info_span"><span>配送原则：</span></div>
							<div class="send_info_p">
								<p>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您的订单得到确认以后，我们将在2个工作日之内将水果送到您所登记的地址，但由于市场货源变动较大，部分热销商品可能会存在缺货导致送货延时或取消订单的特殊情况，请您谅解！
								</p>
							</div>
						</div>
						<div class="divider_2"><span>水果评论：</span></div>
					</el-main>
				</el-container>
			</el-container>
		</div>
	</div>
</template>

<script>
import API from '../../api/api_show';

export default {
	created() {
		this.username = this.$store.getters.username;
	},
	data() {
		return {
			dialogImageUrl: '',
			dialogVisible: false,
			count: 1,
			fruit: [],
			username:'',
			navList: [
				{ name: '/home', navItem: '首页' },
				{ name: '/sort', navItem: '水果分类' },
				{ name: '/login', navItem: '水果礼盒' },
				{ name: '/order', navItem: '我的订单' },
				{ name: '/cart', navItem: '购物车' }
			],
			imgURL: [{ url: require('./../../assets/banner.jpg') }, { url: require('./../../assets/banner2.jpg') }, { url: require('./../../assets/banner3.jpg') }]
		};
	},
	methods: {
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
					this.$router.go('/login');
				})
				.catch(() => {
					this.$message({
						type: 'info',
						message: '已取消'
					});
				});
		},
		handleSelect(key, keyPath) {
			console.log(key, keyPath);
		},
		handleOpen(key, keyPath) {
			console.log(key, keyPath);
		},
		handleClose(key, keyPath) {
			console.log(key, keyPath);
		},
		handleSizeChange(val) {
			console.log(`每页 ${val} 条`);
		},
		handleCurrentChange(val) {
			console.log(`当前页: ${val}`);
		},
		handlePictureCardPreview() {
			this.dialogImageUrl = this.fruit.base64Str;
			this.dialogVisible = true;
		},

		addCart() {
			let that = this;
			console.log('ckID', Number(that.$store.getters.myuser));
			let params = {
				userId: Number(that.$store.getters.myuser),
				count: that.count,
				fruitnum: that.$route.query.id,
				siteprice: that.fruit.siteprice,
				fruitname: that.fruit.fruitname
			};

			API.addtocart(params)
				.then(res => {
					console.log(res);
					that.$router.push({ path: '/cart' });
				})
				.catch(error => {
					console.log('error');
				});
		},

		search: function() {
			let that = this;
			console.log('ckID', Number(that.$store.getters.myuser));
			let params = {
				fruitnum: that.$route.query.id
			};
			console.log(params);
			API.findFru(params)
				.then(res => {
					console.log(res);
					that.fruit = res;
				})
				.catch(error => {
					console.log('error');
				});
		}
	},
	mounted() {
		this.search();
	}
};
</script>

<style scoped lang="scss">
.container {
	position: absolute;
	top: 0px;
	bottom: 0px;
	width: 100%;

	.top_container {
		width: 100%;
		height: 30px;
		background: #f3f3f3;

		.top {
			width: 1200px;
			margin: 0 auto;
			height: 30px;

			.top_left {
				float: left;
				height: 30px;
				line-height: 30px;
				display: inline;
				margin: 0 0 0 20px;
			}

			.top_right {
				float: right;
				height: 30px;
				line-height: 30px;
				display: inline;
				margin: 0 20px 0 0;
				color: red;
			}
		}
	}

	.search {
		width: 100%;
		border-bottom: 5px solid #098a30;

		.search_total {
			width: 1200px;
			margin: 0 auto;
			height: 120px;
			padding: 10px 0 0 0;

			.search_left {
				float: left;
				margin-right: 50px;

				.logo {
					margin-right: 50px;
					float: left;

					.logo_image {
						width: 218px;
						height: 95px;
					}
				}

				.find {
					float: right;
					margin-top: 30px;

					.search_input {
						width: 265px;
					}
				}
			}

			.search_right {
				float: right;

				.navigation {
					width: auto;
					height: 100%;
					margin-top: 20px;
					font-size: 20px;

					.navigation_menu_item {
						font-size: 16px;
					}

					.navigation_menu_item.is-active {
						color: #409eff;
						font-weight: bold;
						font-size: 20px;
					}
				}
			}
		}
	}

	.exhibition {
		width: 100%;
		height: 225px;

		.rotation {
			margin: 0 auto;
			padding: 10px 0 15px 0;
			width: 1200px;
			height: 200px;

			.el-carousel__item:nth-child(2n) {
				background-color: #99a9bf;
			}

			.el-carousel__item:nth-child(2n + 1) {
				background-color: #d3dce6;
			}
		}
	}

	.show_main {
		width: 100%;
		height: 900px;

		.show_main_container {
			width: 1198px;
			height: 900px;
			margin: 10px auto;

			.elaside_0 {
				border: 1px solid #67c23a;
				background-color: #67c23a;

				.elaside_total {
					padding: 15px 15px 0;

					.sort_title {
						overflow: hidden;
						text-align: center;

						.span_1 {
							font-size: 12px;
							color: #fff;
							font-family: arial, \5fae\8f6f\96c5\9ed1, \5b8b\4f53;
						}
						.span_2 {
							position: relative;
							font-size: 24px;
							font-weight: 555;
							height: 30px;
							line-height: 30px;
							color: #fff;
							padding: 0 20px 15px;
							border-bottom: 1px solid #e1e1e1;
						}
					}

					.elmenu_total {
						border: none;
						background-color: #f3f3f3;

						.elmenu_submenu {
							border-bottom: 1px solid #dddddd;
						}

						.el-submenu /deep/.el-submenu__title {
							font-size: 18px;
							font-weight: 500;
							color: #333333;
						}

						.el-submenu /deep/ .el-menu-item {
							color: #666666;
							height: 25px;
							line-height: 25px;
						}
					}
				}

				.elaside_total_bottom {
					padding: 5px 15px 5px 15px;
					height: 300px;
				}
			}
			.elaside_1 {
				border: none;
			}

			.show_main_container_children {
				border: 1px solid #cdcdcd;
				width: 900px;

				.el-header {
					background-color: #fcfcfc;
					border-bottom: 1px solid #eee;

					.elheader_left {
						float: left;
						width: 156px;
						padding: 21px 0;

						.elheader_span {
							font-size: 16px;
							line-height: 1;
							color: red;
						}
					}

					.elheader_right {
						float: right;
						padding: 22px 0;
						width: 700px;
					}
				}

				.el-main {
					position: relative;

					.fruit_details {
						height: 340px;
						padding: 10px;

						.fruit_details_picture {
							height: 340px;
							width: 450px;
							float: left;
							background-color: #f3f3f3;

							.fruit_details_img {
								margin: 20px 25px 20px 25px;
								width: 400px;
								height: 300px;
							}
						}
						.fruit_details_info {
							float: right;
							height: 340px;
							width: 406px;

							.info_top {
								height: 250px;
								display: flex;
								align-items: center;

								.info_ul {
									width: 100%;
									margin: 0;

									.info_li {
										list-style-type: none;

										height: 34px;
										line-height: 34px;

										.info_li_left {
											float: left;
											width: 30%;
											text-align: right;
											display: inline-block;
										}
										.info_li_right {
											float: right;
											width: 70%;
											text-align: left;
											display: inline-block;
										}
									}
								}
							}

							.info_bottom {
								width: 100%;
								height: 70px;
								display: flex;
								align-items: center;

								.add_cart {
									height: 40px;
									width: 50%;
									display: inline-block;
									text-align: center;

									a:hover {
										color: #ff0000;
										text-decoration: underline;
										position: relative;
										left: 1px;
										top: 1px;
									}
								}

								.add_love {
									height: 40px;
									width: 50%;
									display: inline-block;
									text-align: center;

									a:hover {
										color: #ff0000;
										text-decoration: underline;
										position: relative;
										left: 1px;
										top: 1px;
									}
								}
							}
						}
					}

					.fruit_details_add {
						height: 140px;
						margin: 0 10px;

						.fruit_details_add_img {
							width: 100%;
							height: 36px;
							padding: 2px 0;
							text-align: left;
						}

						.fruit_details_add_info {
							width: 100%;
							height: 100px;
						}
					}

					.divider_2 {
						margin: 0 10px;
						height: 30px;
						line-height: 30px;
						font-weight: 555;
						background-color: #d4edf4;
						text-align: left;
					}

					.send_info {
						margin: 0 10px;
						height: 100px;

						.send_info_span {
							width: 100%;
							color: red;
							height: 40px;
							line-height: 30px;
							display: flex;
							align-items: center;
							text-align: left;
						}

						.send_info_p {
							width: 100%;
							height: 60px;
							font-size: 14px;
							display: flex;
							align-items: center;
							text-align: left;

							p {
								margin: 0;
							}
						}
					}
				}
			}
		}
	}
}
</style>
