import Vue from 'vue'
import Router from 'vue-router'
import Login from '../views/Login'
import News from '../views/News'
Vue.use(Router)



export default new Router({
  mode:'history',
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login
    },{
      path: '/News',
      name: '/News',
      component: News 
    }
  ]
})
