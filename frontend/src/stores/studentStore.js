import { defineStore } from 'pinia'
import * as api from '../services/studentApi'

// Pinia store = shared state + functions that change/load that state.
// Use this store from any component with: const store = useStudentStore()
export const useStudentStore = defineStore('students', {
  state: () => ({
    students: [],
    loading: false,
    error: ''
  }),

  // Getters are computed values based on state.
  getters: {
    studentCount: (state) => state.students.length
  },

  // Actions are functions that can call the API and update state.
  actions: {
    async loadStudents() {
      this.loading = true
      this.error = ''
      try {
        this.students = await api.getStudents()
      } catch (e) {
        this.error = e.message
      } finally {
        this.loading = false
      }
    },

    async saveStudent(student, id = '') {
      this.error = ''
      try {
        if (id) {
          await api.updateStudent(id, student)
        } else {
          await api.addStudent(student)
        }
        await this.loadStudents()
      } catch (e) {
        this.error = e.message
      }
    },

    async removeStudent(id) {
      this.error = ''
      try {
        await api.deleteStudent(id)
        await this.loadStudents()
      } catch (e) {
        this.error = e.message
      }
    }
  }
})
