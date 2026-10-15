import { defineStore } from 'pinia'
import * as api from '../services/studentApi'

// A Pinia store is shared state plus functions to load/change it.
export const useStudentStore = defineStore('students', {
  // STATE: the shared data used by all components.
  state: () => ({
    students: [],
    loading: false,
    error: ''
  }),

  // GETTER: calculated data derived from state.
  getters: {
    studentCount: (state) => state.students.length
  },

  // ACTIONS: functions that perform work, including API calls.
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
