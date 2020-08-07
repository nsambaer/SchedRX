<template>
  <div class = "doctor-availability-container">
    <div class="doctor-availability">
      <div v-for="(times, date) in availability.availability" v-bind:key = "date">
        <div class = "availiability-date" v-if="times != null">
          <button v-on:click="showDetails(date)" >{{date}} </button>
        <ul>
          <div class="details" :class="date == active ? activeClass : 'hidden'">
            <button v-on:click="blockDate(date)">Block Entire Day </button>
           <li v-for="time in times" v-bind:key="time" >
            <button class="time-button" v-on:click="blockTime(time,date)">{{time}}</button>
          </li>
          </div>
        </ul>
        </div>
      </div>
      </div>
      <div class="set-availability">
        <form v-on:submit="addAvailability()">
        <input type="date" v-model="availabilityDate" />
        <input type="time" v-model="availabilityOpenTime" />
        <input type="time" step="3600" v-model="availabilityCloseTime" />
        <button type="submit">Update Selected Availability </button>
        </form>
      </div>
  
    
</div>
</template>

<script>


export default {
  name: "doctor-availability",
   props:['availability'],
  data(){
    return{
      availabilityDate:"",
      availabilityOpenTime:"",
      availabilityCloseTime:"",

      newAvailability: {
        doctorId: this.$store.state.user.id,
        specificOpenHours: {
          "":""
        },
        specificCloseHours: {
          "":""
        }
      },
      activeClass: 'is-visible',
      active: null

      
    }
  },
    methods:{
    showDetails(date){
      if(this.active == date){
        this.active = null;
      } else{
        this.active = date;
      }
      
    
    },

    blockTime(time,date){
      window.alert(`you booked an appt at ${time} on ${date}`)
    },
    blockDate(date){
      window.alert(`you have blocked the ${date}`)
    }


  },

  computed:{

  }
 

  

}
</script>

<style>
.is-visible{
  display:show;
}

.doctor-availability-container{
  display: flex;
}

.hidden{
  display: none;
}
</style>