<template>
  <div class="admin-view-container">
    <office-admin/>
    <register-doctor/>

    
  </div>
</template>

<script>
import adminService from "../services/AdminService.js";
import OfficeAdmin from "../components/OfficeAdmin.vue"
import RegisterDoctor from "../components/RegisterDoctor.vue"
export default {
  name: 'admin',
  components: {
    OfficeAdmin,
    RegisterDoctor
  },
  data() {
    return {
      currentOffice: {}
    }
  },
  created() {
    adminService.getOffice(this.$store.state.user.id).then(
      response => {
        if(response.status === 200) {
          this.currentOffice = response.data;
        }
      }
    )
  }
}
</script>

<style>

</style>