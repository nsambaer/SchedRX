import axios from 'axios';

const http = axios.create({
  baseURL: "http://localhost:8080/"
});

export default {

  login(user) {
    return http.post('/login', user)
  },

  register(user, patient) {
    http.post('/register', user).then( (response) => {
      patient.patientId = response.data;
      return http.post('/createPatient', patient);
    })
  }

}
