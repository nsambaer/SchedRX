<template>
  <div class="search-container">
      <h2 class="search-title">Search Patient Usernames</h2>
      <form v-on:submit.prevent="getPatient()">
          <label for="username-search">Username: </label>
          <input 
            id="username-search" 
            type="text"
            v-model="username"
          >
          <button type="submit">Find Patient</button>
      </form>
      <div class="results-container" v-show="showResults">
          <table class="results-table">
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
            userNameFound: ''
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
                });
                
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
}
</style>