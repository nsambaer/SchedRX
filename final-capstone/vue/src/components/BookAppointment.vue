<template>
  <div>
    <div class="primary-care">
      <p
        v-show="!primaryDoctor"
      >You have not chose a primary care physician. To book an appointment, please choose a primary physician</p>
      <form v-on:submit="newPrimaryDoctor">
        <label for="primary_doctor-selector">To set or change your primary doctor, please select a doctor: </label>
        <select id="primary-doctor-selector" v-model="selectedDoctor">
          <option
            v-for="doctor in doctors"
            v-bind:key="doctor.doctorId"
            v-bind:value="doctor"
          >{{ doctor.firstName }} {{ doctor.lastName }}</option>
        </select>
        <input type="submit" />
      </form>
    </div>
    <p></p>
    <p></p>
    <div v-show="primaryDoctor">
      <form>
        <label for="date-selector">Choose a date:</label>
        <input id="date-selector" type="date" />
        <br />
        <select id="time-selector">
          <option value selected="true">--Please select a time--</option>
          <!--<option v-for="timeslot in availability" v-bind:key="timeslot.id" value="timeslot" >{{ timeslot }}</option> -->
        </select>
      </form>
    </div>
  </div>
</template>

<script>
import patientService from "@/services/PatientService";

export default {
  name: "book-appointment",

  data() {
    return {
      patientId: this.$store.state.user.id,
      doctors: [],
      selectedDoctor: {},
    };
  },

  computed: {
    primaryDoctorId() {
        return this.$store.state.patient.primaryDoctorId;
    },
    primaryDoctor() {
      if (typeof primaryDoctorId === "undefined") {
        return false;
      } else {
        return true;
      }
    },
  },

  methods: {
    newPrimaryDoctor() {
      this.$store.commit("NEW_PRIMARY_DOCTOR", this.selectedDoctor.doctorId);
      patientService.updatePrimaryDoctor(this.$store.state.patient); 
    },
  },

  created() {
    patientService
      .getDoctors()
      .then((response) => {
        this.doctors = response.data;
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

</style>