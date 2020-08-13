<template>
  <div>
<<<<<<< HEAD
    <div class="office-listing" v-for="office in offices" v-bind:key="office.officeId"  >

      
        <office-details  v-bind:office="office"  />
      
=======
    <div class="office-listing" v-for="office in offices" v-bind:key="office.officeId">
      <office-details v-bind:office="office" />
>>>>>>> e436b913d50292d8fd6e8dfc863f8c9d39a4e990
    </div>
  </div>
</template>

<script>
import medService from "../services/MedicalService.js";
import OfficeDetails from "@/components/OfficeDetails";

export default {
  name: "office-list",

  components: {
    OfficeDetails,
  },

  data() {
    return {
      offices: [],
    };
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
};
</script>

<style>
.office-listing{
  justify-content: center;
}
</style>