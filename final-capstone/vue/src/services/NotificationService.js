import axios from 'axios'

const http = axios.create({
    baseURL: "http://localhost:8080"
  });


  export default {

    getRecentNotifications(userId) {
        return http.get(`/users/${userId}/notifications`);
    },

    markNotificationRead(notificationId) {
        return http.put(`/notifications/${notificationId}`)
    },

    markAllRead(userId) {
        return http.put(`/users/${userId}/notifications`);
    },

    createNotification(notification) {
        return http.post('/notifications', notification);
    }

  }
