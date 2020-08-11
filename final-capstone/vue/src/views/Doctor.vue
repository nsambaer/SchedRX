<template>
  <div class = "doctor-view-container">
    <div class = "doctor-upcoming-appointments">
    
      
      <doctor-appointments />
    </div>
    <div class="doctor-notifications">
      <h1> this is where the doctor notifications component will be </h1>
      <notification-list />
    </div>
    <div class="doctor-reviews">
      <h1> this is where the doctor reviews component will go </h1>
    </div>
    <div class="doctor-update-availability">
     
      <doctor-availability />

      
    </div>

      
  </div>
</template>

<script>
import DoctorAppointments from "../components/DoctorAppointments.vue"
import DoctorAvailability  from "../components/DoctorAvailability.vue"
import NotificationList from '@/components/NotificationList'

export default {

  name:"doctor",
  components:{
    DoctorAppointments,
    DoctorAvailability,
    NotificationList
  },
  data(){
    return{
      appointments:[],
      availability:[]
    };
    
  },
  created(){
    this.$store.dispatch()
  }

}
</script>

<style>

.doctor-view-container{
  display:grid;
  grid-template-columns: 2fr 1fr ;
  grid-template-areas: 
  "schedule notifications"
  "schedule notifications"
  "update-availability reviews";
}

.doctor-upcoming-appointments{
grid-area:schedule;
/* background-color: khaki; */

}


.doctor-notifications{
grid-area:notifications;
/* background-color: lavenderblush; */
}

.doctor-update-availability{
  grid-area: update-availability;
  /* background-color: lightgreen; */
}

.doctor-reviews{
  grid-area: reviews;
  /* background-color: lightcoral; */
}


</style>