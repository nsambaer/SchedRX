<template>
  <div class="office-details-container">
    <ul id="office-list">
      <div class="standard-display-slot" v-for="office in offices" v-bind:key="office.officeId">
        <div class="office-details">
          <h2 class="office-name">{{ office.officeName }}</h2>
          <p class="office-address">{{ office.address }} {{ office.city }}, {{ office.state }}</p>
          <p class="office-phone">{{ office.phoneNumber }}</p>
          <p class="office-cost">Cost/Hour: ${{ office.cost }}</p>
          <table class="office-hours">
            <td class="office-hours-days">
              <tr>Monday</tr>
              <tr>Tuesday</tr>
              <tr>Wednesday</tr>
              <tr>Thursday</tr>
              <tr>Friday</tr>
              <tr>Saturday</tr>
              <tr>Sunday</tr>
            </td>
            <td>
              <tr>{{ hours(office, 'Monday') }}</tr>
              <tr>{{ hours(office, 'Tuesday') }}</tr>
              <tr>{{ hours(office, 'Wednesday') }}</tr>
              <tr>{{ hours(office, 'Thursday') }}</tr>
              <tr>{{ hours(office, 'Friday') }}</tr>
              <tr>{{ hours(office, 'Saturday') }}</tr>
              <tr>{{ hours(office, 'Sunday') }}</tr>
            </td>
          </table>
          <ul class="doctor-list">
            <li v-for="doctor in office.doctorList" v-bind:key="doctor.doctorId">
              {{ doctor.firstName }} {{ doctor.lastName }}
            </li>
          </ul>
        </div>
      </div>
    </ul>

    <!--
      <table>
        <thead>
          <th>Name</th>
          <th>Address</th>
          <th>Phone</th>
          <th>Cost</th>
          <th>Hours</th>
          <th>Doctors</th>
        </thead>
        <tbody>
          <tr>
            <td>{{ office.officeName }}</td>
            <td>
              {{ office.address }}
              {{ office.city }}
              {{ office.state }}
            </td>
            <td>{{ office.phoneNumber }}</td>
            <td>${{ office.cost }}</td>
            <td>
              <tr>{{ hours(office, 'Monday') }}</tr>
              <tr>{{ hours(office, 'Tuesday') }}</tr>
              <tr>{{ hours(office, 'Wednesday') }}</tr>
              <tr>{{ hours(office, 'Thursday') }}</tr>
              <tr>{{ hours(office, 'Friday') }}</tr>
              <tr>{{ hours(office, 'Saturday') }}</tr>
              <tr>{{ hours(office, 'Sunday') }}</tr>
            </td>
            <td>
              <tr
                v-for="doctor in office.doctorList"
                v-bind:key="doctor.doctorId"
              >{{ doctor.firstName }} {{ doctor.lastName }}</tr>
            </td>
          </tr>
        </tbody>
      </table>
    -->
  </div>
</template>

<script>
import medService from "@/services/MedicalService";

export default {
  name: "office-details",

  data() {
    return {
      offices: [],
      reviews: [],
    };
  },

  methods: {
    hours(office, day) {
      if (office.openHours[day] === null) {
        return "Closed";
      }

      let open = parseInt(office.openHours[day].substr(0, 2));
      let close = parseInt(office.closeHours[day].substr(0, 2));
      let openFiltered = "";
      if (open < 13) {
        openFiltered = open + " AM";
      } else {
        openFiltered = (open % 12) + " PM";
      }

      let closeFiltered = "";

      if (close < 13) {
        closeFiltered = close + " AM";
      } else {
        closeFiltered = (close % 12) + " PM";
      }

      return `${openFiltered} - ${closeFiltered}`;
    },
  },

  created() {
    medService
      .listAllOffices()
      .then((response) => {
        this.offices = response.data;
      })
      .catch((error) => {
        const response = error.response;
        this.errors = true;
        if (response.status === 400) {
          this.errorMsg = "Bad Request: Validation Errors";
        }
      });
  },

  computed: {},
};
</script>


  
<style>

.office-details {
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-template-areas: 
  "name name"
  "address hours"
  "phone hours"
  "cost hours"
  "doctor hours"
  ;
  max-width: 450px;
}

.office-name {
  display: flex;
  justify-content: center;
  grid-area: name;
}

.office-address {
  margin: 5px 0px 5px 0px;
  grid-area: address;
}

.office-phone {
  margin: 5px 0px 5px 0px;
  grid-area: phone;
}

.office-cost {
  margin: 5px 0px 5px 0px;
  grid-area: cost;
}
.office-hours {
  grid-area: hours;
}

.doctor-list {
  margin: 5px 0px 5px 0px;
  padding: 0px;
  grid-area: doctor;
}

.office-hours-days {
  display: flex;
  flex-direction: column;
  justify-content: center;
}

#office-list {
  padding: 0px;
  display: flex;
  flex-direction: column;
  flex-wrap: wrap;
}

ul {
  list-style: none;
}

td {
  min-width: 100px;
  text-align: center;
}

.office-details{
  background-color: white;
  width:75%;
}

</style>