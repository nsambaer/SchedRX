<template>
  <div class="office-admin-container">
      <div class="office-info" v-show="!showEditForm">
        <h2 class="oi-title">Office Information</h2> 
        <table class="office-info-table">
          <tbody>
            <tr>
              <td class="attribute">Office Name: </td>
              <td class="value">{{office.officeName}}</td>
            </tr>
            <tr>
              <td class="attribute">Address: </td>
              <td class="value">{{office.address}}</td>
            </tr>
            <tr>
              <td class="attribute">City: </td>
              <td>{{office.city}}</td>
            </tr>
            <tr>
              <td class="attribute">State: </td>
              <td>{{office.state}}</td>
            </tr>
            <tr>
              <td class="attribute">Zip Code:  </td>
              <td>{{office.zipCode}}</td>
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
        <button class="standard-button" v-on:click="showEditForm = !showEditForm; populateDefaults()">Edit Office Information</button>
      </div>
        
      <div class="edit-office" v-show="showEditForm">
          <h2 class="edit-office-title">Office Information</h2>
          <form class="edit-office-form" v-on:submit.prevent="updateOffice()" v-on:reset.prevent="cancelChanges()">
              <div>
              <label class="office-info-label" for="office-name">Office name: </label>
              <input
                id="office-name" 
                v-model="updatedOffice.officeName" 
                type="text"
              />
              </div>
              <div>
              <label class="office-info-label" for="street-address">Street Address: </label>
              <input 
                id="street-address"
                type="text"
                v-model="updatedOffice.address"
              />
              </div>
              <div>
              <label class="office-info-label" for="city">City: </label>
              <input
                id="city" 
                type="text"
                v-model="updatedOffice.city"
              />
              </div>
              <div>
              <label class="office-info-label" for="state">State: </label>
              <input 
                id="state"
                type="text"
                v-model="updatedOffice.state"
              />
              </div>
              <div>
              <label class="office-info-label" for="zip-code">Zip Code: </label>
              <input
                id="zip-code" 
                type="text"
                v-model="updatedOffice.zipCode"
              />
              </div>
              <div>
              <label class="office-info-label" for="phone">Phone: </label>
              <input
                id="phone" 
                type="text"
                v-model="updatedOffice.phoneNumber"
              />
              </div>
              <div>
              <label class="office-info-label" for="cost">Cost: </label>
              <input
                id="cost" 
                type="text"
                v-model="updatedOffice.cost"
              />
              </div>
              <button class="standard-button" type="submit">Save Changes</button>
              <button class="standard-button" type="reset">Cancel Changes</button>
                
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
      --main-color-dark1: #004a7c;
      --main-color-dark2: #005691;
      --main-color-dark3: #e8f1f5;
      --main-color-dark4: #fafafa;
    }


@media screen and (max-width: 828px) {
  .office-info-label {
    display: block;
  }
  .office-info-table td {
    display: block;
  }
}

.office-info-table td {
  text-align: left;
  font-weight: bold;
}

.office-info-label {
  min-width: 120px;
  display: inline-block;
  font-weight: bold;
}
    /* 

    

    .oi-title {
      background-color: var(--main-color-dark3);
      
      border-style:solid;
      border-width: 1px;
      border-radius: 5px;
      margin-left:auto;
    }

    .attribute {
      background-color: var(--main-color-dark3);
      border-style: solid;
      border-radius: 5px;
      border-width: 1px;
      width: 50%;
    }

    .value {
      background-color: var(--main-color-dark3);
      border-style: solid;
      border-radius: 5px;
      border-width: 1px;
      width: 50%;
    }

    .office-info-label {
      background-color: var(--main-color-dark3);
      margin-left:auto;
      border-radius: 5px;
      border-style: solid;
      border-width: 1px;
    }

    .office-admin-container {
      background-color:var(--main-color-dark2);
      border-style: solid;
      border-width: 1px;
      border-radius: 5px;
      width: 100%;
    }
    .edit-office-form {
        
        grid-area: "office-form";
        grid-template-columns: 1fr, 1fr;
        text-align: center;

    }

    .edit-office-title {
      
      
      border-style:solid;
      border-width: 1px;
      border-radius: 5px;
      text-align: center;
      background-color: var(--main-color-dark3);
    }

    .office-info {
      text-align: center;
    }

    .office-info-table {
      margin-left: auto;
      margin-right: auto;
    } */
</style>