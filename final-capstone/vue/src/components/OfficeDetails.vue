<template>
  <div class="office-details-container">
    <div class="office-details" v-for="office in offices" v-bind:key="office.officeId">
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
    </div>
  </div>
</template>

<script>
import medService from '@/services/MedicalService';

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
        return 'Closed';
      }

      let open = parseInt(office.openHours[day].substr(0, 2));
      let close = parseInt(office.closeHours[day].substr(0,2));
      let openFiltered = '';
      if (open < 13) {
        openFiltered = open + ' AM';
      } else {
        openFiltered = (open % 12) + ' PM';
      }

      let closeFiltered = '';
      
      if (close < 13) {
        closeFiltered = close + ' AM';
      } else {
        closeFiltered = (close % 12) + ' PM';
      }
            
      return `${openFiltered} - ${closeFiltered}`
    }


  },

  created() {
    medService.listAllOffices().then((response) => {
      this.offices = response.data;
    }).catch((error) => {
        const response = error.response;
        this.errors = true;
        if (response.status === 400) {
          this.errorMsg = "Bad Request: Validation Errors";
        }
      });
  },

  computed: {
    
  },
};
</script>


  
<style>
td {
  min-width: 100px;
  text-align: center;
}
</style>