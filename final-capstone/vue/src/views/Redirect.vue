<template>
  <div>
      <h2>
          Redirecting...
      </h2>
  </div>
</template>

<script>
import patientService from '@/services/PatientService';

export default {

beforeCreate() {
    let role = this.$store.state.userRole;
    switch (role)  {
            case "ROLE_PATIENT":
               patientService
        .getPatient(this.$store.state.user.id)
        .then((response) => {
          this.$store.commit("SET_PATIENT", response.data);
          this.$router.push({ name: "patient" });
        })
        .catch((error) => {
          const response = error.response;
          this.errors = true;
          if (response.status === 400) {
            this.errorMsg = "Bad Request: Validation Errors";
          }
        });
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