<template>
  <div class="products-container" id="prd">
    <h1>Products List</h1>
    <!-- Barra de búsqueda -->
    <div class="search-container">
      <input
        type="text"
        v-model="searchQuery"
        placeholder="Search Products..."
        class="search-input"
      />
      <button @click="clearSearch" class="clear-button">Clean</button>
    </div>
    <div class="product-list">
      <div
        v-for="product in currentPageProducts"
        :key="product.id"
        class="product-card"
      >
        <div class="img-container">
          <img :src="product.image" alt="Product Image" class="product-image" />
        </div>
        <div class="product-details">
          <h2 class="product-name">{{ product.name }}</h2>
          <p class="product-description">{{ product.description }}</p>
          <p class="product-price">$ {{ product.price }}</p>
          <p class="product-stock">Stock: {{ product.stock }}</p>
          <button @click="addToCart(product)" class="add-to-cart-button">
            Add to cart
          </button>
        </div>
      </div>
    </div>
    <!-- Controles de paginación -->
    <div class="pagination">
      <button @click="previousPage" :disabled="currentPage === 1">Prev</button>
      <span>Page {{ currentPage }} of {{ totalPages }}</span>
      <button @click="nextPage" :disabled="currentPage === totalPages">
        Next
      </button>
    </div>
    <div class="cart-container" id="cart">
      <h2>Cart</h2>
      <div v-if="cart.length === 0">
        <p>The cart is empty</p>
      </div>
      <div v-else>
        <div v-for="(item, index) in cart" :key="index" class="cart-item">
          <div class="cart-item-details">
            <p>{{ item.name }}</p>
            <p>Quantity: {{ item.quantity }}</p>
            <p>Price per item: ${{ item.price }}</p>
            <p>Total: ${{ item.quantity * item.price }}</p>
          </div>
          <div class="cart-item-actions">
            <button class="quantity-button" @click="decreaseQuantity(index)">
              -
            </button>
            <button class="quantity-button" @click="increaseQuantity(index)">
              +
            </button>
            <button class="remove-button" @click="removeFromCart(index)">
              Delete
            </button>
          </div>
        </div>
        <p>Total of the cart: ${{ totalCartPrice }}</p>
        <button class="buy-button" @click="purchase">Buy</button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      products: [], // Lista de productos obtenidos del backend
      searchQuery: "", // Consulta de búsqueda del usuario
      currentPage: 1, // Página actual de la lista de productos
      pageSize: 8, // Tamaño de la página (máximo de productos por página)
      cart: [], // Carrito de compras
    };
  },
  created() {
    // Obtener la lista completa de productos
    axios
      .get("http://localhost:8080/products")
      .then((response) => {
        this.products = response.data;
      })
      .catch((error) => {
        console.error("Error al obtener la lista de productos:", error);
      });
  },
  computed: {
    // Filtrar los productos según la consulta de búsqueda
    filteredProducts() {
      return this.products.filter((product) =>
        product.name.toLowerCase().includes(this.searchQuery.toLowerCase())
      );
    },
    // Calcular el número total de páginas basado en la lista completa de productos
    totalPages() {
      return Math.ceil(this.filteredProducts.length / this.pageSize);
    },
    // Obtener los productos para la página actual
    currentPageProducts() {
      const startIndex = (this.currentPage - 1) * this.pageSize;
      return this.filteredProducts.slice(
        startIndex,
        startIndex + this.pageSize
      );
    },
    // Calcular el precio total del carrito
    totalCartPrice() {
      return this.cart.reduce(
        (total, item) => total + item.quantity * item.price,
        0
      );
    },
  },
  watch: {
    // Observar cambios en la consulta de búsqueda y volver a la página 1
    searchQuery() {
      this.currentPage = 1;
    },
  },
  methods: {
    // Cambiar a la página anterior
    previousPage() {
      if (this.currentPage > 1) {
        this.currentPage--;
      }
    },
    // Cambiar a la página siguiente
    nextPage() {
      if (this.currentPage < this.totalPages) {
        this.currentPage++;
      }
    },
    // Limpiar la consulta de búsqueda
    clearSearch() {
      this.searchQuery = "";
    },
    // Agregar un producto al carrito
    addToCart(product) {
      // Verificar si el producto ya está en el carrito
      const existingProductIndex = this.cart.findIndex(
        (item) => item.id === product.id
      );
      if (existingProductIndex !== -1) {
        // Calcular la cantidad total del producto en el carrito
        const totalQuantityInCart = this.cart.reduce((total, item) => {
          if (item.id === product.id) {
            return total + item.quantity;
          }
          return total;
        }, 0);

        // Verificar si la cantidad total en el carrito más la cantidad a agregar supera el stock disponible
        if (totalQuantityInCart + 1 <= product.stock) {
          this.cart[existingProductIndex].quantity++;
        } else {
          // Mostrar un mensaje de error si se ha alcanzado el stock máximo
          alert(
            "¡No puedes agregar más unidades de este producto al carrito! Has alcanzado el stock máximo."
          );
        }
      } else {
        // Si el producto no está en el carrito, agregarlo
        this.cart.push({ ...product, quantity: 1 });
      }
    },
    // Método para aumentar la cantidad de un producto en el carrito
    increaseQuantity(index) {
      const product = this.cart[index];
      // Verificar si la cantidad total en el carrito más la cantidad a agregar supera el stock disponible
      if (product.quantity + 1 <= product.stock) {
        product.quantity++;
      } else {
        // Mostrar un mensaje de error si se ha alcanzado el stock máximo
        alert(
          "¡No puedes agregar más unidades de este producto al carrito! Has alcanzado el stock máximo."
        );
      }
    },
    // Método para disminuir la cantidad de un producto en el carrito
    decreaseQuantity(index) {
      if (this.cart[index].quantity > 1) {
        this.cart[index].quantity--;
      }
    },
    // Método para eliminar un producto del carrito
    removeFromCart(index) {
      this.cart.splice(index, 1);
    },
    // Método para limpiar el carrito por completo
    clearCart() {
      this.cart = [];
    },
    // Método para realizar la compra
    purchase() {
      // Aquí puedes realizar alguna acción adicional, como enviar los productos al servidor para completar la compra
      // Por ahora, simplemente limpiaremos el carrito
      this.cart = [];
      alert("¡Gracias por tu compra!");
    },
  },
};
</script>
<style scoped>
/* Estilos para la barra de búsqueda */
.search-container {
  display: flex;
  margin-bottom: 20px;
}

.search-input {
  flex-grow: 1;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 5px 0 0 5px;
}

.clear-button {
  background-color: #333;
  color: #fff;
  border: none;
  padding: 8px 15px;
  font-size: 14px;
  border-radius: 0 5px 5px 0;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.clear-button:hover {
  background-color: #03a044;
}
/* Estilos CSS específicos del componente */
.products-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}

.product-list {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
  gap: 20px;
}

.product-card {
  border: 1px solid #c7c6c6;
  border-radius: 5px;
  overflow: hidden;
}
.img-container {
  width: 100%;
  height: 200px; /* Altura del contenedor de la imagen */
  overflow: hidden;
}
.product-image {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.product-details {
  padding: 15px;
}

.product-name {
  font-size: 18px;
  margin-bottom: 5px;
}

.product-description {
  font-size: 14px;
  color: #d3d3d3;
  margin-bottom: 10px;
}

.product-price {
  font-size: 16px;
  color: #03a044;
  margin-bottom: 5px;
}

.product-stock {
  font-size: 14px;
  color: #777;
  margin-bottom: 10px;
}

.add-to-cart-button {
  background-color: #333;
  color: #fff;
  border: none;
  padding: 8px 15px;
  font-size: 14px;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.add-to-cart-button:hover {
  background-color: #03a044;
}
/* Estilos para la paginación */
.pagination {
  display: flex;
  justify-content: center;
  margin-top: 20px;
}

.pagination button {
  background-color: #333;
  color: #fff;
  border: none;
  padding: 8px 15px;
  font-size: 14px;
  border-radius: 5px;
  cursor: pointer;
  margin: 0 5px;
  transition: background-color 0.3s ease;
}
.pagination button:hover {
  background-color: #03a044;
}

.pagination button:disabled {
  background-color: #ccc;
  cursor: not-allowed;
}
/* Estilos para el carrito */
.cart-container {
  margin-top: 50px;
}

.cart-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 10px;
  border-bottom: 1px solid #ccc;
  padding-bottom: 10px;
}

.cart-item-actions button {
  margin-left: 5px;
}

.cart-item-actions button:first-child {
  margin-left: 0;
}
.quantity-button,
.remove-button {
  background-color: #03a044;
  color: #fff;
  border: none;
  padding: 8px 15px;
  font-size: 14px;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.quantity-button:hover,
.remove-button:hover {
  background-color: #1cb85e;
}

.quantity-button {
  margin-right: 5px;
}

.remove-button {
  margin-left: 10px;
}
/* Estilos para el botón de comprar */
.buy-button {
  background-color: #007bff;
  color: #fff;
  border: none;
  padding: 10px 20px;
  font-size: 16px;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
  margin-top: 20px;
}

.buy-button:hover {
  background-color: #0056b3;
}
</style>
