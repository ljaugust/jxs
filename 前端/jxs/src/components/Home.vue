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
					<div class="logo"><img src="../assets/jxs.png" class="logo_image" /></div>
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
			<div class="sort">
				<div class="sort_title"><span>水 果 分 类</span></div>
				<ul class="st_ul">
					<li class="st_li"><a>进口水果</a></li>
					<li class="st_li"><a>国产水果</a></li>
					<li class="st_li"><a>热带水果</a></li>
					<li class="st_li"><a>水果礼盒</a></li>
				</ul>
			</div>
			<div class="rotation">
				<el-carousel height="450px">
					<el-carousel-item v-for="(ban, i) in imgURL" :key="i"><img v-bind:src="ban.url" class="fruit_image" /></el-carousel-item>
				</el-carousel>
			</div>
		</div>
		<div class="photo_show"><img class="show_image" src="../assets/5.jpg" /></div>
		<div class="divider_1">
			<el-divider class="divider_content_1" content-position="center">
				<span class="span_1">
					<span class="span_2">IMPORTED FRUIT</span>
					<br />
					<span>进口水果</span>
				</span>
			</el-divider>
		</div>
		<div class="fruit_display_1">
			<div class="container_2">
				<div class="picture_1"><img src="../assets/a-1-1.jpg" class="img_picture_1" /></div>
				<div class="fruit_card_1">
					<el-row type="flex" justify="center">
						<el-col :span="6" v-for="(o, index) in 3" :key="o" :offset="index > 0 ? 1 : 0">
							<el-card :body-style="{ padding: '0px' }" class="fruit_card">
								<img src="../assets/a.jpg" class="fruit_image" />
								<div style="padding: 4px;border-top: 2px solid #EBEEF5;">
									<span>好吃的芒果</span>
									<div class="bottom clearfix">
										<time class="time"></time>
										<el-button type="text" class="button">操作按钮</el-button>
									</div>
								</div>
							</el-card>
						</el-col>
					</el-row>
					<br />
					<el-row type="flex" justify="center">
						<el-col :span="6" v-for="(o, index) in 3" :key="o" :offset="index > 0 ? 1 : 0">
							<el-card :body-style="{ padding: '0px' }" class="fruit_card">
								<img src="../assets/a.jpg" class="fruit_image" />
								<div style="padding: 4px;border-top: 2px solid #EBEEF5;">
									<span>好吃的芒果</span>
									<div class="bottom clearfix">
										<time class="time"></time>
										<el-button type="text" class="button">操作按钮</el-button>
									</div>
								</div>
							</el-card>
						</el-col>
					</el-row>
				</div>
			</div>
		</div>
		<div class="divider_1">
			<el-divider class="divider_content_1" content-position="center">
				<span class="span_1">
					<span class="span_2">DOMESTIC FRUIT</span>
					<br />
					<span>国产水果</span>
				</span>
			</el-divider>
		</div>
		<div class="fruit_display_1">
			<div class="container_2">
				<div class="picture_1"><img src="../assets/a-1-2.jpg" class="img_picture_1" /></div>
				<div class="fruit_card_1">
					<el-row type="flex" justify="center">
						<el-col :span="6" v-for="(o, index) in 3" :key="o" :offset="index > 0 ? 1 : 0">
							<el-card :body-style="{ padding: '0px' }" class="fruit_card">
								<img src="../assets/b.jpg" class="fruit_image" />
								<div style="padding: 4px;border-top: 2px solid #EBEEF5;">
									<span>好吃的苹果</span>
									<div class="bottom clearfix">
										<time class="time"></time>
										<el-button type="text" class="button">操作按钮</el-button>
									</div>
								</div>
							</el-card>
						</el-col>
					</el-row>
					<br />
					<el-row type="flex" justify="center">
						<el-col :span="6" v-for="(o, index) in 3" :key="o" :offset="index > 0 ? 1 : 0">
							<el-card :body-style="{ padding: '0px' }" class="fruit_card">
								<img src="../assets/b.jpg" class="fruit_image" />
								<div style="padding: 4px;border-top: 2px solid #EBEEF5;">
									<span>好吃的苹果</span>
									<div class="bottom clearfix">
										<time class="time"></time>
										<el-button type="text" class="button">操作按钮</el-button>
									</div>
								</div>
							</el-card>
						</el-col>
					</el-row>
				</div>
			</div>
		</div>
		<div class="divider_1">
			<el-divider class="divider_content_1" content-position="center">
				<span class="span_1">
					<span class="span_2">FRUIT GIFT BOX</span>
					<br />
					<span>水果礼盒</span>
				</span>
			</el-divider>
		</div>
		<div class="fruit_display_1">
			<div class="container_2">
				<div class="picture_1"><img src="../assets/a-1-3.jpg" class="img_picture_1" /></div>
				<div class="fruit_card_1">
					<el-row type="flex" justify="center">
						<el-col :span="6" v-for="(o, index) in 3" :key="o" :offset="index > 0 ? 1 : 0">
							<el-card :body-style="{ padding: '0px' }" class="fruit_card">
								<img src="../assets/c.jpg" class="fruit_image" />
								<div style="padding: 4px;border-top: 2px solid #EBEEF5;">
									<span>新年礼盒</span>
									<div class="bottom clearfix">
										<time class="time"></time>
										<el-button type="text" class="button">操作按钮</el-button>
									</div>
								</div>
							</el-card>
						</el-col>
					</el-row>
					<br />
					<el-row type="flex" justify="center">
						<el-col :span="6" v-for="(o, index) in 3" :key="o" :offset="index > 0 ? 1 : 0">
							<el-card :body-style="{ padding: '0px' }" class="fruit_card">
								<img src="../assets/c.jpg" class="fruit_image" />
								<div style="padding: 4px;border-top: 2px solid #EBEEF5;">
									<span>新年礼盒</span>
									<div class="bottom clearfix">
										<time class="time"></time>
										<el-button type="text" class="button">操作按钮</el-button>
									</div>
								</div>
							</el-card>
						</el-col>
					</el-row>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
export default {
	created() {
		this.username = this.$store.getters.username;
	},
	data() {
		return {
			navList: [
				{ name: '/home', navItem: '首页' },
				{ name: '/sort', navItem: '水果分类' },
				{ name: '/login', navItem: '水果礼盒' },
				{ name: '/order', navItem: '我的订单' },
				{ name: '/cart', navItem: '购物车' }
			],
			imgURL: [{ url: require('./../assets/ban1.jpg') }, { url: require('./../assets/ban2s.jpg') }, { url: require('./../assets/ban3s.jpg') }],
			username: ''
		};
	},
	methods: {
		handleSelect(key, keyPath) {
			console.log(key, keyPath);
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
					this.$router.go('/login');
				})
				.catch(() => {
					this.$message({
						type: 'info',
						message: '已取消'
					});
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
		width: 1200px;
		margin: 0 auto;
		height: 453px;
		padding: 10px 0 0 0;

		.sort {
			float: left;
			width: 250px;
			height: 450px;
			border: 1px solid #67c23a;

			.sort_title {
				font-size: 24px;
				font-weight: 444;
				height: 90px;
				background-color: #42881f;
				color: #fff;

				span {
					line-height: 90px;
					align-items: center;
				}
			}

			.st_ul {
				margin: 0;
				padding: 0;
			}

			.st_li {
				border-radius: 4px;
				border-top: 1px solid #67c23a;
				list-style: none;
				cursor: pointer;
				height: 89px;
				line-height: 89px;
				background-color: #f5f7fa;

				a {
					font-weight: bold;
					font-size: 16px;
					text-decoration: none;
				}

				a:hover {
					color: red;
					text-decoration: underline;
					position: relative;
					left: 1px;
					top: 1px;
				}
			}
		}

		.rotation {
			float: right;
			width: 900px;
			height: 450px;

			.el-carousel__item h3 {
				color: #475669;
				font-size: 14px;
				opacity: 0.75;
				line-height: 150px;
				margin: 0;
			}

			.el-carousel__item:nth-child(2n) {
				background-color: #99a9bf;
			}

			.el-carousel__item:nth-child(2n + 1) {
				background-color: #d3dce6;
			}
		}
	}

	.photo_show {
		width: 1200px;
		height: 238px;
		margin: 0 auto;

		.show_image {
			padding: 25px 0 35px 0;
			width: 100%;
			height: 178px;
		}
	}

	.divider_1 {
		width: 1200px;
		margin: 0 auto;

		.divider_content_1 {
			margin: 28px 0;
			.span_1 {
				font-size: 26px;
				color: #429b49;
				display: block;

				.span_2 {
					font-size: 20px;
					color: #222;
				}
			}
		}
	}

	.fruit_display_1 {
		width: 1200px;
		height: 560px;
		margin: 0 auto;

		.container_2 {
			padding: 20px 0 0 0;
			height: 540px;

			.picture_1 {
				width: 275px;
				height: 540px;
				float: left;

				.img_picture_1 {
					width: 100%;
					height: 490px;
				}
			}

			.fruit_card_1 {
				float: right;
				width: 900px;
				height: 540px;

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
		}
	}
}
</style>
