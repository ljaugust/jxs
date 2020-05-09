import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './vuex/store'
import ElementUI from 'element-ui' //element-ui的全部组件
import 'element-ui/lib/theme-chalk/index.css' //element-ui的css 
import axios from 'axios'


Vue.config.productionTip = false
Vue.use(ElementUI) //使用elementUI


/* new Vue({
	el: '#app',
	router,
	template: '<App/>',
	components: {
		App
	}
}) */

router.beforeEach((to, from, next) => {
	/* 路由发生变化修改页面title */
	if (to.meta.title) {
		document.title = to.meta.title
	}
	next()
})

new Vue({
	router,
	store,
	render: h => h(App)
}).$mount("#app")
