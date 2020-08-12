<template>
  <div class="home">
    
    <h1>Home</h1>
    <div class="login-register" v-show="!loggedIn">
      
      <login></login>
      <button v-on:click="showForgotPass = !showForgotPass">Forgot Password?</button>
      <forgot-password v-if="showForgotPass"></forgot-password>
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
import forgotPassword from '@/components/ForgotPassword';
export default {
  name: "home",
  data() {
    return {
      showForgotPass: false
    }
  },

  components: {
    login, 
    officeList,
    forgotPassword
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
<style >
h1 {
  color: var(--main-color-dark1);
}
</style>
