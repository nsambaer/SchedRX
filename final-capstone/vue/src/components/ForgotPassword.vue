<template>
  <div>
      <form 
        class="form-forgot-password" 
        v-on:submit.prevent="submitForgotPassword()"
      >
        <label for="fp-username">Username: </label>
        <input 
            id="fp-username" 
            type="text"
            v-model="username"
        />
        <label for="fp-office">Choose your office: </label>
        <select name="" id=""></select>
        <button type="submit">Request Password Reset</button>
      </form>
      <div 
        class="request-success"
        v-if="requestSuccess"
      >Password Request Sent - an administrator will follow up with you shortly.</div>
      <div
        class="request-error"
        v-if="requestError"
      >An error occured - please refresh the page and try again</div>
      <div
        class="user-not-found"
        v-if="userNotFound"
      >Username not found. Please try again.</div>
  </div>
</template>

<script>
import authService from '../services/AuthService.js';
import notificationService from '../services/NotificationService.js';
export default {
    data() {
        return {
            userNotFound: false,
            username: "",
            requestSuccess: false,
            requestError: false,
            notification: {
                userId: 2,
                message: ""
            }
        }
    },
    methods: {
        findUser(){
            
        },
        
        submitForgotPassword() {
            this.notification.message = "Forgotten password request for user: " + this.username;
            authService
                .getUserIdByUsername(this.username)
                .then((response) => {
                    if (response.status === 200) {
                       // this.notification.userId = response.data;
                        notificationService
                        .createNotification(this.notification)
                        .then((response) => {
                            if (response.status === 201) {
                                this.requestSuccess = true;
                                this.username = "";
                            }
                        })
                        .catch((error) => {
                            const response = error.response;
                            if (response.status === 401) {
                                this.requestError = true;
                            }
                        })
                    }
                })
                .catch((error) => {
                    const response = error.response;
                    if (response.status === 500) {
                        this.userNotFound = true;
                    }
                });
        }
    }
}
</script>

<style>

</style>