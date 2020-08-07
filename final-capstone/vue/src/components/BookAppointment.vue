<template>
  <div>
        <div class="primary-care">
            <p v-show="!primaryDoctor">You have not chose a primary care physician. To book an appointment, please choose a primary physician</p>
            
        </div>

        <div v-show="primaryDoctor">
        <form>
            <label for="date-selector" >Choose a date: </label>
            <input id="date-selector" type="date" /> <br>
            <select id="time-selector" name="">
                <option value="" selected="true">--Please select a time--</option>
                <!--<option v-for="timeslot in availability" v-bind:key="timeslot.id" value="timeslot" >{{ timeslot }}</option> -->
            </select>
        </form>
        </div>

  </div>
</template>

<script>

export default {
    name: 'book-appointment',


    data() {
        return {
            patientId: this.$store.state.user.id,
            primaryDoctorId: this.$store.state.patient.primaryDoctorId
        }
    },

    computed: {
        primaryDoctor() {
            if (typeof this.primaryDoctorId === 'undefined') {
                return false;
            } else {
                return true;
            }
        }

    },


}
</script>

<style>

</style>