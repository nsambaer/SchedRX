<template>
  <div class="admin-view-container">
    <div class="office-info-container">
      <office-admin/>
    </div>
    <div class="register-doctor-container">
      <register-doctor/>
    </div>
    

    
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
    adminService.getOffice(this.$store.state.user.id).then( response => {
            this.$store.commit("SET_CURRENT_OFFICE", response.data);
        });
  }
}
</script>

<style>
  .admin-view-container {
    display: grid;
    grid-template-columns: 2fr, 1fr;
    grid-template-areas: 
      "office-info register-doctor";
  }

  .office-info-container {
    grid-area: "office-info"
  }

  .register-doctor-container {
    grid-area: "register-doctor"
  }
</style>