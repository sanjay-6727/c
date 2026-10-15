<script setup>
import { onMounted, ref } from 'vue'
import { useStudentStore } from '../stores/studentStore'
import StudentForm from '../components/StudentForm.vue'

const store = useStudentStore()
const selectedStudent = ref(null)

onMounted(() => {
  store.loadStudents()
})

// The child emits "save"; this parent receives the data and calls Pinia.
async function saveFromForm(studentData) {
  const id = selectedStudent.value?.id || ''
  await store.saveStudent(studentData, id)
  selectedStudent.value = null
}

// The child emits "cancel"; this parent clears the selected record.
function cancelForm() {
  selectedStudent.value = null
}
</script>

<template>
  <section>
    <div class="panel">
      <h2>Students ({{ store.studentCount }})</h2>
      <p v-if="store.loading">Loading students...</p>
      <p v-if="store.error" class="error">{{ store.error }}</p>
      <button @click="store.loadStudents()">Refresh</button>
    </div>

    <!-- PROP: :student sends selectedStudent to the child.
         EMITS: @save and @cancel listen to events sent by the child. -->
    <StudentForm
      :student="selectedStudent"
      @save="saveFromForm"
      @cancel="cancelForm"
    />

    <div class="panel">
      <h2>Student list</h2>
      <p v-if="store.students.length === 0">No students yet.</p>
      <table v-else>
        <thead>
          <tr><th>Name</th><th>Department</th><th>Year</th><th>Actions</th></tr>
        </thead>
        <tbody>
          <tr v-for="student in store.students" :key="student.id">
            <td>{{ student.name }}</td>
            <td>{{ student.department }}</td>
            <td>{{ student.year }}</td>
            <td>
              <button @click="selectedStudent = { ...student }">Edit</button>
              <button @click="store.removeStudent(student.id)">Delete</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </section>
</template>
