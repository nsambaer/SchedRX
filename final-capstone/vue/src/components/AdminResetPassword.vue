<template>
  <div class="reset-password-container">
    
    
        
    
    <div class="reset-password-form">
        <h2 class="title">Reset User Password</h2>
        <form class="reset-password" v-on:submit.prevent="resetPassword">
            <div>
                <label class="username-label" for="update-username">Username: </label>
                <input
                    class="username-input" 
                    type="text"
                    id="update-username"
                    v-model="user.username"
                >
            </div>
            <div>
                <label class="password-label" for="new-password">New Password: </label>
                <input 
                    class="password-input"
                    type="password"
                    id="new-password"
                    v-model="user.newPassword"
                >
                
            </div>
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
    --main-color-dark1: #004a7c;
    --main-color-dark2: #005691;
    --main-color-dark3: #e8f1f5;
    --main-color-dark4: #fafafa;
}

.reset-password-container {
    background-color: var(--main-color-dark2);
   
    text-align: center;

}

.reset-password-form {
    text-align: center;
    display: grid;
    grid-template-areas: 
        "title title"
        "user-label user-input"
        "password-label password-input"
        "button button";
}

.title {
    background-color: var(--main-color-dark3);
      width: 100%;
      border-style:solid;
      border-width: 1px;
      border-radius: 5px;
      text-align: center;
      grid-area: title;
}



.username-label {
    grid-area: user-label;
    width: 100%;
}

.username-input {
    grid-area: user-input;
}
.password-label {
    grid-area: password-label;
}
.password-input {
    grid-area: password-input;
}

button {
    grid-area: button;
}

</style>