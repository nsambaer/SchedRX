<template>
  <div class="home">
    <h1>Home</h1>
    <router-link :to="{name:'register'}">register</router-link>
<login></login>
<office-list></office-list>
  </div>
</template> 

<script>
import login from '@/components/Login';
import officeList from '@/components/OfficeList';
export default {
  name: "home",
  components: {login, officeList},
};
</script>
