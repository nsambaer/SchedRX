<template>
  <div class="appointment-container standard-component-container">
    <h3 class="standard-component-header">Upcoming Appointments</h3>
    <div
      v-for="appointment in appointments"
      v-bind:key="appointment.appointmentId"
      class="appointment-slot"
    >
      <table class="appointment-slot-table">
        <tr class="appointment-first-row">
          <div class="apppointment-first-div">
          <b>Date:</b> {{moment(appointment.appointmentDate).format("MMM Do YYYY")}}
          at {{moment({hour: appointment.appointmentTime.substr(0,2), minute: appointment.appointmentTime.substr(4,2)}).format('hh:mm A')}}
          with Doctor {{appointment.doctor.lastName}}
          </div>
        </tr>
        <tr class="appointment-second-row">
          <div class="appointment-second-div">
          <b>Appointment Type: </b>{{appointment.appointmentType}}
          <br />
          
          <b>Visit Reason: </b>{{appointment.visitReason}}
          </div>
        </tr>
      </table>
    </div>
  </div>
</template>

<script>
import patientService from "@/services/PatientService";

export default {
  name: "patient-appointments",

  data() {
    return {
      patientId: this.$store.state.user.id,
      appointments: [],
    };
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
  },
};
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



@media screen and (max-width: 1024px) {
  .appointment-container {
    display: grid;
    grid-template-areas:
      "first-row"
      "second-row";
  }

  .appointment-first-row {
    grid-area: first-row;
  }
  .appointment-second-row {
    grid-area: second-row;
  }
}
</style>