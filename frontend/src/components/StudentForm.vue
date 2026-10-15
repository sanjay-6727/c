<script setup>
import { reactive, watch } from 'vue'

// PROPS carry data from the parent page down into this child form.
const props = defineProps({
  student: { type: Object, default: null }
})

// EMITS let this child notify the parent that save/cancel happened.
const emit = defineEmits(['save', 'cancel'])

const form = reactive({ name: '', department: '', year: 1 })

// When parent chooses Edit, copy that student's fields into the form.
watch(() => props.student, (student) => {
  form.name = student?.name || ''
  form.department = student?.department || ''
  form.year = student?.year || 1
}, { immediate: true })

function submit() {
  // Emit the form data upward; the parent decides how to save it.
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
