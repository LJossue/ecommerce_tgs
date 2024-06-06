<template>
  <nav>
    <ul>
      <li>
        <router-link to="/" class="logo">
          <span class="nav-item">Ecommerce</span>
        </router-link>
      </li>
      <li>
        <router-link to="/menuproducts">
          <i class="fa-solid fa-shoe-prints"></i>
          <span class="nav-item">Products</span>
        </router-link>
      </li>
      <li>
        <router-link to="/menuusers">
          <i class="fas fa-users"></i>
          <span class="nav-item">Users</span>
        </router-link>
      </li>
      <li>
        <router-link to="/menuorders">
          <i class="fa-solid fa-folder-open"></i>
          <span class="nav-item">Orders</span>
        </router-link>
      </li>
      <li>
        <router-link to="/menuprofile">
          <i class="fas fa-user"></i>
          <span class="nav-item">Profile</span>
        </router-link>
      </li>
      <li>
        <a href="#" class="logout">
          <i class="fas fa-sign-out-alt"></i>
          <span class="nav-item">Sign Out</span>
        </a>
      </li>
    </ul>
  </nav>
</template>

<script>
export default {
  name: "SideBar",
};
</script>

<style scoped>
nav {
  position: fixed;
  top: 0;
  bottom: 0;
  height: 100%;
  left: 0;
  background: #2c3e50;
  width: 190px;
  overflow: hidden;
  transition: width 0.2s linear;
  box-shadow: 0 20 35px rgba(0, 0, 0, 0.1);
  text-align: left; /* Alinea el contenido del nav a la izquierda */
}
.logo {
  display: flex;
}
.logo span {
  font-weight: bold;
  font-size: 18px;
  text-transform: uppercase;
  padding-bottom: 20px;
}
a {
  position: relative;
  color: #ffff;
  font-size: 14px;
  display: table;
  width: 190px;
  padding: 10px;
}
.fas,
.fa-solid {
  position: relative;
  width: 70px;
  height: 40px;
  top: 14px;
  font-size: 20px;
  text-align: center;
}
.nav-item {
  position: relative;
  top: 12px;
  margin-left: 10px;
}
a:hover {
  background: #03a044;
}
.logout {
  position: absolute;
  bottom: 0;
}
</style>
