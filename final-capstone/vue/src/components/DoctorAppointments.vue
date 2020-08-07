<template>
  <div class = "appointment-container">
    <div v-for="appointment in appointments" v-bind:key = "appointment.id">
        <h2>Appointment Date: {{appointment.appointmentDate}}</h2>
        <h3>{{appointment.appointmentTime}}</h3>
        <h4> {{appointment.patientId}}</h4>
        <p>{{appointment.appointmentType}}</p>
        <p>{{appointment.visitReason}}</p>
    </div>

    </div>
</template>

<script>

import doctorService from "../services/DoctorService.js";
export default {
    name: "doctor-appointments",

    data(){
        return{
            appointments:[]
        }
    },
    created(){
        doctorService.getAppointments(this.$store.state.user.id).then(
      response => {
        if(response.status == 200){
          this.appointments = response.data;
        }
      }
    );
    }
    

}
</script>

<style>

</style>