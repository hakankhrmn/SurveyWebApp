<template>
  <main class="login-form">
    <div class="cotainer">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header">Forgot Password</div>
                    <div class="card-body">
                        <form @submit.prevent="handleSubmit">

                            <div class="form-group row">
                                <label for="email_address" class="col-md-4 col-form-label text-md-right">E-Mail Address</label>
                                <div class="col-md-6">
                                    <input type="text" id="email_address" class="form-control" name="email-address" v-model="userMail" required autofocus>
                                </div>
                            </div>

                            <div class="col-md-6 offset-md-4">
                                <button type="submit" class="btn btn-primary">
                                    Submit
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
    name: 'ForgotPass',
    data() {
        return {
            userMail: ''
        }
    },
    methods: {
        async handleSubmit(){
            const response = await axios.post('/forgot_password', {
                userMail: this.userMail
            });
            console.log(response);
        }
    }

}
</script>
