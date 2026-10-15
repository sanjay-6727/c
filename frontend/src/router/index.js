import { createRouter, createWebHistory } from 'vue-router'
import StudentsView from '../views/StudentsView.vue'
import AboutView from '../views/AboutView.vue'

// Each route connects a URL to a page component.
const routes = [
  { path: '/', redirect: '/students' },
  { path: '/students', component: StudentsView },
  { path: '/about', component: AboutView }
]

export default createRouter({
  history: createWebHistory(),
  routes
})
