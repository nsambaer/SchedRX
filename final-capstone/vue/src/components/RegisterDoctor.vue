<template>
  <div id="register" class="text-center">
    <h2 class="h3 mb-3 font-weight-normal">Register Doctor</h2>
    <form class="form-register" @submit.prevent="register">
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
        officeId: "",
      },
      availability: {
      },
      registrationSuccess: false,
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
              .then((adminResponse) => {
                adminResponse;
                this.availability.doctorId = this.doctor.doctorId;
                this.availability.regularOpenHours = this.$store.state.currentOffice.openHours;
                this.availability.regularCloseHours = this.$store.state.currentOffice.closeHours;

                adminService
                  .setDoctorHours(this.doctor.doctorId, this.availability)
                  .then((adminResponse) => {
                    if (adminResponse.status === 201) {
                      this.registrationSuccess = true;
                      this.clearForm();
                    }
                  })
                  .catch((availError) => {
                    const availResponse = availError.response;
                    if (availResponse.status === 400) {
                      this.registrationErrorMsg = "Bad Request: Doctor Error";
                    }
                  });
              })
              .catch((doctorError) => {
                console.log(doctorError.response + '       ' + doctorError.response.status);
                const doctorResponse = doctorError.response;
                if (doctorResponse.status === 400) {
                  this.registrationErrorMsg = "Bad Request: Doctor Error";
                }
              });
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
    clearForm() {
      this.user.username = "";
      this.user.password = "";
      this.user.confirmPassword = "";
      this.doctor.firstName = "";
      this.doctor.lastName = "";
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = "There were problems registering this user.";
    },
  },
};
</script>

<style>
.form-register {
  display: grid;
  grid-template-columns: 200px 200px;
}
</style>