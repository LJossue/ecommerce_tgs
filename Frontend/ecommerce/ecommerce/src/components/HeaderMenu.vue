<template>
  <header class="header">
    <div class="container">
      <router-link to="/" class="logo">E-Commerce</router-link>
      <nav class="nav">
        <a href="#prd" class="nav-link">Products</a>
        <a href="#cart" class="nav-link">Cart</a>
        <router-link to="/menuproducts" class="nav-link">Menu</router-link>
      </nav>
    </div>
  </header>
</template>

<script>
export default {
  // Componente de encabezado (header) para la aplicación Vue.js
};
</script>

<style scoped>
/* Estilos CSS específicos del componente */
.header {
  background-color: #333;
  color: #fff;
  padding: 20px 0;
}

.container {
  max-width: 1200px;
  margin: 0 auto;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.logo {
  font-size: 24px;
  font-weight: bold;
  text-decoration: none;
  color: #fff;
  cursor: pointer;
  transition: color 0.3s ease; /* Agrega una transición suave al cambio de color */
}
.logo:hover {
  color: #03a044;
}

.nav {
  display: flex;
}

.nav-link {
  color: #fff;
  text-decoration: none;
  margin-left: 20px;
  font-size: 16px;
  cursor: pointer; /* Hace que el cursor cambie a una mano al pasar por encima */
  transition: color 0.3s ease; /* Agrega una transición suave al cambio de color */
}

.nav-link:hover {
  color: #03a044; /* Cambia el color al pasar el ratón por encima */
}
</style>
