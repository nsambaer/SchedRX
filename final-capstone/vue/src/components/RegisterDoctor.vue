<template>
  <div id="register" class="text-center">
    <form class="form-register" @submit.prevent="register">
      <h1 class="h3 mb-3 font-weight-normal">Register Doctor</h1>
      <div
        class="alert alert-danger"
        role="alert"
        v-if="registrationErrors"
      >{{ registrationErrorMsg }}</div>
      <label for="username" class="sr-only">Username:</label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <label for="password" class="sr-only">Password:</label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <label for="confirmPassword" class="sr-only">Confirm Password:</label>
      <input
        type="password"
        id="confirmPassword"
        class="form-control"
        placeholder="Confirm Password"
        v-model="user.confirmPassword"
        required
      />
      <div>
        <label for="firstName" class="sr-only">First Name:</label>
        <input
          type="text"
          id="firstName"
          class="form-control"
          placeholder="First Name"
          v-model="doctor.firstName"
          required
        />
        <label for="lastName" class="sr-only">Last Name:</label>
        <input
          type="text"
          id="lastName"
          class="form-control"
          placeholder="Last Name"
          v-model="doctor.lastName"
          required
        />
      </div>
      <router-link :to="{ name: 'home' }">Have an account?</router-link>
      <button class="btn btn-lg btn-primary btn-block" type="submit">Register Doctor</button>
    </form>
  </div>
</template>

<script>
import authService from "@/services/AuthService";
import adminService from "@/services/AdminService";

export default {
  name: "register-doctor",
  data() {
    return {
      user: {
        username: "",
        password: "",
        confirmPassword: "",
        role: "doctor",
      },
      doctor: {
        doctorId: "",
        firstName: "",
        lastName: "",
        officeId: ""
      },
      registrationErrors: false,
      registrationErrorMsg: "There were problems registering this user.",
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = "Password & Confirm Password do not match.";
      } else {
        authService
          .register(this.user)
          .then((response) => {
            this.doctor.doctorId = response.data;
            this.doctor.officeId = this.$store.state.currentOffice.officeId;
            adminService
                .registerDoctor(this.doctor)
                .then(response => {
                    if (response.status === 201) {
                        this.$router.push({
                            name: 'redirect'
                        });
                    }
                })
                .catch((doctorError) => {
                    const doctorResponse = doctorError.response;
                    if (doctorResponse.status === 400) {
                        this.registrationErrorMsg = "Bad Request: Doctor Error";
                    }
                })
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = "Bad Request: Validation Errors";
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = "There were problems registering this user.";
    },
  },
};
</script>

<style></style>