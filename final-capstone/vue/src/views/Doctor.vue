<template>
  <div class="doctor-view-container">
    <div class="doctor-upcoming-appointments">
      <doctor-appointments />
    </div>
    <div class="doctor-notifications">
      <notification-list />
    </div>
    <div class="doctor-reviews">
      <doctor-reviews />
    </div>
    <div class="doctor-update-availability">
      <doctor-availability />
    </div>
  </div>
</template>

<script>
import DoctorAppointments from "../components/DoctorAppointments.vue";
import DoctorAvailability from "../components/DoctorAvailability.vue";
import NotificationList from "@/components/NotificationList";
import doctorService from "../services/DoctorService";
import DoctorReviews from "@/components/DoctorReviews";

export default {
  name: "doctor",
  components: {
    DoctorAppointments,
    DoctorAvailability,
    NotificationList,
    DoctorReviews,
  },
  data() {
    return {
      appointments: [],
      availability: [],
    };
  },
  created() {
    const today = new Date();
    const currentMonth = today.getMonth() + 1;
    const currentYear = today.getFullYear();
    doctorService
      .getAvailability(this.$store.state.user.id, currentMonth, currentYear)
      .then((response) => {
        
        this.$store.state.availability = response.data.availability;
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

.doctor-view-container{
  display:grid;
  grid-template-columns: 2fr 1fr ;
  grid-template-areas: 
  "schedule notifications"
  "update-availability notifications"
  "reviews notifications";
  gap: 10px;
}

@media screen and (max-width: 828px) {
  .doctor-view-container{
    grid-template-columns: 1fr;
    grid-template-areas: 
      "notifications"
      "schedule"
      "update-availability"
      "reviews";
  }

}
.doctor-upcoming-appointments{
grid-area:schedule;
/* background-color: khaki; */

}

.doctor-notifications {
  grid-area: notifications;
  /* background-color: lavenderblush; */
}

.doctor-update-availability {
  grid-area: update-availability;
  /* background-color: lightgreen; */
}

.doctor-reviews {
  grid-area: reviews;
  /* background-color: lightcoral; */
}
</style>