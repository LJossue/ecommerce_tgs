<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
// Importa el enrutador
import router from "./router";

export default {
  name: "App",
  // Asigna el enrutador a la instancia de Vue
  router,
};
</script>

<style>
* {
  font-family: Roboto, Arial, Helvetica, sans-serif;
  margin: 0;
  padding: 0;
  outline: none;
  border: none;
  text-decoration: none;
  box-sizing: border-box;
  scroll-behavior: smooth;
}
#app {
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
body {
  margin: 0;
}
</style>
