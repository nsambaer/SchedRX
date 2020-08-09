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
    }



  }