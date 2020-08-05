<template>
  <div>
    <div v-for="office in offices" v-bind:key="office.officeId" v-toggle="office.showDetails" >
      {{office.officeName}}
      <div class="details" v-show="office.showDetails">
        <office-details v-bind:office="office" />
      </div>
    </div>
  </div>
</template>

<script>
import medService from "../services/MedicalService.js";
import OfficeDetails from '@/components/OfficeDetails';

export default {
  name: 'office-list',

  components: {
    OfficeDetails
  },

  data() {
    return {
      offices: [],
    };
  },
  created() {
    medService.listAllOffices().then((response) => {
      this.offices = response.data;
<<<<<<< HEAD
      });
=======
      this.offices.forEach( (office) => {
        office.showDetails = false;
      });
    });
>>>>>>> badcc97a3808d13f1e032eb7f2c6ea070c69319f
  },
};
</script>

<style>
</style>