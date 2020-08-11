<template>
  <div class="reviews-container">
      <div class="review-div" v-for="review in reviews" v-bind:key="review.id">
          <h4>{{review.reviewTitle}}</h4>
          

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

    created(){
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
            })
                
         }
    },
    computed:{
        currentDoctorId(){
            return this.$store.state.user.id;
        }
    }
    

}
</script>

<style>

</style>