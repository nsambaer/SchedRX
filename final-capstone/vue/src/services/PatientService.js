import axios from 'axios'

const http = axios.create({
    baseURL: "http://localhost:8080"
  });


  export default {

    getAppointments(patientId){
        return http.get(`/doctors/${patientId}/appointments`);
    },

    getAvailability(patientId, month, year){
        return http.get(`/patient/${patientId}/availability`, {params: {month: month, year: year}} );
    }



  }