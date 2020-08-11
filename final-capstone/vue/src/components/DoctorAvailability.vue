<template>
  <div class = "doctor-availability-container">

    <h3>Add or Adjust Your Availability </h3>

     <div class="set-availability">
        <form v-on:submit.prevent="createAvailability()">
        <input type="date" v-model="availabilityDate" />
       <select v-model="availabilityOpenTime">
          <option value="00:00:00">0:00</option>
          <option value="01:00:00">1:00</option>
          <option value="02:00:00">2:00</option>
          <option value="03:00:00">3:00</option>
          <option value="04:00:00">4:00</option>
          <option value="05:00:00">5:00</option>
          <option value="06:00:00">6:00</option>
          <option value="07:00:00">7:00</option>
          <option value="08:00:00">8:00</option>
          <option value="09:00:00">9:00</option>
          <option value="10:00:00">10:00</option>
          <option value="11:00:00">11:00</option>
          <option value="12:00:00">12:00</option>
          <option value="13:00:00">13:00</option>
          <option value="14:00:00">14:00</option>
          <option value="15:00:00">15:00</option>
          <option value="16:00:00">16:00</option>
          <option value="17:00:00">17:00</option>
          <option value="18:00:00">18:00</option>
          <option value="19:00:00">19:00</option>
          <option value="20:00:00">20:00</option>
          <option value="21:00:00">21:00</option>
          <option value="22:00:00">22:00</option>
          <option value="23:00:00">23:00</option>
          <option value="24:00:00">24:00</option>


        </select>

        <select v-model="availabilityCloseTime">
          <option value="00:00:00">0:00</option>
          <option value="01:00:00">1:00</option>
          <option value="02:00:00">2:00</option>
          <option value="03:00:00">3:00</option>
          <option value="04:00:00">4:00</option>
          <option value="05:00:00">5:00</option>
          <option value="06:00:00">6:00</option>
          <option value="07:00:00">7:00</option>
          <option value="08:00:00">8:00</option>
          <option value="09:00:00">9:00</option>
          <option value="10:00:00">10:00</option>
          <option value="11:00:00">11:00</option>
          <option value="12:00:00">12:00</option>
          <option value="13:00:00">13:00</option>
          <option value="14:00:00">14:00</option>
          <option value="15:00:00">15:00</option>
          <option value="16:00:00">16:00</option>
          <option value="17:00:00">17:00</option>
          <option value="18:00:00">18:00</option>
          <option value="19:00:00">19:00</option>
          <option value="20:00:00">20:00</option>
          <option value="21:00:00">21:00</option>
          <option value="22:00:00">22:00</option>
          <option value="23:00:00">23:00</option>
          <option value="24:00:00">24:00</option>
        </select>
        <button type="submit">Request Availability</button>
        <div class="submit-availability-div" v-show="showSubmitAvailability">
          <p>Availability request allowed! Would you like to submit your availability request? </p>
        <button v-on:click.prevent="submitAvailability" >Submit Availability Request </button>
        </div>
        </form>
      </div>

    <div class="doctor-availability" :key="componentKey">
      <button v-on:click="showCurrentAvailabilities = !showCurrentAvailabilities">Show Current Availabilities for Selected Month</button>
      <div v-for="(times, date) in availability.availability" v-bind:key = "date" v-show="showCurrentAvailabilities">
        <div class = "availability-date" v-if="times != null">
          <p v-on:click="showDetails(date)" >{{date}} </p>
        <table>
          <div class="details" :class="date == active ? activeClass : 'hidden'" >
            <tr class ="availability-row-details">
           <td v-for="time in times" v-bind:key="time" >
            {{time}}
          </td>
          </tr>
          </div>
        </table>
        </div>
      </div>
      </div>
     
  
    
</div>
</template>

<script>
 import doctorService from "../services/DoctorService.js";

export default {

 
  name: "doctor-availability",
  
  data(){
    return{
      availabilityDate:"",
      availabilityOpenTime:"",
      availabilityCloseTime:"",
      showCurrentAvailabilities: false,
      componentKey:0,

      newAvailability: {
        doctorId: this.$store.state.user.id,
        specificOpenHours: {
          
        },
        specificCloseHours: {
          
        }
      },
      activeClass: 'is-visible',
      active: null,
      availability:[],
      showSubmitAvailability: false

      
    }
  },
  beforeCreate(){
   
  },
  created(){
   const today = new Date();
    const currentMonth = today.getMonth()+1;
    const currentYear = today.getFullYear();
    this.updateAvailability(currentMonth,currentYear);  
    
    
  }
  ,
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
    },

    updateAvailability(month, year){
      doctorService.getAvailability(this.$store.state.user.id, month, year).then(
      response => {
       
          this.availability = response.data;
        
      }
    ).catch((error) => {
          const response = error.response;
          this.errors = true;
          if (response.status === 400) {
            this.errorMsg = "Bad Request: Validation Errors";
          }
        });
       

    },
    createAvailability(){
      this.newAvailability.specificOpenHours[this.availabilityDate] = this.availabilityOpenTime;
      this.newAvailability.specificCloseHours[this.availabilityDate] = this.availabilityCloseTime;
      if(this.$store.state.doctorAppointments.some(appointment => appointment.appointmentDate == this.availabilityDate)){
        window.alert("There is already an appointment on that day")
      } else if (this.availabilityOpenTime > this.availabilityCloseTime){
        window.alert("Close time must be laster than open time")
      } else if(this.availabilityDate == ""){
        window.alert("Please select a date");
        }else {
        //window.alert("Availabilty available (lol)")
        this.showSubmitAvailability = true;
      }
    },
    submitAvailability(){
    
      this.newAvailability.specificOpenHours[this.availabilityDate] = this.availabilityOpenTime;
      this.newAvailability.specificCloseHours[this.availabilityDate] = this.availabilityCloseTime;
      doctorService.addAvailability(this.$store.state.user.id,this.newAvailability).then(response => {
        if(response.status == 201){
          window.alert("availability created");
          this.updateAvailability(this.availabilityMonth,this.availabilityYear);
          this.refreshAvailability();
          this.showSubmitAvailability = false;
        }
      }).catch((error) => {
          const response = error.response;
          this.errors = true;
          if (response.status === 400) {
            this.errorMsg = "Bad Request: Validation Errors";
          }
        });
      

      

      
    },
    refreshAvailability(){
      this.componentKey +=1;
    }

  },
  watch:{
    availabilityMonth: function(newMonth){
      this.updateAvailability(newMonth,this.availabilityYear)
    },
    availabilityDate: function() {
      this.showSubmitAvailability = false;
    }
    
  },

  computed:{
      availabilityMonth(){
        return this.availabilityDate.substr(5,2);
      },
      availabilityYear(){
        return this.availabilityDate.substr(0,4);
      },
      currentDoctorId(){
        return this.$store.state.user.id;
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
.is-visible{
  display:show;
}

.doctor-availability-container{
  display: flex;
  flex-direction: column;
}
.availability-date{
  background-color: var(--main-color-blue-green);
}

.hidden{
  display: none;
}
.availability-row-details{
  background: whitesmoke;
}
</style>