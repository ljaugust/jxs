import * as API from './http'

export default {

	allfru: params => {
		console.log("请求sort列表")
		return API.GET('/api-a/fruit/findAll', params)
	}
}
