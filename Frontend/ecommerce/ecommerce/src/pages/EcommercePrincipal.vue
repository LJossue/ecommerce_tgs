<template>
  <HeaderMenu />
  <SliderPhotos />
  <ListaProductos />
</template>

<script>
import HeaderMenu from "../components/HeaderMenu.vue";
import SliderPhotos from "../components/SliderPhotos.vue";
import ListaProductos from "../components/ListaProductos.vue";

export default {
  name: "EcommercePrincipal",
  components: {
    HeaderMenu,
    ListaProductos,
    SliderPhotos,
  },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
body {
  margin: 0;
}
</style>
