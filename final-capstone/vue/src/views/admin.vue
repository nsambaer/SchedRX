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
    <div class="patient-search-container">
      <patient-search/>
    </div>
    

    
  </div>
</template>

<script>
//import adminService from "../services/AdminService.js";
import OfficeAdmin from "../components/OfficeAdmin.vue";
import RegisterDoctor from "../components/RegisterDoctor.vue";
import ResetPassword from "../components/AdminResetPassword.vue";
import NotificationList from "../components/NotificationList.vue";
import PatientSearch from "../components/PatientSearch.vue";

export default {
  name: 'admin',
  components: {
    OfficeAdmin,
    RegisterDoctor,
    ResetPassword,
    NotificationList,
    PatientSearch
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
    grid-template-columns: 1fr, 1fr;
    grid-template-areas:
      "notifications notifications"
      "office-info register-doctor" 
      "patient-search reset-password";
      
    background-color: var(--main-color-dark3);
    border-style: ridge;
    border-color: #004a7c;
    border-width: 4pt;
    font-size: 12pt;
    padding: 2em, 2em, 2em, 2em;

  }

  .notification-container {
    grid-area: notifications;
    padding: 1em 1em 1em 1em;
  }

  .office-info-container {
    grid-area: office-info;
    padding: 1em 1em 1em 1em;
  }

  .register-doctor-container {
    grid-area: register-doctor;
    padding: 1em 1em 1em 1em;
  }

  .reset-password-container {
    grid-area: reset-password;
    padding: 1em 1em 1em 1em;
  }

  .patient-search-container {
    grid-area: patient-search;
    padding: 1em 1em 1em 1em;
  }
  @media screen and (max-width: 828px) {
  .admin-view-container {
    grid-template-columns: 1fr;
    grid-template-areas: 
      "notifications"
      "office-info"
      "register-doctor"
      "patient-search"
      "reset-password";
    width: 100%;
  }

  
}

  
</style>