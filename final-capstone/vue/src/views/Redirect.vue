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
    this.$router.push({name: 'home'});
}

}
</script>

<style>

</style>