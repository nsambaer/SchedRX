<template>
  <div class = "appointment-container">
    <div v-for="appointment in appointments" v-bind:key = "appointment.appointmentId" class="appointment-slot">
        <table class="appointment-slot-table">
          <tr class="appointment-first-row">
            <td>Appt Date: {{appointment.appointmentDate}}</td>
            <td>Time: {{appointment.appointmentTime}}</td>
            <td> with Doctor {{appointment.doctorId}}</td>
          </tr>
          <tr class="appointment-second-row">
            <td>Appt Type: {{appointment.appointmentType}}</td>
            <td></td>
            <td>Visit Reason: {{appointment.visitReason}}</td>
          </tr>
        </table>
    </div>

    </div>
</template>

<script>
import patientService from '@/services/PatientService'

export default {
    name: "patient-appointments",

    data(){
        return{
patientId: this.$store.state.user.id,
appointments: []
        }
    },

    created() {
    patientService
      .getAppointments(this.$store.state.user.id)
      .then((response) => {
        if (response.status == 200) {
          this.appointments = response.data;
        }
      })
      .catch((error) => {
        const response = error.response;
        this.errors = true;
        if (response.status === 400) {
          this.errorMsg = "Bad Request: Validation Errors";
        }
      });
}

}
</script>

<style>
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