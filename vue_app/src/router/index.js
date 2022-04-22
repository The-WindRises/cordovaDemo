import Vue from 'vue'
import Router from 'vue-router'
import Login from '../views/Login'
import News from '../views/News'
import DB from '../views/DB'
Vue.use(Router)



export default new Router({

  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login
    },{
      path: '/News',
      name: '/News',
      component: News 
    },{
      path: '/DB',
      name: '/DB',
      component: DB 
    }
  ]
})
