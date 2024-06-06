import { createRouter, createWebHistory } from "vue-router";
import LoginForm from "./pages/LoginForm.vue";
import EcommercePrincipal from "./pages/EcommercePrincipal.vue";
import MenuProducts from "./pages/MenuProducts.vue";
import MenuUsers from "./pages/MenuUsers.vue";

const routes = [
  {
    name: "LoginForm",
    path: "/login",
    component: LoginForm,
  },
  {
    name: "EcommercePrincipal",
    path: "/",
    component: EcommercePrincipal,
  },
  {
    name: "MenuProducts",
    path: "/menuproducts",
    component: MenuProducts,
  },
  {
    name: "MenuUsers",
    path: "/menuusers",
    component: MenuUsers,
  },
];

const router = createRouter({
  history: createWebHistory(), // Invoca createWebHistory para crear el objeto de historial
  routes: routes,
});

export default router;
