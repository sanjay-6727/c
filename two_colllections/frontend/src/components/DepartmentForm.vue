<script setup>
import {ref,watch} from 'vue'
const props=defineProps({initialValue:{type:Object,default:()=>({name:'',code:''})},editingId:{type:String,default:''}})
const emit=defineEmits(['save','cancel'])
const name=ref(''),code=ref('')
watch(()=>props.initialValue,v=>{name.value=v?.name||'';code.value=v?.code||''},{immediate:true,deep:true})
function submit(){if(name.value.trim()&&code.value.trim())emit('save',{name:name.value.trim(),code:code.value.trim()})}
</script>
<template><form @submit.prevent="submit" class="form"><label>Name<input v-model="name" required placeholder="Computer Science"></label><label>Code<input v-model="code" required placeholder="CSE"></label><div><button>{{editingId?'Update':'Add department'}}</button><button v-if="editingId" type="button" class="muted" @click="emit('cancel')">Cancel</button></div></form></template>
