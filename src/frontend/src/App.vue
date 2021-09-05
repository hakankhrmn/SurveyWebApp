<template>
  <div id="app">
    <Nav />
    <router-view/>
  </div>
</template>

<script>
import {mapGetters} from 'vuex'
import axios from 'axios'
import Nav from './views/Nav.vue'

export default {
  name: 'App',
  components: {
    Nav,
  },
  async created() {
      const response = await axios.get('/');
      this.$store.dispatch('user', response.data);
      console.log(response);
  },
  computed: {
    ...mapGetters(['user'])
  }
}
  
</script>

<style lang="scss">
@import url('https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap');

#app {
  font-family: 'Roboto', Helvetica, Arial, sans-serif;
  margin-top: 150px;
  text-align: center;
  color: #2c3e50;
}
</style>
