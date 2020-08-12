import axios from 'axios'

const http = axios.create({
    baseURL: "http://localhost:8080"
  });


  export default {

    getOffice(adminId){
        return http.get(`/offices/${adminId}/current-office`);
    },

    registerDoctor(doctor) {
        return http.post('/doctors', doctor);
    },

    updateOffice(updatedOffice) {
        return http.put(`/offices/${updatedOffice.officeId}`, updatedOffice);
    },

    setDoctorHours(doctorId, availability) {
        return http.post(`/doctors/${doctorId}/availability/regular`, availability)
    },

    getPatientByUsername(username) {
        return http.get(`/patients/find/${username}`);
    },

    

  }