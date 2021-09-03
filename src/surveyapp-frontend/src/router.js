import Vue from "vue"
import Router from 'vue-router'
import Survey from './components/Survey.vue'
import Login from './components/Login.vue'
import Register from './components/Register.vue'
import Home from './components/Home.vue'
import ForgotPass from './components/ForgotPass.vue'
import ResetPass from './components/ResetPass.vue'

Vue.use(Router)

export default new Router({
    mode: 'history',
    routes: [
        {path: '/survey', component: Survey},
        {path: '/login', component: Login},
        {path: '/register', component: Register},
        {path: '/', component: Home},
        {path: '/forgot_password', component: ForgotPass},
        {path: '/reset_password/:token', component: ResetPass}
    ]
})