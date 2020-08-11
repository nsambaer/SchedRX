<template>
  <div class="reset-password-container">
    <div class="title">Reset User Password</div>
    <div class="reset-password-form">
        <form class="reset-password" v-on:submit.prevent="resetPassword">
            <label for="update-username">Username: </label>
            <input 
                type="text"
                id="update-username"
                v-model="user.username"
            >
            <label for="new-password">New Password: </label>
            <input 
                type="password"
                id="new-password"
                v-model="user.newPassword"
            >
            <button type="submit">Update User Password</button>

        </form>
    </div>
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

:root{
    --main-color-turqoise: #086972;
    --main-color-blue-green: #01a9b4;
    --main-color-light-blue: #87dfd6;
    --accent-color-yellow: #fbfd8a;
}

.reset-password-container {
    background-color: var(--main-color-turqoise)

}

.title {
    background-color: var(--main-color-blue-green)
}

/* .reset-password {
    display: grid;
    grid-template-columns: 150px;

} */

</style>