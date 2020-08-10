<template>
  <div>
    <div v-bind:class="office-listing" v-for="office in offices" v-bind:key="office.officeId"  >

      
        <office-details v-bind:office="office"  />
      
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

     
      });
    
  },
  methods:{
    toggleDetails(officeId){
      let clickedOffice = this.offices.find(office => {
        return officeId == office.officeId;
      });
      clickedOffice.showDetails = !clickedOffice.showDetails;
    }
  }
};
</script>

<style>

</style>