<template>
  <div class="home">
    <h1>Home</h1>
    <router-link :to="{name:'register'}">register</router-link>
<login></login>
  </div>
</template> 

<script>
import login from '@/components/Login';
export default {
  name: "home",
  components: {login}
};
</script>
