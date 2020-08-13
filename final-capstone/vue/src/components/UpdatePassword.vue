<template>
  <div class="standard-component-container">
      <h3 class="standard-component-header">Update Password</h3>
      <form v-on:submit.prevent="resetPassword()">
          <div class="old-password">
            <label class="pass-label" for="old-password">Old Password: </label>
            <input 
              type="text"
              id="old-password"
              class="form-control"
              v-model="loginUser.password"
            >
          </div>
          <div class="oldPassword">
            <label class="pass-label" for="new-password">New Password: </label>
            <input 
              type="text"
              id="new-password"
              class="form-control"
              v-model="updatedUser.newPassword"
            >
          
          </div>  
          
          <button class="standard-button" type="submit">Change Password</button>
      </form>
      <div
        class="update-success"
        v-if="updateSuccess"
      >Password updated!</div>
      <div
        class="invalid-password"
        v-if="invalidCredentials"
      >Invalid Password.</div>
      <div
        class="update-error"
        v-if="updateError"
      >Update error</div>
  </div>
</template>

<script>
import authService from "../services/AuthService.js";

export default {
    data() {
        return{
            loginUser: {
              username: "",
              password: "",
            },
            updatedUser: {
              username: "",
              newPassword: "",
            },
            updateSuccess: false,
            updateError: false,
            updateErrorMessage: "",
            invalidCredentials: false
        }
        
    },
    created() {
      this.loginUser.username = this.$store.state.user.username;
      this.updatedUser.username = this.$store.state.user.username;
    },
    methods: {
        resetPassword() {
          authService
            .login(this.loginUser)
            .then((response) => {
              if (response.status === 200) {
                authService
                  .resetPassword(this.updatedUser)
                  .then((response) => {
                    if (response.status === 200) {
                      this.updateSuccess = true;
                      this.loginUser.password = "";
                      this.updatedUser.newPassword = "";
                    }
                  })
                  .catch((error) => {
                    this.updateError = true;
                    if (error.response) {
                      this.updateErrorMsg = 
                        "Error updating password. Response received was '" +
                        error.response.statusText + "'.";
                    } else if (error.request) {
                      this.updateErrorMessage = 
                        "Error updating password. Server could not be reached.";
                    } else {
                      this.updateErrorMessage = 
                        "Error updating password. Request could not be created.";
                    }
                  });
              }
            })
            .catch((authError) => {
              const response = authError.response;
              if (response.status === 401) {
                this.invalidCredentials = true;
              }
            })
        }
    }
}
</script>

<style>

.pass-label {
  display: inline-block;
  min-width: 125px;
}


</style>