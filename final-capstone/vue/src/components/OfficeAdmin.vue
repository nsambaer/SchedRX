<template>
  <div class="office-admin-container">
      <div class="office-info" v-show="!showEditForm">
        
        <table>
          <thead>
            <th class="oi-title"> <h2>Office Information</h2> </th>
          </thead>
          <tbody>
            <tr>
              <td class="attribute">Office Name: </td>
              <td class="value">{{office.officeName}}</td>
            </tr>
            <tr>
              <td class="attribute">Address: </td>
              <td class="value">{{office.address}}, {{office.city}}, {{office.state}}, {{office.zipCode}}</td>
            </tr>
            <tr>
              <td class="attribute">Phone: </td>
              <td class="value">{{office.phoneNumber}}</td>
            </tr>
            <tr>
              <td class="attribute">Cost per hour: </td>
              <td class="value">{{office.cost}}</td>
            </tr>
          </tbody>
        </table>
        <button v-on:click="showEditForm = !showEditForm; populateDefaults()">Edit Office Information</button>
      </div>
        
      <div class="edit-office" v-show="showEditForm">
          <h2 class="edit-office-title">Edit Office Information</h2>
          <form class="edit-office-form" v-on:submit.prevent="updateOffice()" v-on:reset.prevent="cancelChanges()">
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
              <button type="reset">Cancel Changes</button>
                
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
        },
        cancelChanges() {
          this.showEditForm = false;
          this.populateDefaults();
        }
    }
    
}
</script>

<style>
    :root{
      --main-color-turqoise: #086972;
      --main-color-blue-green: #01a9b4;
      --main-color-light-blue: #87dfd6;
      --accent-color-yellow: #fbfd8a;
    }

    .office-info {
      display: grid;
      
    }

    .attribute {
      background-color: var(--main-color-light-blue);
      border-style: solid;
      border-radius: 5px;
      border-width: 1px;
    }

    label {
      background-color:#01a9b4;
    }

    .office-admin-container {
      background-color:var(--main-color-turqoise);
      border-style: solid;
      border-width: 1px;
      border-radius: 5px;
      width: 100%;
    }
    .edit-office-form {
        display: grid;
        grid-area: "office-form";
        grid-template-columns: 150px 200px;

    }
</style>