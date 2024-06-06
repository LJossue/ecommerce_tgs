<template>
  <SideBar />
  <ProductManagement />
</template>

<script>
import SideBar from "../components/SideBar.vue";
import ProductManagement from "../components/ProductManagement.vue";
export default {
  name: "MenuProducts",
  components: {
    SideBar,
    ProductManagement,
  },
};
</script>

<style></style>
