<template>
  <div id="app">
    <div id="title-banner">
      <h1>SchedRx Medical Scheduling Service </h1>
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
  grid-template-columns: 1fr 4fr 1fr;
  grid-template-areas:
  "header header  header"
  "nav content  .";
 
}
  @import url('https://fonts.googleapis.com/css?family=Mulish');

body{
  background-color: var(--main-color-dark3);
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