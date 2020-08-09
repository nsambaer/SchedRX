<template>
  <div class = "appointment-container">
    <div v-for="appointment in appointments" v-bind:key = "appointment.appointmentId">
        <h2>Appointment Date: {{appointment.appointmentDate}}</h2>
        <h3>{{appointment.appointmentTime}}</h3>
        <h4> {{appointment.doctorId}}</h4>
        <p>{{appointment.appointmentType}}</p>
        <p>{{appointment.visitReason}}</p>
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

</style>