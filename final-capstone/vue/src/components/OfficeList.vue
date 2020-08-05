<template>
  <div>
    <div v-for="office in offices" v-bind:key="office.officeId" v-on:click="office.showDetails = !office.showDetails" >
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

=======
>>>>>>> 5773d1e6bd0b67a824aa97b529f11e395871ebc3
      this.offices.forEach( (office) => {
        office.showDetails = true;
      });
      });
<<<<<<< HEAD
    });
=======
>>>>>>> 5773d1e6bd0b67a824aa97b529f11e395871ebc3
  },
};
</script>

<style>
</style>