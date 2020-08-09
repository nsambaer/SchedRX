<template>
  <div>
      <h2>
          Redirecting...
      </h2>
  </div>
</template>

<script>
export default {

beforeCreate() {
    let role = this.$store.state.userRole;
    switch (role)  {
            case "ROLE_PATIENT":
              this.$router.push({ name: "patient" });
              break;
            case "ROLE_DOCTOR":
              this.$router.push({ name: "doctor" });
              break;
            case "ROLE_ADMIN":
              this.$router.push({ name: "admin" });
              break;
            default:
                this.$router.push({ name: "home" });
            break;
          }
}

}
</script>

<style>

</style>