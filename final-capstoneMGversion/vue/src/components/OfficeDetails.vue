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
          <th> Hours </th>
         
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
            <td> ${{ office.cost }}</td>
            <td>
              <tr>
                Monday: {{openHours.Monday}} - {{closedHours.Monday}}
              </tr>
              <tr>
                Tuesday: {{openHours.Tuesday}} - {{closedHours.Tuesday}}
              </tr>
              <tr>
                Wednesday: {{openHours.Wednesday}} - {{closedHours.Wednesday}}
              </tr>
              <tr>
                Thursday: {{openHours.Thursday}} - {{closedHours.Thursday}}
              </tr>
              <tr>
                Friday: {{openHours.Friday}} - {{closedHours.Friday}}
              </tr>
              <tr>
                Saturday: {{openHours.Saturday}} - {{closedHours.Saturday}}
              </tr>
              <tr>
                Sunday: {{openHours.Sunday}} - {{closedHours.Sunday}}
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
  this.office.showDetails=true;
},
computed:{
  openHours(){
      let newOpenHours = Object.fromEntries(Object.entries(this.office.openHours).map( ([key,val]) => 
    [key, val == null ? "Closed": val.substr(0,2)%12]));
    return newOpenHours;
  },
  closedHours(){
    let newCloseHours = Object.fromEntries(Object.entries(this.office.closeHours).map( ([key,val]) => 
    [key, val == null ? "Closed": val.substr(0,2)%12]));
    return newCloseHours;
    
  }
}
}
</script>


  
<style>

td {
  min-width: 100px;
  text-align: center;
}


</style>