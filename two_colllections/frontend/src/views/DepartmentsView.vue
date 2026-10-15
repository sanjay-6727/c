<script setup>
import {onMounted,ref} from 'vue'
import {useDepartments} from '../stores/departments'
import DepartmentForm from '../components/DepartmentForm.vue'
const store=useDepartments(),editingId=ref(''),initialValue=ref({name:'',code:''}),message=ref('')
onMounted(()=>store.load())
function edit(d){editingId.value=d.id;initialValue.value={name:d.name,code:d.code}}
function reset(){editingId.value='';initialValue.value={name:'',code:''}}
async function save(p){try{await store.save(p,editingId.value);message.value='Saved';reset()}catch(e){message.value='Save failed'}}
async function remove(id){if(confirm('Delete this department?')){try{await store.remove(id)}catch(e){message.value='Delete failed'}}}
</script>
<template><section class="card"><h2>Departments collection</h2><p>Create departments first so students can be assigned.</p><DepartmentForm :initial-value="initialValue" :editing-id="editingId" @save="save" @cancel="reset"/><p>{{message}} {{store.error}}</p></section><section class="card"><h2>All departments ({{store.items.length}})</h2><p v-if="!store.items.length">No departments yet.</p><table v-else><thead><tr><th>Name</th><th>Code</th><th>Actions</th></tr></thead><tbody><tr v-for="d in store.items" :key="d.id"><td>{{d.name}}</td><td>{{d.code}}</td><td><button class="muted" @click="edit(d)">Edit</button> <button class="danger" @click="remove(d.id)">Delete</button></td></tr></tbody></table></section></template>
