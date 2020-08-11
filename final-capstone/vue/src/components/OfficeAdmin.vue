<template>
  <div class="office-admin-container">
      <div class="office-info">
        <h2>Office name: {{office.officeName}}</h2>
        <h4>Address: {{office.address}}, {{office.city}}, {{office.state}}, {{office.zipCode}}</h4>
        <h4>Phone: {{office.phoneNumber}}</h4>
        <h4>Cost per hour: {{office.cost}}</h4>
      </div>
      <button v-on:click="showEditForm = !showEditForm; populateDefaults()">Edit Office Information</button>  
      <div class="edit-office" v-show="showEditForm">
          <form class="edit-office-form" v-on:submit.prevent="updateOffice()">
              <label for="office-name">Office name: </label>
              <input
                id="office-name" 
                v-model="updatedOffice.officeName" 
                type="text"
              />
              <label for="street-address">Street Address: </label>
              <input 
                id="street-address"
                type="text"
                v-model="updatedOffice.address"
              />
              <label for="city">City: </label>
              <input
                id="city" 
                type="text"
                v-model="updatedOffice.city"
              />
              <label for="state">State: </label>
              <input 
                id="state"
                type="text"
                v-model="updatedOffice.state"
              />
              <label for="zip-code">Zip Code: </label>
              <input
                id="zip-code" 
                type="text"
                v-model="updatedOffice.zipCode"
              />
              <label for="phone">Phone: </label>
              <input
                id="phone" 
                type="text"
                v-model="updatedOffice.phoneNumber"
              />
              <label for="cost">Cost: </label>
              <input
                id="cost" 
                type="text"
                v-model="updatedOffice.cost"
              />
              <button type="submit">Save Changes</button>
                
          </form>
      </div>

  </div>
</template>

<script>

import adminService from "../services/AdminService.js"

export default {
    name: "office-admin",

    data() {
        return {
            office: {},
            showEditForm: false,
            updatedOffice: {
                officeId: "",
                officeName: "",
                address: "",
                city: "",
                state: "",
                zipCode: "",
                phoneNumber: "",
                cost: ""
            }
        }
    },

    created() {
      this.office = this.$store.state.currentOffice;
    },

    methods: {
        populateDefaults() {
            this.updatedOffice.officeId = this.office.officeId;
            this.updatedOffice.officeName = this.office.officeName;
            this.updatedOffice.address = this.office.address;
            this.updatedOffice.city = this.office.city;
            this.updatedOffice.state = this.office.state;
            this.updatedOffice.zipCode = this.office.zipCode;
            this.updatedOffice.phoneNumber = this.office.phoneNumber;
            this.updatedOffice.cost = this.office.cost;
        },
        updateOffice() {
            adminService.updateOffice(this.updatedOffice)
            .then(response => {
                
                this.$store.commit("SET_CURRENT_OFFICE", response.data);
                this.office = response.data;    
                localStorage.setItem('office',JSON.stringify(this.updatedOffice));
                this.office = JSON.parse(localStorage.getItem('office'));
                this.showEditForm = false;
                
            })
        }
    }
    
}
</script>

<style>
    
    .edit-office-form {
        display: grid;
        grid-area: "office-form";
        grid-template-columns: 150px 200px;

    }
</style>