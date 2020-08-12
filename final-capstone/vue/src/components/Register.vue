<template>
  <div id="register" class="text-center">
    <form class="form-register" @submit.prevent="register">
      <h1 class="h3 mb-3 font-weight-normal">Create Account</h1>
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
          v-model="patient.firstName"
          required
        />
        <label for="lastName" class="sr-only">Last Name:</label>
        <input
          type="text"
          id="lastName"
          class="form-control"
          placeholder="Last Name"
          v-model="patient.lastName"
          required
        />
        <label for="phone" class="sr-only">Phone Number:</label>
        <input
          type="text"
          id="phone"
          class="form-control"
          placeholder="Phone Number"
          v-model="patient.phone"
          required
        />
      </div>
      <div>
        <label for="address" class="sr-only">Address:</label>
        <input
          type="text"
          id="address"
          class="form-control"
          placeholder="Address"
          v-model="patient.address"
          required
        />
        <label for="city" class="sr-only">City:</label>
        <input
          type="text"
          id="city"
          class="form-control"
          placeholder="City"
          v-model="patient.city"
          required
        />
        <label for="state" class="sr-only">State:</label>
        <input
          type="text"
          id="state"
          class="form-control"
          placeholder="state"
          v-model="patient.state"
          required
        />
        <label for="zipCode" class="sr-only">Zip Code:</label>
        <input
          type="text"
          id="zipCode"
          class="form-control"
          placeholder="Zip Code"
          v-model="patient.zipCode"
          required
        />
        <div>
          <label for="birthdate" class="sr-only">Birth Date:</label>
          <input
            type="date"
            id="birthdate"
            class="form-control"
            placeholder="birthdate"
            v-model="patient.birthdate"
            required
          />
        </div>
      </div>
      <router-link :to="{ name: 'home' }">Have an account?</router-link>
      <button class="btn btn-lg btn-primary btn-block" type="submit">Create Account</button>
    </form>
  </div>
</template>

<script>
import authService from "@/services/AuthService";
import medService from "@/services/MedicalService";

export default {
  name: "register",
  data() {
    return {
      user: {
        username: "",
        password: "",
        confirmPassword: "",
        role: "patient",
      },
      patient: {
        patientId: "",
        firstName: "",
        lastName: "",
        phone: "",
        address: "",
        city: "",
        state: "",
        zipCode: "",
        birthdate: "",
      },
      registrationErrors: false,
      registrationErrorMsg: "There were problems registering this user.",
    };
  },
  methods: {
    register() {
      let pw = this.user.password;
      let numCheck = false;
      for (let x = 0; x < pw.length; x++) {
        if (!isNaN(pw.charAt(x))) {
          numCheck = true;
        }
      }
      if (!numCheck) {
        this.registrationErrors = true;
        this.registrationErrorMsg = "Password must be at least 7 characters in length and contain at least one number";
      }
      else if (this.user.password.length < 7) {
        this.registrationErrors = true;
        this.registrationErrorMsg = "Password must be at least 7 characters in length and contain at least one number";
      }
      else if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = "Password & Confirm Password do not match.";
      } else {
        authService
          .register(this.user)
          .then((response) => {
            this.patient.patientId = response.data;
            medService
              .addPatient(this.patient)
              .then((patientResponse) => {
                if (patientResponse.status == 201) {
                  this.$router.push({
                    name: "home",
                  });
                }
              })
              .catch((patientError) => {
                const patientResponse = patientError.response;
                this.registrationErrors = true;
                if (patientResponse.status === 400) {
                  this.registrationErrorMsg = "Bad Request: Patient Error";
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
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = "There were problems registering this user.";
    },
  },
};
</script>

<style></style>
