<template>
  <div id="app">
    <div id="title-banner">
      <h1>The ol' Medical Scheduling App </h1>
    </div>
    <div id="nav">
      <router-link  v-bind:to="{ name: 'home' }" class="nav-button">
      <div class="nav-button-text" >
        <p>Home</p>
      </div>
      </router-link>
    <div class="nav-button">
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div>  
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
}

#content {
  grid-area: content;
}
.nav-button{
  display:flex;
  align-items: center;
  justify-items: center;
  height: 50px;
  width:100%;
  margin:10px;
  background-color: var(--main-color-blue-green);
}

.nav-button:hover{
  background-color: var(--accent-color-yellow);
}

</style>