import * as API from './http'

export default {

	upfru: params => {
		console.log("请求1")
		return API.POST('/api-a/admincenter/addFru', params)
	},
	login: params => {
		console.log("请求login")
		return API.POST('/uaa/oauth/token', params)
	},
	mmp: params => {
		return API.GET('/api-a/fruit/cnm', params)
	},
	findMesg: params => {
		return API.GET('/api-a/findMesg')
	}
}
