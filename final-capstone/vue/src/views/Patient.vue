<template>
<div class = "patient-view-container">
    <div class = "patient-upcoming-appointments">
      <h1> Patient upcoming appointments component will be </h1>
    
    <patient-appointments v-bind:appointments="appointments" />
    </div>
    <div class="patient-notifications">
      <h1> Patient notifications including updated appointments and prescriptions </h1>
    </div>
    <div class="placeholder">
    </div>
  </div>
  
</template>

<script>

import patientService from "../services/PatientService.js";
import PatientAppointments from "../components/PatientAppointments.vue"
export default {

name:"patient",
  components:{
    PatientAppointments
  },
  data(){
    return{
      appointments:[],
    };
    
  },
  created(){
    patientService.getAppointments(this.$store.state.user.id).then(
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

.patient-view-container{
  display:grid;
  grid-template-columns: 2fr 1fr ;
  grid-template-areas: 
  "notifications notifications"
  "schedule schedule"
  "schedule schedule";
}

.patient-upcoming-appointments{
grid-area:"schedule";
background-color: rgb(151, 161, 161);
height: 400px;
}


.patient-notifications{
grid-area:"notifications";
background-color: rgb(151, 161, 161);
}



</style>
