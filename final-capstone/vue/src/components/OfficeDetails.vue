<template>
  <div>
    <ul>
      <li>
      {{office.officeId }}
      </li>
      <li>
      {{ office.officeName }}
      </li>
      <li>
      {{ office.address }}
      {{ office.city }}
      {{ office.state }}
      </li>
      <li>
      {{ office.phoneNumber }}
      </li>
      <li>
      {{ office.cost }}
      </li>
      <li>
      {{office.closeHours }}
      </li>
      <li>
      {{office.openHours }}
      </li>
    </ul>
  </div>
</template>

<script>
export default {
props: {
office: Object
}
}
</script>


  
<style>

</style>