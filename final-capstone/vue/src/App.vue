<template>
  <div id="app">
    <div id="title-banner">
      <h1>Ye olde Medical Scheduling App </h1>
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
--main-color-turqoise: #086972;
--main-color-blue-green: #01a9b4;
--main-color-light-blue: #87dfd6;
--accent-color-yellow: #fbfd8a;
}


#app{
  display:grid;
  grid-template-columns: 1fr 4fr 1fr;
  grid-template-areas:
  "header header  header"
  "nav content  .";
 
}
  @import url('https://fonts.googleapis.com/css?family=Lemonada');

body{
  background-color: var(--main-color-light-blue);
  font-family: 'Lemonada', Arial, Helvetica, sans-serif;
}

#title-banner{
  grid-area: header;
  text-align: center;
}

#nav{
  grid-area: nav;
  display: flex;
  flex-direction: column;
  align-items: center;
  margin: 5px;
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
  background-color: var(--main-color-blue-green);
}
.nav-button-text{
  display: flex;
  justify-items: center;
  text-align: center;
  font-size: 1.5em;
  color: white;
}

.nav-button:hover{
  background-color: var(--accent-color-yellow);
  color:black;
}
.nav-button .nav-button-text:hover{
  color:black;
}

a{
  text-decoration: none;

}

</style>