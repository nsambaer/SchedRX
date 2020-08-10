<template>
  <div>
    <div class="primary-care">
      <p
        v-show="!primaryDoctor"
      >You have not chose a primary care physician. To book an appointment, please choose a primary physician</p>
      <form v-on:submit.prevent="newPrimaryDoctor">
        <label
          for="primary_doctor-selector"
        >To set or change your primary doctor, please select a doctor:</label>
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
      <form v-on:submit.prevent="submitAppointment">
        <label for="date-selector">Choose a date:</label>
        <input id="date-selector" type="date" required v-model="newAppointment.appointmentDate" />
        <br />

        <div v-for="(times, date) in availability" v-bind:key="date">
          <select
            v-show="newAppointment.appointmentDate == date"
            id="time-selector"
            v-model="newAppointment.appointmentTime"
          >
            <option value selected="true">--Please select a time--</option>
            <option v-for="time in times" v-bind:key="time.id" v-bind:value="time">{{ time }}</option>-->
          </select>
        </div>
        <label for="appointment-type">Select an appointment type: </label>
        <select id="appointment-type" v-model="newAppointment.appointmentType" required>
          <option v-for="type in appointmentTypes" v-bind:key="type.id" v-bind:value="type">{{ type }}</option>
        </select> <br>
        <label for="visit-reason">Please list your reasons for the appointment: </label>
        <textarea id="visit-reason" v-model="newAppointment.visitReason" required/>
        <input type="submit" />
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
      availability: {},
      currentDate: '',
      newAppointment: {
        patientId: "",
        doctorId: "",
        officeId: "",
        appointmentDate: "",
        appointmentTime: "",
        visitReason: "",
        appointmentType: "",
      },
      appointmentTypes: [],
      // primaryDoctorId: this.$store.state.patient.primaryDoctorId
      // primaryDoctorId: '',
      // primaryDoctor: false
    };
  },

  computed: {
    primaryDoctorId() {
      let id = this.$store.state.patient.primaryDoctor.doctorId;
      return id;
    },

    availabilityMonth() {
      return this.newAppointment.appointmentDate.substr(5, 2);
    },

    availabilityYear() {
      return this.newAppointment.appointmentDate.substr(0, 4);
    },
  },

  methods: {
    newPrimaryDoctor() {
      this.$store.commit("NEW_PRIMARY_DOCTOR", this.selectedDoctor.doctorId);
      patientService.updatePrimaryDoctor(this.$store.state.patient);
      this.$forceUpdate();
    },

    primaryDoctor() {
      if (typeof this.primaryDoctorId === "undefined") {
        return false;
      } else {
        return true;
      }
    },

    updateAvailability(month, year) {
      patientService
        .getAvailability(this.primaryDoctorId, month, year)
        .then((response) => {
          this.availability = response.data.availability;
        })
        .catch((error) => {
          const response = error.response;
          this.errors = true;
          if (response.status === 400) {
            this.errorMsg = "Bad Request: Validation Errors";
          }
        });
    },

    submitAppointment() {
      const today = new Date();
      this.newAppointment.patientId = this.patientId;
      this.newAppointment.doctorId = this.primaryDoctorId;
      this.newAppointment.officeId = this.$store.state.patient.primaryDoctor.officeId;
      this.newAppointment.lastUpdatedDate = this.currentDate;
      this.newAppointment.lastUpdatedTime = today.toTimeString;

      patientService.postAppointment(this.newAppointment).then( (response) => {
        alert(response.status + ' ' + response.data);
        this.newAppointment.patientId = "";
        this.newAppointment.doctorId = "";
        this.newAppointment.officeId = "";
        this.newAppointment.appointmentTime = "";
        this.newAppointment.visitReason = "";
        this.newAppointment.appointmentType = "";
        this.updateAvailability(this.availabilityMonth, this.availabilityYear);
      }).catch((error) => {
        const response = error.response;
        this.errors = true;
        if (response.status === 400) {
          this.errorMsg = "Bad Request: Validation Errors";
        }
      });

    }

  },

  watch: {
    availabilityMonth: function (newMonth) {
      this.updateAvailability(newMonth, this.availabilityYear);
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

    patientService
      .getAppointmentTypes()
      .then((response) => {
        this.appointmentTypes = response.data;
      })
      .catch((error) => {
        const response = error.response;
        this.errors = true;
        if (response.status === 400) {
          this.errorMsg = "Bad Request: Validation Errors";
        }
      });

    const today = new Date();
    const currentMonth =  String(today.getMonth() + 1).padStart(2, "0");
    const currentYear = today.getFullYear();
    this.updateAvailability(currentMonth, currentYear);
  },
};
</script>

<style>
</style>