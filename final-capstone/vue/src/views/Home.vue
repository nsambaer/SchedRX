<template>
  <div class="home">
    <div class="logout">
      <router-link id="logout" v-bind:to="{ name: 'logout' }" v-show="loggedIn" class="logout-button">Logout</router-link>
    </div>

    <div class="welcome-box-parallax">
      <h1>Welcome!</h1>
      <p>Welcome to the SchedRX Medical Scheduling application! Please enjoy this <br /> interactive awesome medical scheduling expereince. Remember, whatever you do, <br /> don't refresh your browser!</p>
      </div>
    
    <div class="login-register" v-show="!loggedIn">
      
      <login></login>
      
    </div>
    <div v-show="loggedIn">
      <router-link :to="{name: 'redirect'}" tag="button" class="logout-button">User page</router-link>
    </div>
    <p></p>
    <office-details />
  </div>
</template> 

<script>
import login from '@/components/Login';
import OfficeDetails from '@/components/OfficeDetails';

export default {
  name: "home",
  data() {
    return {
      showForgotPass: false
    }
  },

  components: {
    login, 
    OfficeDetails,
    
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

.logout{
  display:flex;
  justify-content: flex-end;
}

.logout-button{
  display:flex;
  align-items: center;
  color: var(--main-color-dark4);
  font-size: 1.5em;
  border: ridge;
 
  justify-content: center;
  text-align: center;
  height: 50px;
  width: 15%;
  margin: 10px;
  background-color: var(--main-color-dark2);
}
.logout-button-text{
  display: flex;
  justify-items: center;
  text-align: center;
  font-size: 1.5em;
  color: white;
}

.logout-button:hover{
  background-color: var(--main-color-dark4);
  color:black;
}
.logout-button .logout-button-text:hover{
  color:black;
}


</style>
