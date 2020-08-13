<template>
  <div class="reviews-container standard-component-container">
       <h2>Reviews</h2>
      <div class="review-div standard-display-slot" v-for="review in reviews" v-bind:key="review.id" >
          <h2>{{review.reviewTitle}}</h2>
          <h3>{{ review.rating }}</h3>
          <p>{{ review.reviewDescription }}</p>
          

      </div>
    
</div>

</template>

<script>

import doctorService from "../services/DoctorService.js";

export default {
    name: "doctor-reviews",
    data(){
        return{

            reviews:[]
            
        }
    },

    methods: {
        getReviews() {
            doctorService.getReviews(this.$store.state.user.id).then(
            response => {
                this.reviews = response.data;
            }).catch((error) => {
                const response = error.response;
                this.errors = true;
                if (response.status === 400) {
                    this.errorMsg = "Bad Request: Validation Errors";
                }
            });
                
         }
    },

    computed:{
        currentDoctorId(){
            return this.$store.state.user.id;
        }
    },
    created() {
        this.getReviews();
        // doctorService.getReviews(this.$store.state.user.id).then(
        //     response => {
        //         this.reviews = response.data;
        //     }).catch((error) => {
        //         const response = error.response;
        //         this.errors = true;
        //         if (response.status === 400) {
        //             this.errorMsg = "Bad Request: Validation Errors";
        //         }
        //     });
    }

}
</script>

<style>

</style>