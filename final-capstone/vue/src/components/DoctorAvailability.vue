<template>
  <div class="doctor-availability-container standard-component-container">
    <h3 class="standard-component-header">Add or Adjust Your Availability</h3>

    <div class="set-availability">
      <form v-on:submit.prevent="createAvailability()">
        <input type="date" v-model="availabilityDate" />
        <select v-model="availabilityOpenTime">
          <option value="00:00:00">12:00 AM</option>
          <option value="01:00:00">1:00 AM</option>
          <option value="02:00:00">2:00 AM</option>
          <option value="03:00:00">3:00 AM</option>
          <option value="04:00:00">4:00 AM</option>
          <option value="05:00:00">5:00 AM</option>
          <option value="06:00:00">6:00 AM</option>
          <option value="07:00:00">7:00 AM</option>
          <option value="08:00:00">8:00 AM</option>
          <option value="09:00:00">9:00 AM</option>
          <option value="10:00:00">10:00 AM</option>
          <option value="11:00:00">11:00 AM</option>
          <option value="12:00:00">12:00 PM</option>
          <option value="13:00:00">1:00 PM</option>
          <option value="14:00:00">2:00 PM</option>
          <option value="15:00:00">3:00 PM</option>
          <option value="16:00:00">4:00 PM</option>
          <option value="17:00:00">5:00 PM</option>
          <option value="18:00:00">6:00 PM</option>
          <option value="19:00:00">7:00 PM</option>
          <option value="20:00:00">8:00 PM</option>
          <option value="21:00:00">9:00 PM</option>
          <option value="22:00:00">10:00 PM</option>
          <option value="23:00:00">11:00 PM</option>
        </select>

        <select v-model="availabilityCloseTime">
          <option value="00:00:00">12:00 AM</option>
          <option value="01:00:00">1:00 AM</option>
          <option value="02:00:00">2:00 AM</option>
          <option value="03:00:00">3:00 AM</option>
          <option value="04:00:00">4:00 AM</option>
          <option value="05:00:00">5:00 AM</option>
          <option value="06:00:00">6:00 AM</option>
          <option value="07:00:00">7:00 AM</option>
          <option value="08:00:00">8:00 AM</option>
          <option value="09:00:00">9:00 AM</option>
          <option value="10:00:00">10:00 AM</option>
          <option value="11:00:00">11:00 AM</option>
          <option value="12:00:00">12:00 PM</option>
          <option value="13:00:00">1:00 PM</option>
          <option value="14:00:00">2:00 PM</option>
          <option value="15:00:00">3:00 PM</option>
          <option value="16:00:00">4:00 PM</option>
          <option value="17:00:00">5:00 PM</option>
          <option value="18:00:00">6:00 PM</option>
          <option value="19:00:00">7:00 PM</option>
          <option value="20:00:00">8:00 PM</option>
          <option value="21:00:00">9:00 PM</option>
          <option value="22:00:00">10:00 PM</option>
          <option value="23:00:00">11:00 PM</option>
        </select>
        <button class="standard-button" type="submit">Request Availability</button>
        <div class="submit-availability-div" v-show="showSubmitAvailability">
          <p>Availability request allowed! Would you like to submit your availability request?</p>
          <button
            class="standard-button"
            v-on:click.prevent="submitAvailability"
          >Submit Availability Request</button>
        </div>
      </form>
    </div>

    <div class="doctor-availability" :key="componentKey">
      <button
        class="standard-button"
        v-on:click="showCurrentAvailabilities = !showCurrentAvailabilities"
      >Show Current Availabilities for Selected Month</button>
      <div
        v-for="(times, date) in this.$store.state.availability"
        v-bind:key="date"
        v-show="showCurrentAvailabilities"
        class="availability-date-container"
      >
        <div class="availability-date" v-if="times != null" v-on:click="showDetails(date)">
          <div class="standard-display-slot date-time-container">
            <p>{{moment(date).format("MMM Do YYYY")}}</p>

            <div class="availability-times" :class="date == active ? activeClass : 'hidden'">
              <div
                v-for="time in times"
                v-bind:key="time"
                class="availability-individual-time"
              >{{moment({hour: time.substr(0,2), minute: time.substr(4,2)}).format('hh:mm A')}}</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import moment from "moment";
import doctorService from "../services/DoctorService.js";

export default {
  name: "doctor-availability",

  data() {
    return {
      availabilityDate: "",
      availabilityOpenTime: "",
      availabilityCloseTime: "",
      showCurrentAvailabilities: false,
      componentKey: 0,

      newAvailability: {
        doctorId: this.$store.state.user.id,
        specificOpenHours: {},
        specificCloseHours: {},
      },
      activeClass: "is-visible",
      active: null,
      availability: [],
      showSubmitAvailability: false,
    };
  },
  beforeCreate() {},
  created() {},
  methods: {
    showDetails(date) {
      if (this.active == date) {
        this.active = null;
      } else {
        this.active = date;
      }
    },

    blockTime(time, date) {
      window.alert(
        `you booked an appt at ${moment({
          hour: time.substr(0, 2),
          minute: time.substr(4, 2),
        }).format("hh:mm A")} on ${moment(date).format("MMM Do YYYY")}`
      );
    },
    blockDate(date) {
      window.alert(
        `you have blocked the ${moment(date).format("MMM Do YYYY")}`
      );
    },

    updateAvailability(month, year) {
      doctorService
        .getAvailability(this.$store.state.user.id, month, year)
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
    createAvailability() {
      this.newAvailability.specificOpenHours[
        this.availabilityDate
      ] = this.availabilityOpenTime;
      this.newAvailability.specificCloseHours[
        this.availabilityDate
      ] = this.availabilityCloseTime;
      if (
        this.$store.state.doctorAppointments.some(
          (appointment) => appointment.appointmentDate == this.availabilityDate
        )
      ) {
        window.alert("There is already an appointment on that day");
      } else if (this.availabilityOpenTime > this.availabilityCloseTime) {
        window.alert("Close time must be laster than open time");
      } else if (this.availabilityDate == "") {
        window.alert("Please select a date");
      } else {
        //window.alert("Availabilty available (lol)")
        this.showSubmitAvailability = true;
      }
    },
    submitAvailability() {
      this.newAvailability.specificOpenHours[
        this.availabilityDate
      ] = this.availabilityOpenTime;
      this.newAvailability.specificCloseHours[
        this.availabilityDate
      ] = this.availabilityCloseTime;
      doctorService
        .addAvailability(this.$store.state.user.id, this.newAvailability)
        .then((response) => {
          if (response.status == 201) {
            window.alert("availability created");
            this.updateAvailability(
              this.availabilityMonth,
              this.availabilityYear
            );
            this.refreshAvailability();
            this.showSubmitAvailability = false;
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
    refreshAvailability() {
      this.componentKey += 1;
    },
  },
  watch: {
    availabilityMonth: function (newMonth) {
      this.updateAvailability(newMonth, this.availabilityYear);
    },
    availabilityDate: function () {
      this.showSubmitAvailability = false;
    },
  },

  computed: {
    availabilityMonth() {
      return this.availabilityDate.substr(5, 2);
    },
    availabilityYear() {
      return this.availabilityDate.substr(0, 4);
    },
    currentDoctorId() {
      return this.$store.state.user.id;
    },
  },
};
</script>

<style>
.is-visible {
  display: show;
}
.availability-header {
  color: var(--main-color-dark1);
}

.doctor-availability-container {
  display: flex;
  flex-direction: column;
  background-color: var(--main-color-dark3);
}
.availability-date {
  /* background-color: var(--main-color-dark2); */
  display: flex;
  margin: 3px;
}

.availability-date-container {
  display: flex;
  align-items: center;
  justify-content: center;
}

.date-time-container {
  width: 500px;
  max-height: 100px;
  justify-content: space-evenly;
}

.availability-individual-date p {
  margin: 0px;
  background-color: var(--main-color-dark2);
}
.availability-individual-date p:hover {
  margin: 0px;
  background-color: var(--main-color-dark3);
  color: var(--main-color-dark1);
}

.availability-individual-date {
  margin: 0px;
  border-radius: 3px;
}

.standard-display-slot {
  padding: 2px;
}
.availability-times {
  display: flex;
  flex-direction: column;
  flex-wrap: wrap;
  border-radius: 3px;
  padding-right: 100px;
}

.availability-individual-time {
  padding: 3px;
  margin-bottom: 2px;
  margin-left: 3px;
}

.hidden {
  display: none;
}
.availability-row-details {
  background: whitesmoke;
}

.submit-availability-div p {
  color: black;
}
</style>