<template>
  <div class = "doctor-availability-container">
    <ul>
      <div v-for="(times, date) in availability.availability" v-bind:key = "date">
      <li  v-if="times != null">
          <button v-on:click="showDetails(date)" >{{date}} </button>
        <ul>
          <div class="details" :class="date == active ? activeClass : 'hidden'">
            <button v-on:click="blockDate(date)">Block Entire Day </button>
           <li v-for="time in times" v-bind:key="time" >
            <button class="time-button" v-on:click="blockTime(time,date)">{{time}}</button>
          </li>
          </div>
        </ul>
      </li>
      </div>
  
    </ul>
</div>
</template>

<script>
export default {
  name: "doctor-availability",
   props:['availability'],
  data(){
    return{

      newAvailability: {
        doctorId: this.$store.state.user.id,
        specificOpenHours: "",
        specificCloseHours: ""
      },
      activeClass: 'is-visible',
      active: null

      
    }
  },
    methods:{
    showDetails(date){
      this.active = date;
    
    },

    blockTime(time,date){
      window.alert(`you booked an appt at ${time} on ${date}`)
    },
    blockDate(date){
      window.alert(`you have blocked the ${date}`)
    }
  }
 

  

}
</script>

<style>
.is-visible{
  display:show;
}

.hidden{
  display: none;
}
</style>