<template>

    <main class="login-form">
    <div class="cotainer">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header">Login</div>
                    <error v-if="error" :error = "error"/>
                    <div class="card-body">
                        <form @submit.prevent="handleSubmit" class="my-form">

                            <div class="form-group row">
                                <label for="email_address" class="col-md-4 col-form-label text-md-right">E-Mail Address</label>
                                <div class="col-md-6">
                                    <input type="text" id="email_address" class="form-control" name="email-address" v-model="username" required autofocus>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="password" class="col-md-4 col-form-label text-md-right">Password</label>
                                <div class="col-md-6">
                                    <input type="password" id="password" class="form-control" name="password" v-model="password" required>
                                </div>
                            </div>

                            <div class="col-md-6 offset-md-4">
                                <button type="submit" class="btn btn-primary">
                                    Login
                                </button>
                                <router-link to="/forgot_password" class="btn btn-link">
                                    Forgot Your Password?
                                </router-link>
                            </div>

                        </form>
                    </div>
                </div>
            </div>
        </div>

    </div>
    </main>


</template>

<script>
import axios from 'axios'
import Error from '@/components/Error.vue'

export default {
    name: 'Login',
    components: {
        Error
    },
    comments: {
        Error
    },
    data() {
        return {
            username: '',
            password: '',
            error: ''
        }
    },
    methods: {
        async handleSubmit() {
            try {
                const response = await axios.post('/login', {
                    username: this.username,
                    password: this.password
                });

                this.$store.dispatch('user', response.data.userDto);
                localStorage.setItem('token', response.data.token);
                axios.defaults.headers.common['Authorization'] = 'Bearer ' + response.data.token;
                console.log(response);
                this.$router.push('/');
            }catch (e) {
                this.error = 'Invalid mail or password';
            }

        }

    }
}
</script>

<style>
    @import url(https://fonts.googleapis.com/css?family=Raleway:300,400,600);

    .login-form
    {
        font-family: Raleway, sans-serif;
    }

    .my-form
    {
        padding-top: 1.5rem;
        padding-bottom: 1.5rem;
    }

    .my-form .row
    {
        margin-left: 0;
        margin-right: 0;
    }

    .login-form
    {
        padding-top: 1.5rem;
        padding-bottom: 1.5rem;
    }
    .row
    {
        padding-top: 1rem;
        padding-bottom: 1rem;
    }

    .login-form .row
    {
        margin-left: 0;
        margin-right: 0;
    }
</style>