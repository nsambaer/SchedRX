<template>
  <div class = "appointment-container">
    <h3 class="appointment-header">Your Upcoming Appointments</h3>

    <div v-for="appointment in appointments" v-bind:key = "appointment.appointmentId" class="appointment-slot">

      <table class="appointment-slot-table">
        <tr class="appointment-first-row">
          <td>Appointment Date: {{appointment.appointmentDate}} </td>
          <td>, {{appointment.appointmentTime}}</td>
          <td> at Office {{appointment.officeId}}</td>
        </tr>
        <tr class="appointment-second-row">
          <td>  Patient: {{appointment.patientId}} </td>
          <td><button v-on:click="showAppointmentDetails(appointment.appointmentId)">Show Details </button></td>
        </tr>
        <tr  class="details" :class="appointment.appointmentId == active ? activeClass : 'hidden'">
          <p>Type: {{appointment.appointmentType}}</p>
        <p>Reason: {{appointment.visitReason}}</p>
        </tr>
      </table>
  
        
    </div>

    </div>
</template>

<script>

import doctorService from "../services/DoctorService.js";
export default {
    name: "doctor-appointments",

    data(){
        return{
            activeClass: 'is-visible',
            active:null
        }
    },
    created(){
        doctorService.getAppointments(this.$store.state.user.id).then(
      response => {
        if(response.status == 200){
          this.$store.state.doctorAppointments = response.data;
        }
      }
    );
    },
    computed: {
      appointments() {
        return this.$store.state.doctorAppointments;
    }
    },
    methods:{
      showAppointmentDetails(id){
        if(this.active == id){
        this.active = null;
      } else{
        this.active = id;
      }

      }
    }

    

}
</script>

<style>

:root{
--main-color-turqoise: #086972;
--main-color-blue-green: #01a9b4;
--main-color-light-blue: #87dfd6;
--accent-color-yellow: #fbfd8a;
}

.appointment-slot{
  background-color:whitesmoke;
  border-color: black;
  color: black;
  border: 4px;
  width:90%;
}

.appointment-slot-table{
  background-color:whitesmoke;
  border-color: black;
  color: black;
  border: 4px;
}
.appointment-container{
  display:flex;
  flex-direction: column;
 background-color: var(--main-color-turqoise);
 align-items: center;
 color: white;
 padding:5px;

}
.appointment-header{
  text-align: center;
}

.is-visible{
  display:show;
}

.hidden{
  display: none;
}

</style>