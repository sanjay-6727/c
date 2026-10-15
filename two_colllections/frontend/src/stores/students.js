import {defineStore} from 'pinia'
import axios from 'axios'
const API='http://localhost:8080/api/students'
export const useStudents=defineStore('students',{state:()=>({items:[],error:''}),actions:{
 async load(){try{this.items=(await axios.get(API)).data;this.error=''}catch(e){this.error='Cannot load students. Check backend and MongoDB.'}},
 async save(payload,id){if(id) await axios.put(`${API}/${id}`,payload);else await axios.post(API,payload);await this.load()},
 async remove(id){await axios.delete(`${API}/${id}`);await this.load()}
}})
