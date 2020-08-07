<template>
  <div class = "appointment-container">
    <div v-for="appointment in appointments" v-bind:key = "appointment.id">
        <h2>Appointment Date: {{appointment.appointmentDate}}</h2>
        <h3>{{appointment.appointmentTime}}</h3>
        <h4> {{appointment.patientId}}</h4>
        <p>{{appointment.appointmentType}}</p>
        <p>{{appointment.visitReason}}</p>
    </div>

    </div>
</template>

<script>
export default {
    name: "patient-appointments",

    data(){
        return{

        }
    },

computed: {
    primaryDoctor() {
        if (typeof this.primaryDoctorId === 'undefined') {
            return false;
        } else {
            return true;
        }
    }
}

}

</script>

<style>

</style>