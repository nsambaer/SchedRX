<template>
  <div class = "appointment-container">
    <h3 class="appointment-header">Your Upcoming Appointments</h3>

    <div v-for="appointment in appointments" v-bind:key = "appointment.appointmentId" class="appointment-slot">

      <table class="appointment-slot-table">
        <tr class="appointment-first-row">
          <td>Appointment Date: {{appointment.appointmentDate}} </td>
          <td>, {{appointment.appointmentTime}}</td>
          <td> at {{appointment.office.officeName}}</td>
        </tr>
        <tr class="appointment-second-row">
          <td>  Patient: {{appointment.patient.firstName}} {{appointment.patient.lastName}}</td>
          <td><button v-on:click="showAppointmentDetails(appointment.appointmentId)">Show Details </button></td>
          <td><button v-on:click="editAppointmentDetails(appointment.appointmentId)">Edit Appointment </button></td>
        </tr>
        <tr  class="details" :class="appointment.appointmentId == activeDetails ? activeClass : 'hidden'">
          <p>Type: {{appointment.appointmentType}}</p>
        <p>Reason: {{appointment.visitReason}}</p>
        </tr>
        <tr  class="edit-appointment" :class="appointment.appointmentId == activeEdit ? activeClass : 'hidden'" >
                 <label for="date-selector">Choose a new date:</label>
            <input id="date-selector" type="date" required v-model="newAppointment.appointmentDate" />

            <div v-for="(times, date) in availability.availability" v-bind:key="date"  >
             
         

              <select id="time-selector" v-model="newAppointment.appointmentTime" v-show="date==newAppointment.appointmentDate" >
                <option v-for="time in times" v-bind:key="time" v-bind:value="time">{{ time }}</option>
              </select>
              <button v-on:click="submitNewAppointment(appointment)" v-show="(date==newAppointment.appointmentDate) && (newAppointment.appointmentTime != '')" >Change Appointment </button>
            </div>

            <!-- <div v-for="(times, date) in availability" v-bind:key="date">
              <select
                v-show="newAppointment.appointmentDate == date"
                id="time-selector"
                v-model="newAppointment.appointmentTime"
              >
                <option value selected="true">--Please select a time--</option>
                <option v-for="time in times" v-bind:key="time.id" v-bind:value="time">{{ time }}</option>
              </select>
            </div> -->
            
        
    
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
            activeDetails:null,
            activeEdit:null,
            newAppointment: {
                appointmentId:0,
                patientId: "",
                doctorId: "",
                officeId: "",
                appointmentDate: "",
                appointmentTime: "",
                visitReason: "",
                appointmentType: "",
                isVirtual:""
             },
             newNotification:{
               userId: 0,
               message: ""
             }
        }
    },
    created(){
        doctorService.getAppointments(this.$store.state.user.id).then(
      response => {
        if(response.status == 200){
          this.$store.state.doctorAppointments = response.data;
          
        }
      }
    ).catch((error) => {
          const response = error.response;
          this.errors = true;
          if (response.status === 400) {
            this.errorMsg = "Bad Request: Validation Errors";
          }
        });

       
    },


    computed: {
      appointments() {
        return this.$store.state.doctorAppointments; 
    },
      availability(){
        return this.$store.state.availability;
      }
    },
    methods:{
      showAppointmentDetails(id){
        if(this.activeDetails == id){
        this.activeDetails = null;
        
      } else{
        this.activeDetails = id;
      }
      },

      editAppointmentDetails(id){
        if(this.activeEdit == id){
        this.activeEdit = null;
        this.newAppointment.appointmentId = 0;
      } else{
        this.activeEdit = id;
        this.newAppointment.appointmentId = id;
      }
      
      },
      submitNewAppointment(appointment){
        let oldAppointmentDate = appointment.appointmentDate;
        let oldAppointmentTime = appointment.appointmentTime;
        appointment.appointmentDate = this.newAppointment.appointmentDate;
        appointment.appointmentTime = this.newAppointment.appointmentTime;

        this.newNotification.userId = appointment.patient.patientId;
          this.newNotification.message = `Doctor ${appointment.doctor.lastName} changed your appointment from ${oldAppointmentDate} at ${oldAppointmentTime} to ${appointment.appointmentDate} at ${appointment.appointmentTime}`;

        doctorService.updateAppointment(appointment.appointmentId, appointment).then( response => {
            window.alert(response.status);
          if(response.status == 202){
            window.alert("Appointment Updated!")
            
            this.sendNotification(this.newNotification);
          }
        }).catch((error) => {
          const response = error.response;
          this.errors = true;
          if (response.status === 400) {
            this.errorMsg = "Bad Request: Validation Errors";
          }
        });

      },

      sendNotification(notification){
        doctorService.sendNotification(notification).then(response => {
          if(response.status == 201){
            window.alert("Notification created");
          }
        })
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