import * as API from './http'

export default {

	addtocart: params => {
		console.log("请求加入购物车")
		return API.POST('/api-a/addCart', params)
	},
	findFru: params => {
		console.log("请求水果详情")
		return API.GET('/api-a/fruit/findFru', params)
	}
}
