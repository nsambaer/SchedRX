<template>
  <div>
    <form v-on:submit.prevent="postReview">
        <h2>Leave a Review</h2>
      <span>Choose Office:</span>
      <div v-for="office in offices" v-bind:key="office.officeId">
        <input
          type="radio"
          name="officeId"
          :value="office.officeId"
          v-model="newReview.office.officeId"
          required
        />
        <label>{{ office.officeName }}</label>
        <div
          v-show="office.officeId == newReview.office.officeId"
          v-for="doctor in office.doctorList"
          v-bind:key="doctor.doctorId"
        >
          <input
            type="radio"
            name="doctorId"
            :value="doctor.doctorId"
            v-model="newReview.doctor.doctorId"
            required
          />
          <label>{{ doctor.firstName }} {{ doctor.lastName }}</label>
        </div>
      </div>
      <label for="title">Review Title: </label>
      <input id="title" v-model="newReview.reviewTitle" type="text" required /> <br>
      <label for="rating">Rating: </label>
      <select v-model="newReview.rating" id="rating" required>
        <option value="1">1</option>
        <option value="2">2</option>
        <option value="3">3</option>
        <option value="4">4</option>
        <option value="5">5</option>
      </select> <br>
      <label for="description">Your feedback: </label> <br>
      <textarea v-model="newReview.reviewDescription" id="description" cols="30" rows="10" required></textarea> <br>
      <input type="submit" />
    </form>
  </div>
</template>

<script>
import patientService from "@/services/PatientService";
import medService from "@/services/MedicalService";

export default {
  data() {
    return {
      newReview: {
        patient: {
          patientId: "",
        },
        doctor: {
          doctorId: "",
        },
        office: {
          officeId: "",
        },
        rating: "",
        reviewTitle: "",
        rewviewDescription: "",
        comments: ''
      },

      offices: [],
    };
  },

  methods: {
    postReview() {
      this.newReview.patient.patientId = this.$store.state.user.id;

      patientService
        .createReview(this.newReview)
        .then(() => {

            this.newReview.patient.patientId = "";
            this.newReview.doctor.doctorId = "";
            this.newReview.office.officeId = "";
            this.newReview.rating = "";
            this.newReview.reviewTitle = "";
            this.newReview.reviewDescription = "";
        })
        .catch((error) => {
          const response = error.response;
          this.errors = true;
          if (response.status === 400) {
            this.errorMsg = "Bad Request: Validation Errors";
          }
        });

    },
  },

  created() {
    medService
      .listAllOffices()
      .then((response) => {
        this.offices = response.data;
      })
      .catch((error) => {
        const response = error.response;
        this.errors = true;
        if (response.status === 400) {
          this.errorMsg = "Bad Request: Validation Errors";
        }
      });
  },
};
</script>

<style>
</style>