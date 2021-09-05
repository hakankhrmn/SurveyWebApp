<template>
  <main class="login-form">
    <div class="cotainer">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header">Reset Password</div>
                    <div class="card-body">
                        <form @submit.prevent="handleSubmit">

                            <div class="form-group row">
                                <label for="password" class="col-md-4 col-form-label text-md-right">Password</label>
                                <div class="col-md-6">
                                    <input type="password" id="password" class="form-control" name="password" v-model="userPassword" required>
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
    name: 'ResetPass',
    config: {
  headers: {
    "Access-Control-Allow-Origin": "*",
    "Access-Control-Allow-Methods": "GET,PUT,POST,DELETE,PATCH,OPTIONS"
  }
},
    data() {
        return {
            userPassword: '',
            token: this.$route.params.token
        }
    },
    methods: {
        async handleSubmit() {
            const response = await axios.post('/reset_password/' + this.token , {
                userPassword: this.userPassword
            });
            console.log(response);
            this.$router.push('/login');
        }
    }

}
</script>