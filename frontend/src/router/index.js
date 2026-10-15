import { createRouter, createWebHistory } from 'vue-router'
import StudentsView from '../views/StudentsView.vue'
import AboutView from '../views/AboutView.vue'

// Each route maps a URL to a page component.
const routes = [
  { path: '/', redirect: '/students' },
  { path: '/students', component: StudentsView },
  { path: '/about', component: AboutView }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
