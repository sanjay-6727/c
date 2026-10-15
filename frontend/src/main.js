import { createApp } from 'vue'
import { createPinia } from 'pinia'
import App from './App.vue'
import router from './router'

const app = createApp(App)

// Pinia must be installed before using stores.
app.use(createPinia())

// Router enables URL-based page navigation.
app.use(router)

app.mount('#app')
