<template>
  <div>
    <div class="primary-care">
      <p
        v-show="primaryDoctorId === 0"
      >You have not chosen a primary care physician. To book an appointment, please choose a primary physician</p>
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
    <div v-show="primaryDoctorId > 0">
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
        <p>Select a location for your appointment: </p>
        <input type="radio" id="office" value="false" v-model="newAppointment.virtual" />
        <label for="office">In-person</label>
        <input type="radio" id="virtual" value="true" v-model="newAppointment.virtual" />
        <label for="virtual">Virtual</label>
        <br>
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
import notifService from '@/services/NotificationService';

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
        patient: {},
        doctor: {},
        office: {},
        appointmentDate: "",
        appointmentTime: "",
        virtual: '',
        visitReason: "",
        appointmentType: "",
      },
      appointmentTypes: [],
      notification: {
        userId: '',
        message: ''
      }
      // primaryDoctorId: this.$store.state.patient.primaryDoctorId
      // primaryDoctorId: '',
      // primaryDoctor: false
    };
  },

  computed: {
    primaryDoctorId() {
      let id;
      if (this.$store.state.patient.primaryDoctor === undefined){
        id = 0;
      } else {
        id = this.$store.state.patient.primaryDoctor.doctorId;
        console.log("the primary doctor id is " + id);
      }
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
      this.updateAvailability(this.availabilityMonth, this.availabilityYear)
      this.$forceUpdate();
    },

    primaryDoctor() {
      if (this.primaryDoctorId === 0) {
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
      this.newAppointment.patient.patientId = this.patientId;
      this.newAppointment.doctor.doctorId = this.primaryDoctorId;
      this.newAppointment.office.officeId = this.$store.state.patient.primaryDoctor.officeId;
      this.newAppointment.lastUpdatedDate = this.currentDate;
      this.newAppointment.lastUpdatedTime = today.toTimeString;

      patientService.postAppointment(this.newAppointment).then( (response) => {
        alert(response.status + ' You have booked an appointment!');

        this.notification.userId = this.primaryDoctorId;
        this.notification.message = `A new appointment has been booked with ${this.$store.state.patient.firstName} ${this.$store.state.patient.lastName} at ${this.newAppointment.appointmentTime} on ${this.newAppointment.appointmentDate}`

        notifService.createNotification(this.notification).then( () => {

        }).catch((error) => {
        const response = error.response;
        this.errors = true;
        if (response.status === 400) {
          this.errorMsg = "Bad Request: Validation Errors";
        }
      });
      this.newAppointment.patientId = "";
      this.newAppointment.doctorId = "";
      this.newAppointment.officeId = "";
      this.newAppointment.appointmentTime = "";
      this.newAppointment.virtual = "";
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