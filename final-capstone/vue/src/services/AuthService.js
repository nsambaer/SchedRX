import axios from 'axios';

const http = axios.create({
  baseURL: "http://localhost:8080/"
});

export default {

  login(user) {
    return http.post('/login', user)
  },

  register(user) {
    return http.post('/register', user)
  },

  resetPassword(user) {
    return http.put('/reset-password', user)
  },

  getUserIdByUsername(username) {
    return http.get(`/user-by-id/${username}`);
  },

  getAllOffices() {
    return http.get('/offices');
  },

  getAdminByOffice(officeId) {
    return http.get(`/offices/${officeId}/admin`);
  }

}
