<template>
  <main class="page">
    <header class="hero">
      <div>
        <p class="eyebrow">MINI STORE</p>
        <h1>Shopping Cart</h1>
        <p>Browse products, manage your cart, and place an order.</p>
      </div>
      <div class="cart-count">
        <span>Cart items</span>
        <strong>{{ totalItems }}</strong>
      </div>
    </header>

    <p v-if="message" class="notice" :class="{ error: isError }">{{ message }}</p>

    <section class="panel">
      <div class="section-title">
        <div>
          <h2>Products</h2>
          <p class="muted">Products are loaded from the Spring Boot API.</p>
        </div>
        <button class="secondary" @click="loadProducts">Refresh</button>
      </div>

      <p v-if="loadingProducts" class="muted">Loading products...</p>
      <p v-else-if="products.length === 0" class="muted">No products available.</p>

      <div class="product-grid">
        <article v-for="product in products" :key="product.id" class="product-card">
         
          <h3>{{ product.name }}</h3>
          <p class="muted">{{ product.description }}</p>
          <div class="product-bottom">
            <strong>₹{{ product.price }}</strong>
            <button @click="addToCart(product)">Add to cart</button>
          </div>
        </article>
      </div>
    </section>

    <section class="panel">
      <h2>Your Cart</h2>
      <p v-if="cart.length === 0" class="muted">Your cart is empty. Add a product above.</p>

      <div v-for="item in cart" :key="item.id" class="cart-row">
        <div class="cart-info">
          <strong>{{ item.name }}</strong>
          <span class="muted">₹{{ item.price }} each</span>
        </div>
        <div class="quantity">
          <button class="secondary" @click="changeQuantity(item.id, -1)">−</button>
          <span>{{ item.quantity }}</span>
          <button class="secondary" @click="changeQuantity(item.id, 1)">+</button>
        </div>
        <strong>₹{{ item.price * item.quantity }}</strong>
        <button class="danger" @click="removeItem(item.id)">Remove</button>
      </div>

      <div v-if="cart.length > 0" class="checkout">
        <label>
          Customer name
          <input v-model.trim="customerName" placeholder="Enter your name" />
        </label>
        <div class="total">
          <span>Total ({{ totalItems }} items)</span>
          <strong>₹{{ totalPrice }}</strong>
        </div>
        <div class="checkout-actions">
          <button @click="placeOrder" :disabled="placingOrder">
            {{ placingOrder ? 'Placing order...' : 'Place Order' }}
          </button>
          <button class="secondary" @click="clearCart">Clear Cart</button>
        </div>
      </div>
    </section>

    <section class="panel">
      <div class="section-title">
        <div>
          <h2>Saved Orders</h2>
          <p class="muted">Orders placed here are stored in MongoDB.</p>
        </div>
        <button class="secondary" @click="loadOrders">Refresh</button>
      </div>

      <p v-if="orders.length === 0" class="muted">No orders yet.</p>
      <article v-for="order in orders" :key="order.id" class="order-card">
        <div class="section-title">
          <strong>{{ order.customerName }}</strong>
          <strong>₹{{ order.total }}</strong>
        </div>
        <p class="muted">Order ID: {{ order.id }}</p>
        <p v-for="item in order.items" :key="item.productId">
          {{ item.name }} × {{ item.quantity }}
        </p>
        <small class="muted">{{ formatDate(order.createdAt) }}</small>
      </article>
    </section>
  </main>
</template>

<script setup>
import { computed, onMounted, ref } from 'vue'

const PRODUCTS_URL = 'http://localhost:8080/api/products'
const ORDERS_URL = 'http://localhost:8080/api/orders'

const products = ref([])
const cart = ref([])
const orders = ref([])
const customerName = ref('')
const loadingProducts = ref(false)
const placingOrder = ref(false)
const message = ref('')
const isError = ref(false)

const totalItems = computed(() =>
  cart.value.reduce((sum, item) => sum + item.quantity, 0)
)

const totalPrice = computed(() =>
  cart.value.reduce((sum, item) => sum + item.price * item.quantity, 0)
)

function notify(text, error = false) {
  message.value = text
  isError.value = error
}

async function loadProducts() {
  loadingProducts.value = true
  try {
    const response = await fetch(PRODUCTS_URL)
    if (!response.ok) throw new Error('Could not load products')
    products.value = await response.json()
    notify('')
  } catch (error) {
    notify('Cannot connect to backend. Start MongoDB and Spring Boot first.', true)
  } finally {
    loadingProducts.value = false
  }
}

function addToCart(product) {
  const item = cart.value.find(i => i.id === product.id)
  if (item) {
    item.quantity++
  } else {
    cart.value.push({ ...product, quantity: 1 })
  }
  notify(`${product.name} added to cart.`)
}

function changeQuantity(id, amount) {
  const item = cart.value.find(i => i.id === id)
  if (!item) return

  item.quantity += amount
  if (item.quantity <= 0) removeItem(id)
}

function removeItem(id) {
  cart.value = cart.value.filter(item => item.id !== id)
}

function clearCart() {
  cart.value = []
}

async function placeOrder() {
  if (!customerName.value) {
    notify('Please enter your name before placing the order.', true)
    return
  }
  if (cart.value.length === 0) {
    notify('Your cart is empty.', true)
    return
  }

  placingOrder.value = true
  try {
    const payload = {
      customerName: customerName.value,
      items: cart.value.map(item => ({
        productId: item.id,
        quantity: item.quantity
      }))
    }

    const response = await fetch(ORDERS_URL, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(payload)
    })

    if (!response.ok) {
      const body = await response.text()
      throw new Error(body || 'Could not place order')
    }

    notify('Order placed successfully!')
    clearCart()
    await loadOrders()
  } catch (error) {
    notify('Could not place order. Check that the backend and MongoDB are running.', true)
  } finally {
    placingOrder.value = false
  }
}

async function loadOrders() {
  try {
    const response = await fetch(ORDERS_URL)
    if (!response.ok) throw new Error('Could not load orders')
    orders.value = await response.json()
  } catch (error) {
    // Product loading shows the main backend connection error if the API is offline.
  }
}

function formatDate(value) {
  if (!value) return ''
  return new Date(value).toLocaleString()
}

onMounted(() => {
  loadProducts()
  loadOrders()
})
</script>