<template>

<div class="office-details-container" >

  <button  v-on:click="showOfficeDetails = !showOfficeDetails">{{office.officeName}} </button>
  <div class="office-details" v-show="showOfficeDetails">
    
      <table>
        <thead>
          <th>
            Name
          </th>
          <th>
            Address
          </th>
          <th>
            Phone
          </th>
          <th>
            Cost
          </th>
          <th> Open </th>
          <th>Close</th>
        </thead>
        <tbody>
          <tr>
            <td>
              {{ office.officeName }}
            </td>
            <td>
              {{ office.address }}
            {{ office.city }}
               {{ office.state }}
            </td>
            <td>{{ office.phoneNumber }}</td>
            <td> {{ office.cost }}</td>
            <td>
              <tr>
                {{office.openHours.Monday}} - {{office.closeHours.Monday}}
              </tr>
            </td>
            
          </tr>
          </tbody>
        


      </table>
    
    
  </div>
</div>
</template>

<script>
export default {
  name:"office-details",

  data(){
    return{
      showOfficeDetails: false
    }
  },
props: {
  office: Object
}
,
created(){
  this.office.showDetails=false;
}
}
</script>


  
<style>



</style>