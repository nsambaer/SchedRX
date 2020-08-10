<template>
  <div>
      <form v-on:submit.prevent="resetPassword">
          <label for="username">Username: </label>
          <input 
            type="text" 
            id="username" 
            class="form-control"
            v-model="user.username"
          >
          <label for="confirm-username">Confirm Username: </label>
          <input 
            type="text" 
            id="confirm-username" 
            class="form-control"
          >
          <label for="old-password">Old Password: </label>
          <input 
            type="text"
            id="old-password"
            class="form-control"
            v-model="user.oldPassword"
          >
          <label for="new-password">New Password: </label>
          <input 
            type="text"
            id="new-password"
            class="form-control"
            v-model="user.newPassword"
          >
          <button type="submit">Change Password</button>
      </form>
  </div>
</template>

<script>


export default {
    data() {
        return{
            user: {
                username: "",
                confirmUsername: "",
                oldPassword: "",
                newPassword: ""
            }
        }
        
    },
    methods: {
        
    }
}
</script>

<style>

</style>