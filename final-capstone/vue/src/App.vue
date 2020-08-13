<template>
  <div id="app">
    <div id="title-banner">
      <img id="header-image" src="./assets/images/header-banner-transperant.png" />
    </div>
    <div id="nav">
      <router-link  v-bind:to="{ name: 'home' }" class="nav-button">
     
        Home
     
      </router-link>
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''" class="nav-button">Logout</router-link>
        
    </div>
    <div id="content">
      <router-view></router-view>
    </div>
    
    
    </div>
</template>
<script>
export default {
}
</script>
<style >
:root{
--main-color-dark1: #004a7c;
--main-color-dark2: #005691;
--main-color-dark3: #e8f1f5;
--main-color-dark4: #fafafa;
}


#app{
  display:grid;
  grid-template-columns: 1fr 2fr 2fr 1fr;
  grid-template-areas:
  "header header header header"
  ".  . nav ."
  ".  content content  .";
 
}

@media screen and (max-width: 1024 px) {
  #app {
    grid-template-columns: 1fr 1fr;
    grid-template-areas:
      "header header"
      ". nav"
      "content content";
  }

  #header-image {
    height:auto;
    width: 40%;
  }
}
 


#title-banner{
  display:flex;
  justify-content: flex-start;
  grid-area: header;
  text-align: center;
  border-bottom: 10px solid var(--main-color-dark2)
}
#header-image{
 
  height: 354px;
  width:1000px;
  margin: 20px 20px 20px 100px;
}

#nav{
  grid-area: nav;
  display: flex;
  align-items: center;
  max-width: 500px;
  margin: 5px;
}

#banner-background{
/* The image used */
  background-image: url("./assets/images/doctor-front-banner.jpg");

  /* Set a specific height */
  height: 500px;

  /* Create the parallax scrolling effect */
  background-attachment: fixed;
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;

}

#content {
  grid-area: content;
  margin: 10px;

 
  

}
.nav-button{
  display:flex;
  align-items: center;
  color:white;
  font-size: 1.5em;
  border: ridge;
 
  justify-content: center;
  text-align: center;
  height: 50px;
  width:100%;
  margin:10px;
  background-color: var(--main-color-dark2);
}
.nav-button-text{
  display: flex;
  justify-items: center;
  text-align: center;
  font-size: 1.5em;
  color: white;
}

.nav-button:hover{
  background-color: var(--main-color-dark4);
  color:black;
}
.nav-button .nav-button-text:hover{
  color:black;
}

a{
  text-decoration: none;

}

</style>