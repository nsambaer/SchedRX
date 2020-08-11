<template>
  <div class="admin-view-container">
    <div class="office-info-container">
      <office-admin/>
    </div>
    <div class="register-doctor-container">
      <register-doctor/>
    </div>
    <div class="reset-password-container">
      <reset-password/>
    </div>
    <div class="notification-container">
      <notification-list/>
    </div>
    

    
  </div>
</template>

<script>
//import adminService from "../services/AdminService.js";
import OfficeAdmin from "../components/OfficeAdmin.vue";
import RegisterDoctor from "../components/RegisterDoctor.vue";
import ResetPassword from "../components/AdminResetPassword.vue";
import NotificationList from "../components/NotificationList.vue";

export default {
  name: 'admin',
  components: {
    OfficeAdmin,
    RegisterDoctor,
    ResetPassword,
    NotificationList
  },
  data() {
    return {
    }
  },

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