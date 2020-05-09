import axios from 'axios'
import store from "../vuex/store";

axios.defaults.withCredentials = true;

//添加一个请求拦截器
axios.interceptors.request.use(
	config => {
		// 判断是否存在token，如果存在的话，则每个http header都加上token
		if (store.getters.token) {
			config.headers.Authorization = store.getters.token;
		}
		store.state.loading = true
		console.log("请求完毕")
		return config
	},
	error => {
		return Promise.reject(error)
	}
);

// 添加一个响应拦截器
axios.interceptors.response.use(function(response) {
	store.state.loading = false
	console.log("响应完毕")
	return response
}, function(error) {
	if (error.response) {
		switch (error.response.status) {
			case 401:
				// 返回 401 清除token信息并跳转到登录页面
				store.commit('removeToken');
				store.commit('removeUser');
				router.replace({
					path: '/login',
					query: {
						redirect: router.fullPath
					}
				})
		}
	}
	store.state.loading = false
	return Promise.reject(error);
});

//基地址
let base = "http://localhost:8002"


//通用方法
export const POST = (url, params) => {
	return axios.post(`${base}${url}`, params).then(res => res.data)
}

export const GET = (url, params) => {
	return axios.get(`${base}${url}`, {
		params: params
	}).then(res => res.data)
}

export const PUT = (url, params) => {
	return axios.put(`${base}${url}`, params).then(res => res.data)
}

export const DELETE = (url, params) => {
	return axios.delete(`${base}${url}`, {
		params: params
	}).then(res => res.data)
}

export const PATCH = (url, params) => {
	return axios.patch(`${base}${url}`, params).then(res => res.data)
}
