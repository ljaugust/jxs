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
		<div class="cartlist">
			<el-table
				:header-cell-style="{ background: '#BEBEBE', color: '#606266', 'text-align': 'center' }"
				:cell-style="{ 'text-align': 'center' }"
				border
				ref="multipleTable"
				:data="tableData"
				tooltip-effect="light"
				@selection-change="handleSelectionChange"
				:summary-method="getSummaries"
				:show-summary="showSum"
				:row-style="rowstyle"
			>
				<el-table-column type="selection" width="80"></el-table-column>
				<el-table-column prop="fruitnum" label="水果编号" width="100">
					<template slot-scope="scope">
						{{ scope.row.fruitnum }}
					</template>
				</el-table-column>
				<el-table-column prop="fruitname" label="水果名称" width="350">
					<template slot-scope="scope">
						{{ scope.row.fruitname }}
					</template>
				</el-table-column>
				<el-table-column prop="count" label="订购数量" show-overflow-tooltip>
					<template slot-scope="scope">
						<div @click.stop>
							<el-input-number
								size="small"
								v-model="scope.row.count"
								@change="handleChange($event, scope.row)"
								:step="1"
								:min="1"
								:max="10"
								label="描述文字"
							></el-input-number>
						</div>
					</template>
				</el-table-column>
				<el-table-column prop="siteprice" label="单价(元/份)" show-overflow-tooltip>
					<template slot-scope="scope">
						{{ scope.row.siteprice }}
					</template>
				</el-table-column>
				<el-table-column prop="totalmoney" label="金额" show-overflow-tooltip>
					<template slot-scope="scope">
						{{ scope.row.totalmoney }}
					</template>
				</el-table-column>
				<el-table-column label="操作" width="100">
					<template slot-scope="scope">
						<el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
					</template>
				</el-table-column>
			</el-table>
			<div class="btn">
				<div class="not_select"><el-button @click="toggleSelection()">取消选择</el-button></div>
				<div class="buy"><el-button @click="confirmOrder()">提交订单</el-button></div>
			</div>
			<el-dialog title="确认订单" :visible.sync="dialogTableVisible" width="30%" center :close-on-click-modal="false" :showClose="false">
				<el-row class="dia_row">
					<el-col>
						<span>一共选择 {{ ann }} 件商品，共计 {{ amm }} 元。</span>
					</el-col>
				</el-row>
				<el-row class="dia_row">
					<el-col :span="8"><span>请输入收货人姓名</span></el-col>
					<el-col :span="16"><el-input placeholder="请输入收货人姓名" v-model="name" clearable></el-input></el-col>
				</el-row>
				<el-row class="dia_row">
					<el-col :span="8"><span>请输入收货人联系电话</span></el-col>
					<el-col :span="16"><el-input placeholder="请输入收货人联系电话" v-model="phone" clearable></el-input></el-col>
				</el-row>
				<el-row class="dia_row">
					<el-col :span="8"><span>请输入收货地址</span></el-col>
					<el-col :span="16"><el-input placeholder="请输入收货地址" v-model="address" clearable></el-input></el-col>
				</el-row>
				<el-row class="dia_row">
					<el-col :span="8"><span>请选择支付方式</span></el-col>
					<el-col :span="16">
						<el-radio-group v-model="payway">
							<el-radio :label="1">微信支付</el-radio>
							<el-radio :label="2">支付宝支付</el-radio>
						</el-radio-group>
					</el-col>
				</el-row>
				<el-row slot="footer" class="dialog-footer">
					<el-button @click.stop="dialogTableVisible = false">取 消</el-button>
					<el-button type="primary" @click.stop="allbuy()">确 定</el-button>
				</el-row>
			</el-dialog>
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
			tableData: [],
			multipleSelection: [],
			selectData: [],
			selectRow: [],
			user: 'hhh',
			showSum: true,
			dialogTableVisible: false,
			ann: 0,
			amm: 0,
			payway: 1,
			address: '',
			name: '',
			phone: '',
			username:''
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
		getSummaries(param) {
			let that = this;
			const { columns, data } = param;
			const sums = [];

			columns.forEach((column, index) => {
				if (index === 0) {
					sums[index] = '总价';
					sums[0] = '合计';
					return;
				} else if (index === 3) {
					let an = 0;
					Object.keys(data).forEach(key => {
						if (that.selectRow.includes(Number(key))) {
							an += data[key].count;
						}
						sums[3] = '一共 ' + an + ' 个';
						that.ann = an;
						return;
					});
				} else if (index === 5) {
					let am = 0;
					Object.keys(data).forEach(key => {
						if (that.selectRow.includes(Number(key))) {
							am += data[key].totalmoney;
						}
						sums[5] = '共 ' + am + ' 元';
						that.amm = am;
						return;
					});
				}
			});

			return sums;
		},

		handleSelect(key, keyPath) {
			console.log(key, keyPath);
		},
		handleDelete(index, row) {
			let param = [];
			param.push(row.cartid);
			this.loading = false;
			API.delCart(param).then(res => {
				if (res == 'success') {
					this.search();
					this.showSum = false;
					this.$nextTick().then(() => {
						this.showSum = true;
					});
				}
			});
		},
		handleChange(value, rows) {
			let tm = value * rows.siteprice;
			rows.totalmoney = tm;
			rows.count = value;
		},
		toggleSelection() {
			this.$refs.multipleTable.clearSelection();
		},
		handleSelectionChange(val) {
			this.selectData = val;
			let p = {
				data: this.$refs.multipleTable.data
			};
			this.showSum = false;
			this.$nextTick().then(() => {
				this.showSum = true;
			});
		},
		rowstyle({ row, rowIndex }) {
			if (this.selectRow.includes(rowIndex)) {
				return { 'background-color': '#F0FFFF' };
			}
		},
		confirmOrder() {
			if (this.selectRow.length == 0) {
				this.$message({
					message: '未选择商品',
					type: 'warning'
				});
			} else {
				this.dialogTableVisible = true;
			}
		},
		allbuy() {
			if (this.name == '' || this.phone == '' || this.address == '') {
				this.$message({
					message: '请填写完整信息',
					type: 'warning'
				});
			} else {
				let data = this.$refs.multipleTable.data;
				let p = [];
				this.loading = false;
				Object.keys(data).forEach(key => {
					if (this.selectRow.includes(Number(key))) {
						p.push(data[key]);
					}
				});
				let param = {
					cart: p,
					payway: this.payway,
					address: this.address,
					count: this.ann,
					allMoney: this.amm,
					name: this.name,
					phone: this.phone
				};
				API.buy(param).then(res => {
					if (res == 'success') {
						this.$confirm('购买成功！', '提示', {
							confirmButtonText: '确定',
							type: 'success'
						}).then(() => {
							this.$router.push('/order');
						});
					}
				});
			}
		},
		search: function() {
			let that = this;
			console.log('ckID', Number(that.$store.getters.myuser));
			let params = {
				userId: Number(that.$store.getters.myuser)
			};

			API.findcart(params)
				.then(res => {
					that.tableData = res.data;
				})
				.catch(error => {
					console.log('error');
				});
		}
	},
	watch: {
		selectData(data) {
			this.selectRow = [];
			if (data.length > 0) {
				data.forEach((item, index) => {
					this.selectRow.push(this.tableData.indexOf(item));
				});
			}
		},
		tableData: {
			handler(newValue, oldValue) {
				this.tableData = newValue;
			},
			deep: true
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
	.cartlist {
		width: 100%;
		padding: 20px;

		.el-table {
			width: 1200px;
			margin: 0 auto;

			/deep/ .el-table__body {
				/deep/ .el-table__row td {
					padding: 10px 0;
					text-align: center;
				}

				tr:hover > td {
					background-color: rgba(0, 0, 0, 0) !important;
				}
			}

			/deep/ .el-table__footer {
				/deep/ td {
					padding: 10px 0;
					text-align: center;
				}
			}
		}

		.btn {
			width: 1200px;
			align-items: center;
			margin: 20px auto;

			.not_select {
				display: inline-block;
				margin-right: 15px;
			}
			.buy {
				display: inline-block;
			}
		}

		.dia_row {
			padding: 15px 0;
			display: flex;
			align-items: center;

			span {
				font-weight: bold;
			}
		}
	}
}
</style>
