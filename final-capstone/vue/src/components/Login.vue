<template>
  <div id="login" class="text-center">
    <form class="form-signin" @submit.prevent="login">
      <h2 class="standard-component-header">Please Sign In</h2>
      <div
        class="alert alert-danger"
        role="alert"
        v-if="invalidCredentials"
      >Invalid username and password!</div>
      <div
        class="alert alert-danger"
        role="alert"
        v-if="roleError"
      >There is a problem with your role- {{ role }}</div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >Thank you for registering, please sign in.</div>
      <label for="username" class="sr-only">Username: </label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <label for="password" class="sr-only">Password: </label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
     
      <button class = "standard-button" type="submit">Sign in</button>
      <button class="standard-button" v-on:click.prevent="showForgotPass = !showForgotPass">Forgot Password?</button><br />
       <router-link :to="{ name: 'register' }">Are you a patient that needs an account? Please click here!</router-link>
    </form>
    
    <forgot-password v-if="showForgotPass"></forgot-password>
  </div>
</template>

<script>
import authService from "../services/AuthService";
import patientService from '@/services/PatientService';
import adminService from '../services/AdminService';
import forgotPassword from '@/components/ForgotPassword';

export default {
  name: "login",
  components: {
    forgotPassword
  },
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
      invalidCredentials: false,
      roleError: false,
      role: "Role not found",
      showForgotPass: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then((response) => {
          this.$store.commit("SET_AUTH_TOKEN", response.data.token);
          this.$store.commit("SET_USER", response.data.user);

          this.role = response.data.user.authorities[0].name;
          this.$store.commit("SET_USER_ROLE", this.role);

          switch (this.role)  {
            case "ROLE_PATIENT":
              this.setPatient();
              break;
            case "ROLE_DOCTOR":
              this.$router.push({ name: "doctor" });
              break;
            case "ROLE_ADMIN":
              this.setOffice();
              break;
            default:
            this.roleError = true;
            break;
          }
        }).catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },

      setPatient() {
      patientService
        .getPatient(this.$store.state.user.id)
        .then((response) => {
          this.$store.commit("SET_PATIENT", response.data);
          this.$router.push({ name: "patient" });
        })
        .catch((error) => {
          const response = error.response;
          this.errors = true;
          if (response.status === 400) {
            this.errorMsg = "Bad Request: Validation Errors";
          }
        });
    },

    setOffice() {
      adminService
        .getOffice(this.$store.state.user.id)
        .then((response) => {
          this.$store.commit("SET_CURRENT_OFFICE", response.data);
          this.$router.push({ name: "admin" });
        })
        .catch((error) => {
          const response = error.response;
          this.errors = true;
          if (response.status === 400) {
            this.errorMsg = "Bad Request: Validation Errors"
          }
        })
    }

  },
};
</script>
<style>

</style>
