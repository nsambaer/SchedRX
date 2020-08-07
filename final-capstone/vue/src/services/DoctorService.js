import axios from 'axios'

const http = axios.create({
    baseURL: "http://localhost:8080"
  });


  export default {

    getAppointments(doctorId){
        return http.get(`/doctors/${doctorId}/appointments`);
    },

    getAvailability(doctorId, month, year){
        return http.get(`/doctors/${doctorId}/availability`, {params: {month: month, year: year}} );
    }



  }