<template>
  <div class="admin-view-container">
    <div class="admin-office" >
      <office-details v-bind:office="currentOffice"/>
    </div>
    <div class="register-doctor">

    </div>
    
  </div>
</template>

<script>
import adminService from "../services/AdminService.js";
import OfficeDetails from "../components/OfficeDetails.vue";
export default {
  components: {
    OfficeDetails
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