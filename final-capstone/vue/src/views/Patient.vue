<template>
  <div class="patient-view-container">
    <div class="patient-upcoming-appointments">
      <h1>Patient upcoming appointments component will be</h1>
      <patient-appointments v-bind:appointments="appointments" />
    </div>
    <div class="book-appointments">
      <h1>Patients book appointments based on availability</h1>
      <book-appointment />
    </div>
    <div class="patient-notifications">
      <h1>Patient notifications including updated appointments and prescriptions</h1>
    </div>
    <div class="patient-reviews"></div>
  </div>
</template>

<script>
import patientService from "../services/PatientService.js";
import PatientAppointments from "../components/PatientAppointments.vue";
import BookAppointment from "../components/BookAppointment.vue";

export default {
  name: "patient",
  components: {
    PatientAppointments,
    BookAppointment,
  },
  data() {
    return {
      appointments: [],
    };
  },
  created() {
    patientService
      .getAppointments(this.$store.state.user.id)
      .then((response) => {
        if (response.status == 200) {
          this.appointments = response.data;
        }
      })
      .catch((error) => {
        const response = error.response;
        this.errors = true;
        if (response.status === 400) {
          this.errorMsg = "Bad Request: Validation Errors";
        }
      });

    patientService
      .getPatient(this.$store.state.user.id)
      .then((response) => {
        this.$store.commit('SET_PATIENT', response.data)
      })
      .catch((error) => {
        const response = error.response;
        this.errors = true;
        if (response.status === 400) {
          this.errorMsg = "Bad Request: Validation Errors";
        }
      });
  },
};
</script>

<style>
.patient-view-container {
  display: grid;
  grid-template-columns: 2fr 1fr;
  grid-template-areas:
    "book notifications"
    "schedule update-availability"
    "reviews reviews";
}

.book-appointments {
  grid-area: "book";
  background-color: rgb(178, 236, 255);
}

.patient-upcoming-appointments {
  grid-area: "schedule";
  background-color: rgb(178, 236, 255);
  height: 400px;
}

.patient-notifications {
  grid-area: "notifications";
  background-color: rgb(178, 236, 255);
}

.patient-reviews {
  grid-area: "reviews";
  background-color: rgb(178, 236, 255);
}
</style>
