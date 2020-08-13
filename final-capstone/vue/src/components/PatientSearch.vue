<template>
  <div class="search-container">
      <h2 class="search-title">Search Patient Usernames</h2>
      <form v-on:submit.prevent="getPatient()">
          <label class="username-search-label" for="username-search">Username: </label>
          <input 
            id="username-search" 
            type="text"
            v-model="username"
            required
            placeholder="Enter username to search"
          >
          <button type="submit">Find Patient</button>
      </form>
      <h3>Search Results: </h3>
      <div class="results-container" >
          <table class="results-table" v-show="!searchError">
              <thead>
                  <th>Username: </th>
                  <th>Patient Name: </th>
                  <th>Date of Birth: </th>
                  <th>Phone: </th>
              </thead>
              <tbody>
                  <tr>
                      <td>{{userNameFound}}</td>
                      <td>{{patient.lastName}}, {{patient.firstName}}</td>
                      <td>{{patient.birthdate}}</td>
                      <td>{{patient.phone}}</td>
                      
                  </tr>
              </tbody>
          </table>
          <div class="search-error" v-show="searchError">
            <h3>{{searchErrorMsg}}</h3>
          </div>
          <button v-on:click.prevent="clearSearch()">Clear Search</button>
      </div>
      <div class="search-error" v-show="searchError">
          <h3>{{searchErrorMsg}}</h3>
          <button v-on:click.prevent="clearSearch()">Clear Search</button>
      </div>
  </div>
</template>

<script>
import adminService from '../services/AdminService.js';
export default {
    data() {
        return {
            username: '',
            errorMsg: '',
            patient: {
                patientId: '',
                firstName: '',
                lastName: '',
                primaryDoctor: {
                    doctorId: '',
                    officeId: ''
                },
                address: '',
                state: '',
                city: '',
                zipCode: '',
                phone: '',
                birthdate: ''
            },
            showResults: false,
            userNameFound: '',
            searchErrorMsg: '',
            searchError: false,
        }
    },

    methods: {
        getPatient() {
            adminService
                .getPatientByUsername(this.username)
                .then(response => {
                    this.patient = response.data;
                    this.userNameFound = this.username;
                    this.showResults = true;
                })
                .catch(error => {
                    const response = error.response;
                    this.searchError = true;
                    if (response.status === 500) {
                        this.searchErrorMsg = "User not found."
                    }
                });
                
        },

        clearSearch() {
            this.patient = {};
            this.showResults = false;
            this.searchError = false;
            this.userNameFound = '';
            this.username = '';
        }
    }
}
</script>

<style>

.username-search-label {
    font-weight: bold;
}
/* :root{
    --main-color-dark1: #004a7c;
    --main-color-dark2: #005691;
    --main-color-dark3: #e8f1f5;
    --main-color-dark4: #fafafa;
}

.search-title {
    border-style: solid;
    border-width: 1px;
    border-radius: 5px;
    background-color: var(--main-color-dark3);
}

.results-table {
    width: 100%;
    border-style: solid;
    border-width: 1px;
    background-color: var(--main-color-dark3);
}
.search-container {
    background-color: var(--main-color-dark1);
    text-align: center;
    border-style: solid;
    border-width: 1px;
    border-radius: 5px;
} */
</style>