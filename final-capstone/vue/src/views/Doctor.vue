<template>
  <div class = "doctor-view-container">
    <div class = "doctor-upcoming-appointments">
      <h1> this is where my upcoming appointments component will be </h1>
      
      <doctor-appointments v-bind:appointments="appointments" />
    </div>
    <div class="doctor-notifications">
      <h1> this is where the doctor notifications component will be </h1>
    </div>
    <div class="doctor-reviews">
      <h1> this is where the doctor reviews component will go </h1>
    </div>
    <div class="doctor-update-availability">
      <h1> this is where the doctor update availability component will be </h1>
      <doctor-availability v-bind:availability="availability" />

      
    </div>

      
  </div>
</template>

<script>

import doctorService from "../services/DoctorService.js";
import DoctorAppointments from "../components/DoctorAppointments.vue"
import DoctorAvailability  from "../components/DoctorAvailability.vue"
export default {

  name:"doctor",
  components:{
    DoctorAppointments,
    DoctorAvailability
  },
  data(){
    return{
      appointments:[],
      availability:[]
    };
    
  },
  created(){
    doctorService.getAppointments(this.$store.state.user.id).then(
      response => {
        if(response.status == 200){
          this.appointments = response.data;
        }
      }
    );

    doctorService.getAvailability(this.$store.state.user.id, 8, 2020).then(
      response => {
       
          this.availability = response.data;
        
      }
    )
      
    
  }

}
</script>

<style>

.doctor-view-container{
  display:grid;
  grid-template-columns: 2fr 1fr ;
  grid-template-areas: 
  "schedule notifications"
  "schedule update-availability"
  "reviews update-availability";
}

.doctor-upcoming-appointments{
grid-area:"schedule";
background-color: khaki;
height: 400px;
}


.doctor-notifications{
grid-area:"notifications";
background-color: lavenderblush;
}

.doctor-update-availability{
  grid-area: "update-availability";
  background-color: lightgreen;
}

.doctor-reviews{
  grid-area: "reviews";
  background-color: lightcoral;
}


</style>