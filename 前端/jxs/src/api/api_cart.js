import * as API from './http'

export default {

	findcart: params => {
		console.log("请求1")
		return API.GET('/api-a/findcart', params)
	},
	buy: params => {
		return API.POST('/api-a/buy', params)
	},
	delCart: params => {
		return API.POST('/api-a/delCart', params)
	},
	findOrder: params => {
		return API.GET('/api-a/findOrder', params)
	},
	delOrder: params => {
		return API.GET('/api-a/delOrder', params)
	}
}
