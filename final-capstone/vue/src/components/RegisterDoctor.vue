<template>
  <div id="register-doctor" class="text-center">

    <h2 class="reg-doctor-title">Register Doctor</h2>
    <form class="form-register-doctor" @submit.prevent="register">
      <div
        class="alert alert-danger"
        role="alert"
        v-if="registrationErrors"
      >{{ registrationErrorMsg }}</div>
      <div class="doctor-username-container">
        <label for="doctor-username" class="register-doctor-label">Username:</label>
        <input
          type="text"
          id="doctor-username"
          class="doctor-form-control"
          placeholder="Username"
          v-model="user.username"
          required
          autofocus
        />
      </div>
      <div class="doctor-password-container">
      <label for="doctor-password" class="register-doctor-label">Password:</label>
        <input
          type="password"
          id="doctor-password"
          class="doctor-form-control"
          placeholder="Password"
          v-model="user.password"
          required
        />
      </div>
      <div class="doctor-confirm-container">
        <label for="doctor-confirmPassword" class="register-doctor-label">Confirm Password:</label>
        <input
          type="password"
          id="doctor-confirmPassword"
          class="doctor-form-control"
          placeholder="Confirm Password"
          v-model="user.confirmPassword"
          required
        />
      </div>
      <div class="doctor-first-name-container">
        <label for="doctor-firstName" class="register-doctor-label">First Name:</label>
        <input
          type="text"
          id="doctor-firstName"
          class="doctor-form-control"
          placeholder="First Name"
          v-model="doctor.firstName"
          required
        />
      </div>
      <div class="last-name-container">
        <label for="doctor-lastName" class="register-doctor-label">Last Name:</label>
        <input
          type="text"
          id="doctor-lastName"
          class="doctor-form-control"
          placeholder="Last Name"
          v-model="doctor.lastName"
          required
        />
      </div>

      <button class="standard-button" type="submit">Register Doctor</button>
      <button v-on:click.prevent="registrationErrors = false" v-show="registrationErrors">Clear Errors</button>
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

.register-doctor-label {
  display: inline-block;
  min-width: 150px;
  font-weight: bold;
}

@media screen and (max-width: 828px) {
  .register-doctor-label {
    display: block;
  }
  }



/* :root{
      --main-color-dark1: #004a7c;
      --main-color-dark2: #005691;
      --main-color-dark3: #e8f1f5;
      --main-color-dark4: #fafafa;
  }


#register-doctor {
  background-color:var(--main-color-dark2);
  border-radius: 5px;
  border-style:solid;
  border-width: 1px;
  width: 100%;
  display: grid;
  grid-template-areas: 
    "username"
    "password"
    "confirm"
    "first-name"
    "last-name";
  text-align: left;
}

.doctor-form-register {
  
    width: 100%;
  
  
  
}



.doctor-username-container {
  grid-area: username;
}
.doctor-password-container {
  grid-area: password;
}
.doctor-confirm-container {
  grid-area: confirm;
}
.doctor-first-name-container {
  grid-area: first-name;
}
.doctor-last-name-container {
  grid-area: last-name;
}
.reg-doctor-title {
  background-color:var(--main-color-dark3);
  border-radius: 5px;
  border-style: solid;
  border-width: 1px;
}

.register-doctor-label {
  background-color: var(--main-color-dark3);
  border-style: solid;
  border-width: 1px;
  border-radius: 5px;
  width: 100%;
  text-align: left;
  min-width: 200px;
  


} */


</style>