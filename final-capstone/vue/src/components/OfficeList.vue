<template>
  <div>
    <div v-for="office in offices" v-bind:key="office.officeId" >
      <button v-on:click="toggleDetails(office.officeId)">{{office.officeName}} </button>

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

      this.offices.forEach( (office) => {
        office.showDetails = true;
      });
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