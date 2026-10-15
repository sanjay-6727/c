import { createRouter, createWebHistory } from 'vue-router'
import StudentsView from '../views/StudentsView.vue'
import DepartmentsView from '../views/DepartmentsView.vue'
export default createRouter({history:createWebHistory(),routes:[
 {path:'/',redirect:'/students'},{path:'/students',component:StudentsView},{path:'/departments',component:DepartmentsView}
]})
