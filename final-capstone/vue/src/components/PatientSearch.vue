<template>
  <div>
      <form v-on:submit.prevent="getPatient()">
          <label for="username-search">Username: </label>
          <input 
            id="username-search" 
            type="text"
            v-model="username"
          >
          <button type="submit">Find Patient</button>
      </form>
      <div v-show="showResults">
          <table>
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

</style>