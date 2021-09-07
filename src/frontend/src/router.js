import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'
import Surveys from './views/Surveys.vue'
import Login from './views/Login.vue'
import Register from './views/Register.vue'
import ForgotPass from './views/ForgotPass.vue'
import ResetPass from './views/ResetPass.vue'
import NonActiveSurveys from './views/NonActiveSurveys.vue'

Vue.use(Router)

export default new Router({
    routes: [
        {path: '/', name: 'Home', component: Home},
        {path: '/survey', name: 'Surveys', component: Surveys},
        {path: '/survey/nonactives', name: 'NonActiveSurveys', component: NonActiveSurveys},
        {path: '/login', name: 'Login', component: Login},
        {path: '/register', name: 'Register', component: Register},
        {path: '/forgot_password', name: 'ForgotPass', component: ForgotPass},
        {path: '/reset_password/:token', name: 'ResetPass', component: ResetPass}
    ]
})
