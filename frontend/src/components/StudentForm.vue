<script setup>
import { reactive, watch } from 'vue'

// PROPS: values received from the parent component.
const props = defineProps({
  student: {
    type: Object,
    default: null
  }
})

// EMITS: events sent from this child back to the parent.
const emit = defineEmits(['save', 'cancel'])

const form = reactive({
  name: '',
  department: '',
  year: 1
})

// When parent selects a student to edit, copy it into the form.
watch(() => props.student, (student) => {
  form.name = student?.name || ''
  form.department = student?.department || ''
  form.year = student?.year || 1
}, { immediate: true })

function submit() {
  // Send a copy to parent; parent will call the Pinia store.
  emit('save', {
    name: form.name,
    department: form.department,
    year: Number(form.year)
  })
}

function cancel() {
  emit('cancel')
}
</script>

<template>
  <form class="panel" @submit.prevent="submit">
    <h2>{{ student ? 'Edit student' : 'Add student' }}</h2>
    <input v-model.trim="form.name" placeholder="Name" required>
    <input v-model.trim="form.department" placeholder="Department" required>
    <input v-model.number="form.year" type="number" min="1" placeholder="Year" required>
    <button type="submit">{{ student ? 'Update' : 'Add' }}</button>
    <button type="button" @click="cancel">Clear / Cancel</button>
  </form>
</template>
