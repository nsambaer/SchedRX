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
    },

    addAvailability(doctorId, availability){

      return http.post(`/doctors/${doctorId}/availability/specific`, availability);
    },

    getReviews(doctorId){
      return http.get(`/offices/${doctorId}/reviews`);
    },

    updateAppointment(appointmentId, appointment){
      return http.put(`/appointments/${appointmentId}`, appointment);
    },

    sendNotification(notification){

      return http.post(`/notifications`, notification)
    },

    deleteAppointment(appointmentId){
      return http.delete(`/appointments/${appointmentId}`);
    }



  }