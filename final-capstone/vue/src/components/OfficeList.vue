<template>
  <div>
    <div v-for="office in offices" v-bind:key="office.officeId"> {{office.officeName}}
      
       </div>
  </div>
</template>

<script>
import medService from '../services/MedicalService.js' 
export default {
  data() {
    return {
      offices: []
    }
  },
created() {
medService.listAllOffices().then(response => {
this.offices = response;
})
}

}
</script>

<style>

</style>