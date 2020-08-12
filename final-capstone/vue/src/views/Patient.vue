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

table {
  border-style: ridge;
  border-color: #086972;
  border-width: 4pt;
  
}
.patient-view-container {
  display: grid;
  grid-template: auto 1fr auto;
}


.book-appointments {
  background-color: rgb(178, 236, 255);
}

.patient-upcoming-appointments {
  grid-area: "schedule";
  background-color: rgb(178, 236, 255);
  height: auto;
}

.patient-notifications {
  grid-area: "notifications";
  background-color: rgb(178, 236, 255);
}

.patient-reviews {
  background-color: rgb(178, 236, 255);
}

.nav-button-password{
  display:flex;
  align-items: center;
  color:white;
  font-size: 1.5em;
  border-color: white;
  border: ridge;
 
  justify-content: center;
  text-align: center;
  height: 50px;
  width:auto;
  margin:10px;
  background-color: var(--main-color-blue-green);
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
