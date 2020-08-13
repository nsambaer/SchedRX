<template>
  <div class = "standard-component-container">
   
      <h3 class="standard-component-header">Book a New Appointment</h3>
      <div class=" book-appointment-container">
      <p
        v-show="primaryDoctorId === 0"
      >You have not chosen a primary care physician. To book an appointment, please choose a primary physician</p>
      <form v-on:submit.prevent="newPrimaryDoctor">
        <label
          for="primary_doctor-selector"
        ><b>Please select your preferred doctor:</b></label>
        <select id="primary-doctor-selector" v-model="selectedDoctor">
          <option value="" selected disabled hidden>Select Doctor</option>
          <option
            v-for="doctor in doctors"
            v-bind:key="doctor.doctorId"
            v-bind:value="doctor"
          >{{ doctor.firstName }} {{ doctor.lastName }}</option>
        </select>
        <input class="standard-button" type="submit" value = "Update Primary Doctor"/>
      </form>
    
   <br />
  
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
            <option v-for="time in times" v-bind:key="time.id" v-bind:value="time">{{ moment({hour: time.substr(0,2), minute: time.substr(4,2)}).format('hh:mm A') }}</option>-->
          </select>
          
        </div>
        <br>
        <b>Select a location for your appointment: </b>
        <br />
        <input type="radio" id="office" value="false" v-model="newAppointment.virtual" />
        <label for="office" class="normal">In-person</label>
        <input type="radio" id="virtual" value="true" v-model="newAppointment.virtual" />
        <label for="virtual" class="normal">Virtual</label>
        <br>
        <br>
        <label for="appointment-type">Select an appointment type: </label>
        <select id="appointment-type" v-model="newAppointment.appointmentType" required>
          <option v-for="type in appointmentTypes" v-bind:key="type.id" v-bind:value="type">{{ type }}</option>
        </select> 
        <br>
        <br>
        <label for="visit-reason">Please list your reasons for the appointment: </label>
        <textarea id="visit-reason" v-model="newAppointment.visitReason" required/>
        <br />
        <input class="standard-button" type="submit" value="Book Appointment" />
      </form>
    </div>
    </div>
  </div>
</template>

<script>
import patientService from "@/services/PatientService";
import notifService from '@/services/NotificationService';
import moment from 'moment';

export default {
  name: "book-appointment",

  data() {
    return {
      patientId: this.$store.state.user.id,
      doctors: [],
      selectedDoctor: this.$store.state.patient.primaryDoctor,
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
      this.updateAvailability(this.availabilityMonth, this.availabilityYear);
      window.alert(`Primary doctor updated to ${this.selectedDoctor.firstName} ${this.selectedDoctor.lastName}` );
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

      patientService.postAppointment(this.newAppointment).then( () => {
        alert('You have booked an appointment!');

        this.notification.userId = this.primaryDoctorId;
        this.notification.message = `A new appointment has been booked with ${this.$store.state.patient.firstName} ${this.$store.state.patient.lastName} at ${moment({hour: this.newAppointment.appointmentTime.substr(0,2), minute: this.newAppointment.appointmentTime.substr(4,2)}).format('hh:mm A')} on ${moment(this.newAppointment.appointmentDate).format("MMM Do YYYY")}`

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

.book-appointment-container{
  display: flex;
  align-items: left;
  flex-direction: column;
}



.book-appointment-container label{
  font-weight: bold;
}

.book-appointment-container label.normal{
  font-weight:normal;
}



</style>