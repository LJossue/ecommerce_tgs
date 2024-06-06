<template>
  <SideBar />
  <UsersManagement />
</template>

<script>
import SideBar from "../components/SideBar.vue";
import UsersManagement from "../components/UsersManagement.vue";
export default {
  name: "MenuUsers",
  components: {
    SideBar,
    UsersManagement,
  },
};
</script>

<style></style>
