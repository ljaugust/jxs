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
						<el-menu :default-active="this.$route.path" class="navigation_menu" router mode="horizontal">
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
						<div class="fruit_card_1"><sort-child :fruit="fruits"></sort-child></div>
						<div class="page">
							<el-pagination
								@size-change="handleSizeChange"
								@current-change="handleCurrentChange"
								:current-page="pag"
								:page-sizes="[6, 9, 12, 15]"
								:page-size="pageSize"
								layout="total, sizes, prev, pager, next, jumper"
								:total="totalNum"
							></el-pagination>
						</div>
					</el-main>
				</el-container>
			</el-container>
		</div>
	</div>
</template>

<script>
import SortChild from '@/components/sort/SortChild';
import API from '../../api/api_sort';

export default {
	components: {
		SortChild
	},
	created() {
		this.username = this.$store.getters.username;
	},
	data() {
		return {
			totalNum: 0,
			pageSize: 9,
			pag: 1,
			navList: [
				{ name: '/home', navItem: '首页' },
				{ name: '/sort', navItem: '水果分类' },
				{ name: '/login', navItem: '水果礼盒' },
				{ name: '/order', navItem: '我的订单' },
				{ name: '/cart', navItem: '购物车' }
			],
			fruits: [],
			username: '',
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
			this.pageSize = val;
			this.search();
		},
		handleCurrentChange(val) {
			this.pag = val;
			this.search();
		},

		search: function() {
			let that = this;
			let params = {
				page: that.pag,
				limit: that.pageSize
			};

			API.allfru(params)
				.then(res => {
					that.fruits = res.data.records;
					that.totalNum = res.data.total;
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

		.show_main_container {
			width: 1198px;

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
			}
			.elaside_1 {
				border: none;
			}

			.show_main_container_children {
				border: 1px solid #cdcdcd;

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

					.fruit_card_1 {
						margin: 10px 10px;
						width: 856px;

						.el-col-6 {
							width: 28%;

							.fruit_card {
								.fruit_image {
									width: 100%;
									height: 181px;
									display: block;
								}

								.time {
									font-size: 12px;
									color: #999;
								}

								.bottom {
									margin-top: 10px;
									line-height: 9px;
								}

								.button {
									padding: 0;
									float: right;
								}

								.clearfix:before,
								.clearfix:after {
									display: table;
									content: '';
								}

								.clearfix:after {
									clear: both;
								}
							}
						}
					}

					.page {
						margin: 0 10px;
						padding: 10px 0 5px 0;
						width: 856px;
						height: 36px;
						border-top: 2px solid #d5d5d5;
					}
				}
			}
		}
	}
}
</style>
