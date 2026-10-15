<script setup>
import {ref,watch} from 'vue'
const props=defineProps({departments:{type:Array,default:()=>[]},initialValue:{type:Object,default:()=>({name:'',email:'',year:1,departmentId:''})},editingId:{type:String,default:''}})
const emit=defineEmits(['save','cancel'])
const name=ref(''),email=ref(''),year=ref(1),departmentId=ref('')
watch(()=>props.initialValue,v=>{name.value=v?.name||'';email.value=v?.email||'';year.value=v?.year||1;departmentId.value=v?.departmentId||''},{immediate:true,deep:true})
function submit(){if(name.value.trim()&&email.value.trim()&&departmentId.value)emit('save',{name:name.value.trim(),email:email.value.trim(),year:Number(year.value),departmentId:departmentId.value})}
</script>
<template><form @submit.prevent="submit" class="form"><label>Name<input v-model="name" required placeholder="Arun"></label><label>Email<input v-model="email" type="email" required></label><label>Year<select v-model.number="year"><option v-for="n in 5" :key="n" :value="n">{{n}}</option></select></label><label>Department<select v-model="departmentId" required><option disabled value="">Choose department</option><option v-for="d in departments" :key="d.id" :value="d.id">{{d.name}} ({{d.code}})</option></select></label><div><button>{{editingId?'Update':'Add student'}}</button><button v-if="editingId" type="button" class="muted" @click="emit('cancel')">Cancel</button></div></form></template>
