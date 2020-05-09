import Vue from 'vue'
import Router from 'vue-router'
import store from "../vuex/store"

import Home from '@/components/Home'
import Show from '@/components/show/Show'
import Sort from '@/components/sort/Sort'
import Admin from '@/components/admin/Admin'
import AdminLogin from '@/components/admin/adminLogin'
import Cart from '@/components/cart/Cart'
import Order from '@/components/cart/Order'


const Login = resolve => require(['@/components/Login'], resolve)
Vue.use(Router)

let router = new Router({
	mode: 'history',
	routes: [{
			path: '/',
			redirect: '/login'
		},
		{
			path: '/login',
			name: '登录',
			component: Login,
			meta: {
				title: '京鲜生--登录'
			}
		},
		{
			path: '/login/admin',
			name: '管理员登录',
			component: AdminLogin,
			meta: {
				title: '京鲜生--管理员登录'
			}
		},
		{
			path: '/admincenter',
			name: '后台管理',
			component: Admin,
			meta: {
				title: '京鲜生--后台管理'
			}
		},
		{
			path: '/home',
			name: '首页',
			component: Home,
			meta: {
				title: '京鲜生--首页'
			}
		},
		{
			path: '/show',
			name: '水果详情',
			component: Show,
			meta: {
				title: '京鲜生--水果详情'
			}
		},
		{
			path: '/sort',
			name: '水果分类',
			component: Sort,
			meta: {
				title: '京鲜生--水果分类'
			}
		},
		{
			path: '/cart',
			name: '购物车',
			component: Cart,
			meta: {
				title: '京鲜生--购物车'
			}
		},
		{
			path: '/order',
			name: '我的订单',
			component: Order,
			meta: {
				title: '京鲜生--我的订单'
			}
		}
	]
})

router.beforeEach((to, from, next) => {
	if (to.path.startsWith('/login')) {
		store.commit('removeToken');
		next();
	} else {
		let token = store.getters.token;
		if (!token) {
			next({
				path: '/login',
				query: {
					redirect: to.fullPath //将跳转的路由path作为参数，登录成功后跳转到该路由
				}
			})
		} else {
			next()
		}
	}
})

export default router
