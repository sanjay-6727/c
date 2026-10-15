<script setup>
import {onMounted,ref} from 'vue'
import {useStudents} from '../stores/students'
import {useDepartments} from '../stores/departments'
import StudentForm from '../components/StudentForm.vue'
const students=useStudents(),departments=useDepartments(),editingId=ref(''),initialValue=ref({name:'',email:'',year:1,departmentId:''}),message=ref('')
onMounted(async()=>{await Promise.all([students.load(),departments.load()])})
function deptName(id){return departments.items.find(d=>d.id===id)?.name||'Missing department'}
function edit(s){editingId.value=s.id;initialValue.value={name:s.name,email:s.email,year:s.year,departmentId:s.departmentId}}
function reset(){editingId.value='';initialValue.value={name:'',email:'',year:1,departmentId:''}}
async function save(p){try{await students.save(p,editingId.value);message.value='Saved';reset()}catch(e){message.value='Save failed. Create/select a department first.'}}
async function remove(id){if(confirm('Delete this student?'))await students.remove(id)}
</script>
<template><section class="card"><h2>Students collection</h2><p>Each student stores a departmentId reference.</p><p v-if="!departments.items.length" class="warning">Create a department first on the Departments page.</p><StudentForm :departments="departments.items" :initial-value="initialValue" :editing-id="editingId" @save="save" @cancel="reset"/><p>{{message}} {{students.error}} {{departments.error}}</p></section><section class="card"><h2>All students ({{students.items.length}})</h2><p v-if="!students.items.length">No students yet.</p><table v-else><thead><tr><th>Name</th><th>Email</th><th>Year</th><th>Department</th><th>Actions</th></tr></thead><tbody><tr v-for="s in students.items" :key="s.id"><td>{{s.name}}</td><td>{{s.email}}</td><td>{{s.year}}</td><td>{{deptName(s.departmentId)}}</td><td><button class="muted" @click="edit(s)">Edit</button> <button class="danger" @click="remove(s.id)">Delete</button></td></tr></tbody></table></section></template>
