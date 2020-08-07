import axios from 'axios'

const http = axios.create({
    baseURL: "http://localhost:8080"
  });


  export default {

    getAvailability(doctorId, month, year){
        return http.get(`/doctors/${doctorId}/availability`, {params: {month: month, year: year}} );
    },

    getPatient(patientId) {
      return http.get(`/patients/${patientId}`);
    },

    getAppointments(patientId) {
      return http.get(`/patients/${patientId}/appointments`);
    }


  }