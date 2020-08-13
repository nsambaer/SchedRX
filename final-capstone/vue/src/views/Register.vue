<template>
  <div class="registration-container">
    <div id="nav">
      <router-link  v-bind:to="{ name: 'home' }" class="nav-button">
     
        Home
     
      </router-link>
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''" class="nav-button">Logout</router-link>
        
    </div>
    <div class="registration-component">
    <register></register>
    </div>
  </div>
  
</template>

<script>
import register from '@/components/Register';
export default {
components: {register}
}
</script>

<style>

  .registration-container {
    display: grid;
    grid-template-columns: 1fr;
    grid-template-areas: 
    "nav"
    "register";
  }

.registration-component {
  grid-area: register;
}

  #nav {
    display: flex;
    justify-content: space-between;
    width: 100%;
    grid-area: nav;
  }


</style>