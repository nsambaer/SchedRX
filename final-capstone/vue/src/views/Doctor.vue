<template>
  <div class = "doctor-view-container">
    <div class = "doctor-upcoming-appointments">
      <h1> this is where my upcoming appointments component will be </h1>
    </div>
    <div class="doctor-notifications">
      <h1> this is where the doctor notifications component will be </h1>
    </div>
    <div class="placeholder">
    </div>
    <div class="doctor-update-availability">
      <h1> this is where the doctor update availability component will be </h1>
    </div>

      
  </div>
</template>

<script>
export default {

}
</script>

<style>

.doctor-view-container{
  display:grid;
  grid-template-columns: 2fr 1fr ;
  grid-template-areas: 
  "schedule notifications"
  "schedule update-availability"
  "schedule update-availability";
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


</style>