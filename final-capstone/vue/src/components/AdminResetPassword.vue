<template>
  <div>
    <form class="reset-password" v-on:submit.prevent="resetPassword">
        <label for="update-username">Username: </label>
        <input 
            type="text"
            id="update-username"
            v-model="user.username"
        >
        <label for="new-password">New Password: </label>
        <input 
            type="text"
            id="new-password"
            v-model="user.newPassword"
        >
        <button type="submit">Update User Password</button>

    </form>
    <div class="update-error" v-if="updateError">

    </div>
    <div class="update-success" v-if="updateSuccess">
        {{updateSuccessMsg}}
    </div>
  </div>
</template>

<script>
import authService from "../services/AuthService.js";
export default {
    name: "reset-password",
    data() {
        return {
            user: {
                username: "",
                newPassword: ""
            },
            updateError: false,
            updateErrorMsg: "There were problems updating the password.",
            updateSuccess: false,
            updateSuccessMsg: "Password updated!"
        }
    },
    methods: {
        resetPassword() {
            authService
                .resetPassword(this.user)
                .then(response => {
                    if (response.status === 200) {
                        this.updateSuccess = true;
                        this.user.username = "";
                        this.user.newPassword = "";
                    }
                })
                .catch(error => {
                    const response = error.response;
                    this.updateError = true;
                    if (response.status === 400) {
                        this.updateErrorMsg = "User not found."
                    }
                });
        }
    }

}
</script>

<style>

</style>