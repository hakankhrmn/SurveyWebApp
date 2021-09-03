<template>
  <main class="login-form">
    <div class="cotainer">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header">Register</div>
                    <div class="card-body">
                        <form @submit.prevent ="handleSubmit">
                            <div class="form-group row">
                                <label for="user-name" class="col-md-4 col-form-label text-md-right">Name</label>
                                <div class="col-md-6">
                                    <input type="text" id="user-name" class="form-control" name="user-name" v-model="userName" required autofocus>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="user-surname" class="col-md-4 col-form-label text-md-right">Surname</label>
                                <div class="col-md-6">
                                    <input type="text" id="user-surname" class="form-control" name="user-surname" v-model="userSurname" required>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="email_address" class="col-md-4 col-form-label text-md-right">E-Mail Address</label>
                                <div class="col-md-6">
                                    <input type="email" id="email_address" class="form-control" name="email-address" v-model="userMail" required>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="password" class="col-md-4 col-form-label text-md-right">Password</label>
                                <div class="col-md-6">
                                    <input type="password" id="password" class="form-control" name="password" v-model="userPassword" required>
                                </div>
                            </div>

                            <div class="col-md-6 offset-md-4">
                                <button class="btn btn-primary">
                                    Register
                                </button>
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

export default {
    name: 'Register',
    data(){
        return {
            userName: '',
            userSurname: '',
            userMail: '',
            userPassword: ''
        }
    },
    methods: {
        async handleSubmit(){
            await axios.post('/register',{
                userName: this.userName,
                userSurname: this.userSurname,
                userMail: this.userMail,
                userPassword: this.userPassword
           });
           this.$router.push('/login');
        }
    }

}
</script>

<style>

</style>