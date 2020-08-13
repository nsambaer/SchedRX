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
          <td>Appt Date: {{moment(appointment.appointmentDate).format("MMM Do YYYY")}}</td>
          <td>Time: {{moment({hour: appointment.appointmentTime.substr(0,2), minute: appointment.appointmentTime.substr(4,2)}).format('hh:mm A')}}</td>
          <td>with Doctor {{appointment.doctor.lastName}}</td>
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
.appointment-slot {
  background-color: whitesmoke;
  border-color: black;
  color: black;
  border: 4px;
  width: 90%;
}

.appointment-slot-table {
  background-color: whitesmoke;
  border-color: black;
  color: black;
  border: 4px;
}
.appointment-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  color: white;
  padding: 5px;
}
.appointment-header {
  text-align: center;
}

.is-visible {
  display: show;
}

.hidden {
  display: none;
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