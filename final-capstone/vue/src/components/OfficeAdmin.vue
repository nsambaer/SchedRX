<template>
  <div>
      <h2>Office name: {{office.officeName}}</h2>
      <h4>Address: {{office.address}}, {{office.city}}, {{office.state}}, {{office.zipCode}}</h4>
      <h4>Phone: {{office.phoneNumber}}</h4>
      <h4>Cost per hour: {{office.cost}}</h4>
  </div>
</template>

<script>


export default {
    computed: {
        office() {
            return this.$store.state.currentOffice;
        }
    }
    
}
</script>

<style>

</style>