<template>
  <nav class="navbar navbar-expand-lg navbar-light navbar-laravel">
    <div class="container">
        <router-link to="/" class="navbar-brand">Home</router-link>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ml-auto" v-if="!user">
                <li class="nav-item">
                    <router-link to="/login" class="nav-link">Login</router-link>
                </li>
                <li class="nav-item">
                    <router-link to="/register" class="nav-link">Register</router-link>
                </li>
            </ul>
            <ul class="navbar-nav ml-auto" v-if="user">
                <li class="nav-item">
                    <a href="javascript:void(0)" @click="handleClick" class="nav-link">Logout</a>
                </li>
            </ul>

        </div>
    </div>
</nav>
</template>

<script>
import {mapGetters} from 'vuex'
import axios from 'axios'

export default {
    name: 'Nav',
    methods: {
        handleClick() {
            localStorage.removeItem('token');
            delete axios.defaults.headers.common["Authorization"];
            this.$store.dispatch('user', null)
            this.$router.go();
        }
    },
    computed: {
        ...mapGetters(['user'])
    }


}
</script>

<style>
    @import url(https://fonts.googleapis.com/css?family=Raleway:300,400,600);

    .navbar-laravel
    {
        box-shadow: 0 2px 4px rgba(0,0,0,.04);
    }

    .navbar-brand , .nav-link
    {
        font-family: Raleway, sans-serif;
    }



</style>