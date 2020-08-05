import axios from 'axios'

const http = axios.create({
    baseURL: "http://localhost:8080"
  });


  export default {

    // addPatient() {
    //   return http.post(`/login/${}`)
    // },

    // updatePatient(patient) {
    //   return http.put(`/login/${}`);
    // },
    listAllOffices() {
        return http.get('/offices');
    }







  }