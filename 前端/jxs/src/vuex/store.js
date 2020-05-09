import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);
const state = {
	loading: false,
	params: [],
	token: localStorage.getItem('access_token') ? localStorage.getItem('access_token') : '',
	buy: [],
	myuser: localStorage.getItem('user') ? localStorage.getItem('user') : '',
	username: localStorage.getItem('username') ? localStorage.getItem('username') : '',
}
const mutations = {
	addParam(state, value) {
		state.params.push(value)
	},
	addToken(state, payload) {
		state.token = payload;
		localStorage.setItem('access_token', state.token);
	},
	removeToken(state) {
		localStorage.removeItem('access_token');
	},
	addbuy(state, payload) {
		state.buy = payload;
	},
	addUser(state, payload) {
		state.myuser = payload;
		localStorage.setItem('user', state.myuser);
	},
	removeUser(state) {
		localStorage.removeItem('user');
	},
	addUserName(state, payload) {
		state.username = payload;
		localStorage.setItem('username', state.username);
	},
	removeUserName(state) {
		localStorage.removeItem('username');
	}
}

//getters只会依赖state的成员去更新
const getters = {
	token: (state) => state.token,
	buy: (state) => state.buy,
	myuser: (state) => state.myuser,
	username: (state) => state.username,
}

export default new Vuex.Store({
	state,
	mutations,
	getters
});
