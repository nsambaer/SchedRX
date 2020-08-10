import axios from 'axios'

const http = axios.create({
    baseURL: "http://localhost:8080"
  });


  export default {

    getRecentNotifications(userId) {
        return http.get(`/users/${userId}/notifications/recent`);
    },

    markNotificationRead(notificationId) {
        return http.put(`/notifications/${notificationId}`)
    },

    markAllRead() {
        return '';
    }

  }
