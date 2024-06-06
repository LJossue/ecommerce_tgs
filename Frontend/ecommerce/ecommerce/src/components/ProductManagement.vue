<template>
  <div class="main-content">
    <h1>Product Management</h1>

    <!-- Formulario de Producto -->
    <div class="form-container">
      <form @submit.prevent="handleSubmit">
        <div class="form-group">
          <label for="product-name">Product Name</label>
          <input type="text" id="product-name" v-model="form.name" required />
        </div>
        <div class="form-group">
          <label for="product-price">Price</label>
          <input
            type="number"
            id="product-price"
            v-model="form.price"
            step="0.01"
            required
          />
        </div>
        <div class="form-group">
          <label for="product-description">Description</label>
          <textarea
            id="product-description"
            v-model="form.description"
            required
          ></textarea>
        </div>
        <div class="form-group">
          <label for="product-stock">Stock</label>
          <input
            type="number"
            id="product-stock"
            v-model="form.stock"
            required
          />
        </div>
        <div class="form-group">
          <label for="product-image">Image URL</label>
          <input type="text" id="product-image" v-model="form.image" required />
        </div>
        <button type="submit" class="btn btn-primary">Save Product</button>
      </form>
    </div>

    <!-- Tabla de Productos -->
    <div class="table-container">
      <table class="product-table">
        <thead>
          <tr>
            <th>Name</th>
            <th>Price</th>
            <th>Description</th>
            <th>Stock</th>
            <th>Image</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="product in products" :key="product.id">
            <td>{{ product.name }}</td>
            <td>{{ product.price }}</td>
            <td>{{ product.description }}</td>
            <td>{{ product.stock }}</td>
            <td>
              <img
                :src="product.image"
                alt="Product Image"
                class="product-image"
              />
            </td>
            <td>
              <button @click="editProduct(product)" class="btn btn-secondary">
                Edit
              </button>
              <button @click="deleteProduct(product.id)" class="btn btn-danger">
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
  name: "ProductManagement",
  data() {
    return {
      form: {
        id: null,
        name: "",
        price: "",
        description: "",
        stock: "",
        image: "",
      },
      products: [],
    };
  },
  methods: {
    async fetchProducts() {
      try {
        const response = await axios.get("http://localhost:8080/products");
        this.products = response.data;
      } catch (error) {
        console.error("There was an error fetching the products!", error);
      }
    },
    async handleSubmit() {
      if (this.form.id) {
        // Editar producto existente
        try {
          await axios.put(
            `http://localhost:8080/products/${this.form.id}`,
            this.form
          );
          this.fetchProducts();
        } catch (error) {
          console.error("There was an error updating the product!", error);
        }
      } else {
        // Agregar nuevo producto
        try {
          await axios.post("http://localhost:8080/products", this.form);
          this.fetchProducts();
        } catch (error) {
          console.error("There was an error adding the product!", error);
        }
      }
      this.resetForm();
    },
    editProduct(product) {
      this.form = { ...product };
    },
    async deleteProduct(id) {
      try {
        await axios.delete(`http://localhost:8080/products/${id}`);
        this.fetchProducts();
      } catch (error) {
        console.error("There was an error deleting the product!", error);
      }
    },
    resetForm() {
      this.form = {
        id: null,
        name: "",
        price: "",
        description: "",
        stock: "",
        image: "",
      };
    },
  },
  created() {
    this.fetchProducts();
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
.form-group textarea {
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
.product-table {
  width: 100%;
  border-collapse: collapse;
}
.product-table th,
.product-table td {
  border: 1px solid #dee2e6;
  padding: 10px;
  text-align: left;
}
.product-table th {
  background-color: #e9ecef;
}
.product-image {
  width: 100px;
  height: auto;
}
</style>
