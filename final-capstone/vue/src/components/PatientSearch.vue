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
          <button class="standard-button" type="submit">Find Patient</button>
      </form>
      <h3 v-show="showResults">Search Results: </h3>
      <div class="results-container" >
          <table class="results-table" v-show="!searchError && showResults">
              <thead class="results-header">
                  <th class="results-head">Username: </th>
                  <th class="results-head">Patient Name: </th>
                  <th class="results-head">Date of Birth: </th>
                  <th class="results-head">Phone: </th>
              </thead>
              <tbody class="results-body">
                  <tr class="results-row">
                      <td class="results-value">{{userNameFound}}</td>
                      <td class="results-value">{{patient.concat}}</td>
                      <td class="results-value">{{patient.birthdate}}</td>
                      <td class="results-value">{{patient.phone}}</td>
                      
                  </tr>
              </tbody>
          </table>
          <div class="search-error" v-show="searchError">
            <h3>{{searchErrorMsg}}</h3>
          </div>
          <br>
          <button v-show="searchError || showResults" class="standard-button" v-on:click.prevent="clearSearch()">Clear Search</button>
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
                    this.patient.concat = this.patient.lastName + ', ' + this.patient.firstName;

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

@media screen and (max-width: 828px) {
    .username-search-label {
        display: block;
    }
    .results-head, .results-header, .results-body, .results-row, .results-value {
        display: block;
    }
    .results-value:nth-of-type(1):before { 
        content: "Username: "; 
        font-weight: bold;
        text-align: left;
    }
    .results-value:nth-of-type(2):before { 
        content: "Patient Name: "; 
        font-weight: bold;
    }
    .results-value:nth-of-type(3):before { 
        content: "Date of Birth: "; 
        font-weight: bold;
    }
    .results-value:nth-of-type(4):before { 
        content: "Phone: "; 
        font-weight: bold;
    }
    .results-head {
        position: absolute;
		top: -9999px;
		left: -9999px;
    }
    .results-value {
        top: 6px;
		left: 6px;
		width: 45%; 
		padding-right: 10px; 
		white-space: nowrap;
    }
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