<template>
  <div class="main-content">
    <h1>User Management</h1>

    <!-- Formulario de Usuario -->
    <div class="form-container">
      <form @submit.prevent="handleSubmit">
        <div class="form-group">
          <label for="username">Username</label>
          <input type="text" id="username" v-model="form.username" required />
        </div>
        <div class="form-group">
          <label for="password">Password</label>
          <input
            type="password"
            id="password"
            v-model="form.password"
            required
          />
        </div>
        <div class="form-group">
          <label for="email">Email</label>
          <input type="email" id="email" v-model="form.email" required />
        </div>
        <div class="form-group">
          <label for="roles">Roles</label>
          <select id="roles" v-model="form.roles" multiple required>
            <option v-for="role in roles" :key="role.id" :value="role.name">
              {{ role.name }}
            </option>
          </select>
        </div>
        <button type="submit" class="btn btn-primary">Save User</button>
      </form>
    </div>

    <!-- Tabla de Usuarios -->
    <div class="table-container">
      <table class="user-table">
        <thead>
          <tr>
            <th>Username</th>
            <th>Email</th>
            <th>Roles</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="user in users" :key="user.id">
            <td>{{ user.username }}</td>
            <td>{{ user.email }}</td>
            <td>
              <ul>
                <li v-for="role in user.roles" :key="role.id">
                  {{ role.name }}
                </li>
              </ul>
            </td>
            <td>
              <button @click="editUser(user)" class="btn btn-secondary">
                Edit
              </button>
              <button @click="deleteUser(user.id)" class="btn btn-danger">
                Delete
              </button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "UserManagement",
  data() {
    return {
      form: {
        id: null,
        username: "",
        password: "",
        email: "",
        roles: [], // Cambiar a array para soportar múltiples roles
      },
      users: [],
      roles: [], // Para almacenar los roles
    };
  },
  methods: {
    async fetchUsers() {
      try {
        const response = await axios.get("http://localhost:8080/users");
        this.users = response.data;
      } catch (error) {
        console.error("There was an error fetching the users!", error);
      }
    },
    async fetchRoles() {
      // Método para obtener los roles de la API
      try {
        const response = await axios.get("http://localhost:8080/roles");
        this.roles = response.data;
      } catch (error) {
        console.error("There was an error fetching the roles!", error);
      }
    },
    async handleSubmit() {
      const payload = {
        username: this.form.username,
        password: this.form.password,
        email: this.form.email,
        roles: this.form.roles, // Cambiar a array para soportar múltiples roles
      };
      if (this.form.id) {
        // Editar usuario existente
        try {
          await axios.put(
            `http://localhost:8080/users/${this.form.id}`,
            payload
          );
          this.fetchUsers();
        } catch (error) {
          console.error("There was an error updating the user!", error);
        }
      } else {
        // Agregar nuevo usuario
        try {
          await axios.post("http://localhost:8080/users", payload);
          this.fetchUsers();
        } catch (error) {
          console.error("There was an error adding the user!", error);
        }
      }
      this.resetForm();
    },
    editUser(user) {
      this.form = {
        id: user.id,
        username: user.username,
        password: "", // No podemos obtener la contraseña
        email: user.email,
        roles: user.roles.map((role) => role.name), // Mapear roles a nombres
      };
    },
    async deleteUser(id) {
      try {
        await axios.delete(`http://localhost:8080/users/${id}`);
        this.fetchUsers();
      } catch (error) {
        console.error("There was an error deleting the user!", error);
      }
    },
    resetForm() {
      this.form = {
        id: null,
        username: "",
        password: "",
        email: "",
        roles: [], // Cambiar a array para soportar múltiples roles
      };
    },
  },
  created() {
    this.fetchUsers();
    this.fetchRoles();
  },
};
</script>

<style scoped>
.main-content {
  margin-left: 200px; /* Ajusta este valor según el ancho de tu sidebar */
  padding: 20px;
  background-color: #f8f9fa;
  min-height: 100vh;
}
.form-container {
  background: white;
  padding: 20px;
  margin-bottom: 20px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}
.form-group {
  margin-bottom: 15px;
}
.form-group label {
  display: block;
  margin-bottom: 5px;
}
.form-group input,
.form-group textarea,
.form-group select {
  width: 100%;
  padding: 8px;
  box-sizing: border-box;
  border: 1px solid #ced4da;
  border-radius: 4px;
}
.btn {
  padding: 10px 15px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}
.btn-primary {
  background-color: #007bff;
  color: white;
}
.btn-secondary {
  background-color: #6c757d;
  color: white;
}
.btn-danger {
  background-color: #dc3545;
  color: white;
}
.table-container {
  background: white;
  padding: 20px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}
.user-table {
  width: 100%;
  border-collapse: collapse;
}
.user-table th,
.user-table td {
  border: 1px solid #dee2e6;
  padding: 10px;
  text-align: left;
}
.user-table th {
  background-color: #e9ecef;
}
</style>
