import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(JSON.stringify(localStorage.getItem('user')));
const currentRole = localStorage.getItem('role');
const currentPatient = JSON.parse(JSON.stringify(localStorage.getItem('patient')));


if(currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {},
    userRole: currentRole || '',
    patient: currentPatient || {},
    patientAppointments: [],
    currentOffice: {},
    doctorAppointments:[],
    availability:[]
  

  },
  
  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user',JSON.stringify(user));
    },

    SET_USER_ROLE(state, role) {
      state.userRole = role;
      localStorage.setItem('role', role);
    },

    SET_PATIENT(state, patient) {
      state.patient = patient;
      localStorage.setItem('patient',JSON.stringify(patient));
    },

    SET_PATIENT_APPOINTMENTS(state,patientAppointments) {
      state.patientAppointments = patientAppointments;
    },

    NEW_PRIMARY_DOCTOR(state, doctorId) {
      state.patient.primaryDoctor.doctorId = doctorId;
    },
    SET_CURRENT_OFFICE(state, office) {
      state.currentOffice = office;
      localStorage.setItem('office', office);
      
    },

    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      localStorage.removeItem('role');
      localStorage.removeItem('patient');
      state.token = '';
      state.user = {};
      state.userRole = '';
      axios.defaults.headers.common = {};
      state.patient = {};
      state.patientAppointment = '';
      state.currentOffice = {};
    }
  }
  
})
