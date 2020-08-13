<template>
  <div class = "appointment-container">
    <h3 class="appointment-header">Your Upcoming Appointments</h3>

    <div v-for="appointment in appointments" v-bind:key = "appointment.appointmentId" class="standard-display-slot">

      <table class="appointment-slot-table">
        <tr class="appointment-first-row">
          <div class="appointment-first-div">
            <b>Appointment Date:</b> {{moment(appointment.appointmentDate).format("MMM Do YYYY")}} at {{moment({hour: appointment.appointmentTime.substr(0,2), minute: appointment.appointmentTime.substr(4,2)}).format('hh:mm A')}}
          </div>

         
        </tr>
        <tr class="appointment-second-row">
          <div class="appointment-second-div">
            <b>Patient:</b> {{appointment.patient.firstName}} {{appointment.patient.lastName}}
           </div> 
        </tr>
        <tr class="appointment-third-row">
          <div class="appointment-third-div">
            <button class="standard-button" v-on:click="showAppointmentDetails(appointment.appointmentId)">Show Details </button>
            <button class="standard-button" v-on:click="editAppointmentDetails(appointment.appointmentId)">Edit Appointment </button>
          </div>
        </tr>
        <tr  class="details" :class="appointment.appointmentId == activeDetails ? activeClass : 'hidden'">
          <div class="appointment-details-div">
              <b>Type:</b> {{appointment.appointmentType}} <br />
              <b>Format:</b> {{appointment.isVirtual}} <br />
              <b>Reason:</b> {{appointment.visitReason}}
            </div>
    
        </tr>
        <tr  class="edit-appointment" :class="appointment.appointmentId == activeEdit ? activeClass : 'hidden'" >
          <div class="edit-appointment-div">
            <label for="date-selector">Choose a new date: </label>
            <input id="date-selector" type="date" required v-model="newAppointment.appointmentDate" />
              <button class="standard-button" v-on:click="deleteAppointment(appointment)">Delete Appointment </button>
            <div v-for="(times, date) in availability.availability" v-bind:key="date"  >
             
              <div class="appointment-time-selector" v-show="date==newAppointment.appointmentDate">
                <label for="time-selector">Please choose a time: </label>
              <select id="time-selector" v-model="newAppointment.appointmentTime"  >
                <option v-for="time in times" v-bind:key="time" v-bind:value="time">{{ time }}</option>
              </select>
              </div>
              
              <button class="standard-button" v-on:click="submitNewAppointment(appointment)" v-show="(date==newAppointment.appointmentDate) && (newAppointment.appointmentTime != '')" >Change Appointment </button>

          </div>
                 
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
import moment from 'moment';
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

      deleteAppointment(appointment){
        doctorService.deleteAppointment(appointment.appointmentId).then(() => {
          window.alert('Appointment Deleted!')
          this.newNotification.userId = appointment.patient.patientId;
          this.newNotification.message = `Doctor ${appointment.doctor.lastName} has canceled your appointment on ${moment(appointment.appointmentDate).format("MMM Do YYYY")} at ${moment({hour: appointment.appointmentTime.substr(0,2), minute: appointment.appointmentTime.substr(4,2)}).format('hh:mm A')}`;
          this.sendNotification(this.newNotification);
          this.getAppointments();
        }).catch((error) => {
          const response = error.response;
          this.errors = true;
          if (response.status === 400) {
            this.errorMsg = "Bad Request: Validation Errors";
          }
        });
      },
      submitNewAppointment(appointment){
        let oldAppointmentDate = appointment.appointmentDate;
        let oldAppointmentTime = appointment.appointmentTime;
        appointment.appointmentDate = this.newAppointment.appointmentDate;
        appointment.appointmentTime = this.newAppointment.appointmentTime;

        this.newNotification.userId = appointment.patient.patientId;
          this.newNotification.message = `Doctor ${appointment.doctor.lastName} changed your appointment from ${moment(oldAppointmentDate).format("MMM Do YYYY")} at ${moment({hour: oldAppointmentTime.substr(0,2), minute: oldAppointmentTime.substr(4,2)}).format('hh:mm A')} to ${moment(appointment.appointmentDate).format("MMM Do YYYY")} at ${moment({hour: appointment.appointmentTime.substr(0,2), minute: appointment.appointmentTime.substr(4,2)}).format('hh:mm A')}`;

        doctorService.updateAppointment(appointment.appointmentId, appointment).then( (response) => {
          if(response.status == 202){
            window.alert("Appointment Updated!")
            this.activeEdit = null;
            this.sendNotification(this.newNotification);
            this.getAppointments();
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
        doctorService.sendNotification(notification).then( (response) => {
          if (response.status == 201) {
            this.newNotification.userId = 0;
            this.newNotification.message = '';
          }
   
        }).catch((error) => {
          const response = error.response;
          this.errors = true;
          if (response.status === 400) {
            this.errorMsg = "Bad Request: Validation Errors";
          }
        });
      },

      getAppointments() {
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
      }

    }

    

}
</script>

<style>






.appointment-container{
  display:flex;
  flex-direction: column;
 background-color: var(--main-color-dark3);
 align-items: center;
 color: white;
 padding:5px;
 border:2px solid var(--main-color-dark1);

}
.appointment-header{
  text-align: center;
  color: var(--main-color-dark1)
}

.is-visible{
  display:show;
}

.hidden{
  display: none;
}

.appointment-slot{
    display: flex;
    background-color:var(--main-color-dark4);
    border-color: black;
    color: black;
    border: 1px solid black;
    width:90%;
    margin:3px;
    border-radius: 3px;
    padding:3px;

  }



</style>