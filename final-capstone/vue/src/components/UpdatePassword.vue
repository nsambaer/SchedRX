<template>
  <div>
      <form v-on:submit.prevent="resetPassword">
          <label for="old-password">Old Password: </label>
          <input 
            type="text"
            id="old-password"
            class="form-control"
            v-model="loginUser.password"
          >
          <label for="new-password">New Password: </label>
          <input 
            type="text"
            id="new-password"
            class="form-control"
            v-model="updatedUser.password"
          >
          <button type="submit">Change Password</button>
      </form>
      <div
        class="invalid-password"
        v-if="invalidCredentials"
      >Invalid Password.</div>
      <div
        class="update-error"
        v-if="updateError"
      >{{updateErrorMessage}}</div>
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
              password: "",
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
              if (response.status === 201) {
                authService
                  .resetPassword(this.updatedUser)
                  .then((response) => {
                    if (response.status === 200) {
                      this.updateSuccess = true;
                      this.loginUser.password = "";
                      this.updatedUser.password = "";
                    }
                  })
                  .catch((updateError) => {
                    this.updateError = true;
                    if (updateError.response) {
                      this.updateErrorMsg = 
                        "Error updating password. Response received was '" +
                        updateError.response.statusText + "'.";
                    } else if (updateError.request) {
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

</style>