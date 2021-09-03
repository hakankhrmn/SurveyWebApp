<template>
<div>
  <h3 v-if="user">Hello, {{user.userName}} {{user.userSurname}}</h3>
  <h3 v-if="!user">You are not logged in</h3>
  <Survey v-if="user" />
</div>
</template>

<script>
import {mapGetters} from 'vuex'
import Survey from "./Survey";
export default {
    name: 'Home',
    components: {Survey},
    computed: {
      ...mapGetters(['user'])
    }
}
</script>

<style>

</style>