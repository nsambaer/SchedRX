<template>
  <div class = "doctor-availability-container">
    <ul>
    
      <li v-for="(times, date) in availability.availability" v-bind:key = "date" >
          <button v-on:click="showTimes(date)" >{{date}} </button>
        <ul>
          <li v-for="time in times" v-bind:key="time" v-show="date.show">
            {{time}}
          </li>
        </ul>
      </li>
  
    </ul>
</div>
</template>

<script>
export default {
  name: "doctor-availability",
  data(){
    return{
      
    }
  },

  props:['availability']

}
</script>

<style>

</style>