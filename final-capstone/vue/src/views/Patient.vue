<template>
  <table>
  <div class="patient-view-container">
    <div class="patient-upcoming-appointments">
      <h1>Upcoming Appointments</h1>
      <patient-appointments />
    </div>
    <div class="book-appointments">
      <h1>Book New Appointment</h1>
      <book-appointment />
    </div>
    <div class="patient-notifications">
      <h1>Notifications</h1>
      <notification-list></notification-list>
    </div>
    <div class="patient-reviews">
      <patient-reviews />
    </div>
    <router-link :to=" {name: 'update-password'}" class="nav-button-password">Update Password</router-link>
  </div>
  </table>
</template>

<script>
//import patientService from "../services/PatientService.js";
import PatientAppointments from "../components/PatientAppointments.vue";
import BookAppointment from "../components/BookAppointment.vue";
import NotificationList from "../components/NotificationList.vue";
import PatientReviews from '@/components/PatientReviews';

export default {
  name: "patient",
  components: {
    PatientAppointments,
    BookAppointment,
    NotificationList,
    PatientReviews
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
table {
  border-style: ridge;
  border-color: #004a7c;
  border-width: 4pt;
  
}
.patient-view-container {
  display: grid;
  grid-template: auto 1fr auto;
}


.book-appointments {
  background-color: #e8f1f5;
}

.patient-upcoming-appointments {
  grid-area: "schedule";
  background-color: #e8f1f5;
  height: auto;
}

.patient-notifications {
  grid-area: "notifications";
  background-color: #e8f1f5;
}

.patient-reviews {
  background-color: #e8f1f5;
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
