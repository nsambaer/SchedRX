<template>
  <div class="home">
    <div class="welcome-box-parallax">
      <h1>Welcome!</h1>
      <p>Welcome to the SchedRX Medical Scheduling application! Please enjoy this <br /> interactive awesome medical scheduling expereince. Remember, whatever you do, <br /> don't refresh your browser!</p>
      </div>
    
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
.home{
  display:flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
}

.login-register{
  display:flex;
  justify-content: flex-end;
  align-items:flex-start;
}


</style>
