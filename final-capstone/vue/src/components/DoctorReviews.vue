<template>
  <div class="reviews-container standard-component-container">
       <h3 class="standard-component-header">Reviews</h3>
      <div class="review-div standard-display-slot" v-for="review in reviews" v-bind:key="review.id" >
          <h4>{{review.reviewTitle}}</h4>
          <div class="review-body">
              <b>Reviewer: </b>{{review.patient.firstName}} {{review.patient.lastName}}<br />
              <b>Rating: </b>{{ review.rating }} out of 5 <br />
              <b>Review: </b>{{ review.reviewDescription }}
            </div>
          
          
          

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
        },

        patientLastNameFirstLetter(){
            return this.reviews.patient.lastName.substr(0,1);
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

h2{
    color:var(--main-color-dark1);
    
}

.review-div{
    flex-direction: column;
    padding:10px;
}

h4{
    margin-bottom:5px;
    margin-top:5px;
}


</style>