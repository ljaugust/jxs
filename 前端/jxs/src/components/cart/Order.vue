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
		<div class="myorder">
			<el-collapse v-for="item in order" :key="item.id" accordion v-model="activeNames">
				<el-collapse-item :name="item.buyid">
					<template slot="title">
						<div @click.stop>
							<span class="order_span">订单号：{{ item.numorder }}</span>
							<el-button type="text" @click.stop="seeMesg(item)" class="order_btn">点击查看订单信息</el-button>
							<el-button type="danger" size="small" icon="el-icon-delete" circle @click.stop="OrderDelete(item.buyid)"></el-button>
							<el-dialog title="订单信息" :visible.sync="dialogMesg" width="30%" center :close-on-click-modal="false" :showClose="false">
								<el-row class="dia_row">
									<el-col>
										<span>一共有 {{ item.count }} 件商品，共计 {{ item.money }} 元</span>
									</el-col>
								</el-row>
								<el-row class="dia_row">
									<el-col :span="8"><span>收货人姓名</span></el-col>
									<el-col :span="16">{{ item.name }}</el-col>
								</el-row>
								<el-row class="dia_row">
									<el-col :span="8"><span>收货人联系电话</span></el-col>
									<el-col :span="16">{{ item.phone }}</el-col>
								</el-row>
								<el-row class="dia_row">
									<el-col :span="8"><span>收货地址</span></el-col>
									<el-col :span="16">{{ item.address }}</el-col>
								</el-row>
								<el-row class="dia_row">
									<el-col :span="8"><span>下单时间</span></el-col>
									<el-col :span="16">{{ item.buytime }}</el-col>
								</el-row>
								<el-row class="dia_row">
									<el-col :span="8"><span>付款方式</span></el-col>
									<el-col :span="16">{{ item.payway }}</el-col>
								</el-row>
								<el-row slot="footer" class="dialog-footer">
									<el-button @click.stop="dialogMesg = false">取 消</el-button>
									<el-button type="primary" @click.stop="dialogMesg = false">确 定</el-button>
								</el-row>
							</el-dialog>
						</div>
					</template>

					<el-table :data="item.carts" tooltip-effect="dark" style="width: 100%" :cell-style="{ 'text-align': 'center' }" :header-cell-style="{ 'text-align': 'center' }">
						<el-table-column type="index" width="50"></el-table-column>
						<el-table-column label="水果编号">
							<template slot-scope="scope" show-overflow-tooltip>
								{{ scope.row.fruitnum }}
							</template>
						</el-table-column>
						<el-table-column label="水果名称">
							<template slot-scope="scope">
								{{ scope.row.fruitname }}
							</template>
						</el-table-column>
						<el-table-column label="订购数量" show-overflow-tooltip>
							<template slot-scope="scope">
								{{ scope.row.count }} 份
							</template>
						</el-table-column>
						<el-table-column label="单价(元/份)" show-overflow-tooltip>
							<template slot-scope="scope">
								{{ scope.row.siteprice }} 元
							</template>
						</el-table-column>
						<el-table-column label="金额" show-overflow-tooltip>
							<template slot-scope="scope">
								{{ scope.row.totalmoney }} 元
							</template>
						</el-table-column>
					</el-table>
				</el-collapse-item>
			</el-collapse>
		</div>
	</div>
</template>

<script>
import API from '../../api/api_cart';
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
			order: [],
			activeNames: [],
			dialogMesg: false,
			username: ''
		};
	},

	methods: {
		findOrder() {
			let params = {
				userId: Number(this.$store.getters.myuser)
			};
			API.findOrder(params).then(res => {
				if (res) {
					this.order = res;
					this.activeNames.push(res[0].buyid);
				}
			});
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
		},
		seeMesg(data) {
			this.userMesg = data;
			this.dialogMesg = true;
		},
		OrderDelete(data) {
			let params = {
				buyid: data
			};
			API.delOrder(params).then(res => {
				if (res == 'success') {
					this.$message({
						type: 'success',
						message: '删除成功'
					});
					this.findOrder();
				}
			});
		}
	},
	watch: {
		order: {
			handler(newValue, oldValue) {
				this.order = newValue;
			},
			deep: true
		}
	},
	mounted() {
		this.findOrder();
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
	.myorder {
		width: 100%;
		padding: 20px;

		.el-collapse {
			width: 1200px;
			margin: 0 auto;

			.el-collapse-item {
				/deep/ .el-collapse-item__header {
					background-color: #f5f7fa;
				}

				/deep/ .el-collapse-item__wrap {
					padding: 0 20px;
				}

				.order_span {
					font-size: 18px;
					margin: 0 20px;
				}

				.dia_row {
					display: flex;
					align-items: center;

					span {
						font-weight: bold;
					}
				}

				/deep/ .order_btn {
					span {
						font-size: 12px;
					}
				}
			}
		}
	}
}
</style>
