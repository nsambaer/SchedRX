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
    },

    getDoctors() {
      return http.get('/doctors');
    },

    updatePrimaryDoctor(patient) {
      return http.put(`/patients/${patient.id}`, patient)
    },

    getAppointmentTypes() {
      return http.get('/appointment-types');
    },

    postAppointment(appointment) {
      return http.post(`/patients/${appointment.patientId}/appointments`, appointment);
    },

    createReview(review) {
      return http.post(`patients/${review.patient.patientId}/reviews`, review);
    }

  }