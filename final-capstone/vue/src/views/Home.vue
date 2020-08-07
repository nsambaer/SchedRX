<template>
  <div class="home">
    <h1>Home</h1>
    <div class="login-register" v-show="!loggedIn">
      <router-link :to="{name:'register'}">register</router-link>
      <login></login>
    </div>
    <div v-show="loggedIn">
      <router-link :to="{name: 'redirect'}" tag="button">User page</router-link>
    </div>
    <p></p>
    <office-list></office-list>
  </div>
</template> 

<script>
import login from '@/components/Login';
import officeList from '@/components/OfficeList';
export default {
  name: "home",

  components: {
    login, 
    officeList
  },

  computed: {
    loggedIn() {
      if (this.$store.state.token === '') {
        return false;
      } else {
      return true;
      }
    }
  }

};
</script>
