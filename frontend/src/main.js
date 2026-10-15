import { createApp } from 'vue'
import { createPinia } from 'pinia'
import App from './App.vue'
import router from './router'

const app = createApp(App)

// Pinia stores shared data; Router handles page navigation.
app.use(createPinia())
app.use(router)
app.mount('#app')
