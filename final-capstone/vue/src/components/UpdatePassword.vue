<template>
  <div>
      <form v-on:submit.prevent="resetPassword">
          <label for="old-password">Old Password: </label>
          <input 
            type="text"
            id="old-password"
            class="form-control"
            v-model="loginUser.oldPassword"
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
            loginUser: {
              username: "",
              oldPassword: "",
            }
        }
        
    },
    methods: {
        
    }
}
</script>

<style>

</style>