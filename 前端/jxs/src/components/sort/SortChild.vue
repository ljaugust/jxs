<template>
	<div>
		<el-row type="flex" justify="center" v-for="(page, index) of pages" :key="index">
			<el-col :span="6" v-for="(item, innerindex) of page" :key="item.id" :offset="innerindex > 0 ? 1 : 0" style="margin-bottom:18px">
				<el-card :body-style="{ padding: '0px' }" class="fruit_card">
					<a :href="'/show?id=' + item.fruitnum"><img :src="item.base64Str" class="fruit_image" /></a>
					<div style="padding: 4px;border-top: 2px solid #EBEEF5;">
						<div class="op">
							<span class="op_span">{{ item.fruitname }}</span>
						</div>
						<div class="bottom clearfix">
							<time class="time"></time>
							<el-button type="text" class="button">操作按钮</el-button>
						</div>
					</div>
				</el-card>
			</el-col>
		</el-row>
	</div>
</template>

<script>
export default {
	name: 'sortchild',
	props: ['fruit'],
	data() {
		return {
			theurl: process.env.VUE_APP_API_ROOT,
			iconBase64: 0
		};
	},
	computed: {
		pages() {
			let that = this;
			console.log(process.env.VUE_APP_API_ROOT);
			console.log(this.fruit);
			const pages = [];
			Object.keys(this.fruit).forEach((item, index) => {
				const page = Math.floor(index / 3); //3代表3条为一行，随意更改
				if (!pages[page]) {
					pages[page] = [];
				}

				pages[page].push(this.fruit[item]);
				console.log(pages);
				console.log(page);
			});
			return pages;
		}
	}
};
</script>

<style lang="scss">
.el-col-6 {
	width: 28%;

	.fruit_card {
		a:hover {
			text-decoration: underline;
			position: relative;
			left: 1px;
			top: 1px;
		}

		.fruit_image {
			width: 100%;
			height: 181px;
			display: block;
		}

		.op {
			height: 20px;
			overflow: hidden;
			margin: 0 2px 0 2px;

			.op_span {
				float: left;
				width: 200px;
				font-size: 14px;
				overflow: hidden;
				text-overflow: ellipsis;
				white-space: nowrap;
			}
		}

		.bottom {
			margin-top: 10px;
			line-height: 9px;

			.time {
				font-size: 12px;
				color: #999;
			}

			.button {
				padding: 0;
				float: right;
			}
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
</style>
