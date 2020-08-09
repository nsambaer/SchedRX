<template>
  <div>
      <h2>Office name: {{office.officeName}}</h2>
      <h4>Address: {{office.address}}, {{office.city}}, {{office.state}}, {{office.zipCode}}</h4>
      <h4>Phone: {{office.phone}}</h4>
      <h4>Cost per hour: {{office.costPerHour}}</h4>
  </div>
</template>

<script>
import adminService from '../services/AdminService.js'

export default {
    data() {
        return {
            office: {
                officeId: '',
                officeName: '',
                address: '',
                city: '',
                state: '',
                zipCode: '',
                phone: '',
                costPerHour: ''

            }
        } 
    },
    created() {
        adminService.getOffice(this.$store.state.user.id).then( response => {
            this.office = response.data;
        });
    }
}
</script>

<style>

</style>