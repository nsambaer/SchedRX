<template>
  <!-- <table class="patient-table"> -->
  <div class="patient-view-container">
    <div id="nav">
      <router-link  v-bind:to="{ name: 'home' }" class="nav-button">Home</router-link>
      <h1>Patient Dashboard</h1>
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''" class="nav-button">Logout</router-link>
        
    </div>
    <div class="patient-upcoming-appointments">
      
      <patient-appointments />
    </div>
    <div class="book-appointments">
      
      <book-appointment />
    </div>
    <div class="patient-notifications">
      
      <notification-list></notification-list>
    </div>
    <div class="patient-reviews">
      <patient-reviews />
    </div>
    <div class="update-password">
      <update-password></update-password>
    </div>
    
  </div>
  
</template>

<script>
//import patientService from "../services/PatientService.js";
import PatientAppointments from "../components/PatientAppointments.vue";
import BookAppointment from "../components/BookAppointment.vue";
import NotificationList from "../components/NotificationList.vue";
import PatientReviews from '@/components/PatientReviews';
import UpdatePassword from '../components/UpdatePassword.vue';

export default {
  name: "patient",
  components: {
    PatientAppointments,
    BookAppointment,
    NotificationList,
    PatientReviews,
    UpdatePassword
  },
  data() {
    return {

    };
  },
  created() {

    // patientService
    //   .getPatient(this.$store.state.user.id)
    //   .then((response) => {
    //     this.$store.commit('SET_PATIENT', response.data)
    //   })
    //   .catch((error) => {
    //     const response = error.response;
    //     this.errors = true;
    //     if (response.status === 400) {
    //       this.errorMsg = "Bad Request: Validation Errors";
    //     }
    //   });
  },
};
</script>

<style>
:root{
--main-color-dark1: #004a7c;
--main-color-dark2: #005691;
--main-color-dark3: #e8f1f5;
--main-color-dark4: #fafafa;
}
.patient-view-container {
  /* border-style: ridge;
  border-color: #004a7c;
  border-width: 4pt;
  background-color: #e8f1f5; */
  display: grid;
  grid-template: 1fr;
  grid-template-areas:
    "nav" 
    "notifications"
    "appt-list"
    "book"
    "reviews"
    "password";
  gap:10px;
}

  #nav {
    display: flex;
    justify-content: space-between;
    width: 100%;
    grid-area: nav;
  }

@media screen and (max-width: 1024px) {
  .patient-view-container {
    grid-template-columns: 1fr;
    grid-template-areas: 
      "notifications"
      "appt-list"
      "book"
      "reviews"
      "password";
    width: 100%;
  }
}
.book-appointments {
  background-color: #e8f1f5;
  grid-area: book;
}

.patient-upcoming-appointments {
  grid-area: appt-list;
}

.patient-notifications {
  grid-area: notifications;
  background-color: #e8f1f5;
}

.patient-reviews {
  background-color: #e8f1f5;
  grid-area: reviews;
}

.update-password {
  grid-area: password;
}

.nav-button-password{
  display:flex;
  align-items: center;
  color:#fafafa;
  font-size: 1.5em;
  border-color: #fafafa;
  border: ridge;
 
  justify-content: center;
  text-align: center;
  height: 50px;
  width:auto;
  margin:10px;
  background-color: #004a7c;
}
.nav-button-password-text{
  display: flex;
  justify-items: center;
  text-align: center;
  font-size: 1.5em;
  color: white;
}

.nav-button-password:hover{
  background-color: var(--accent-color-yellow);
  color:black;
}
.nav-button-password-text:hover{
  color:black;
}


</style>
