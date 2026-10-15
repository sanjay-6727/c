<script setup>
import { computed, onMounted, ref } from 'vue'

const API = 'http://localhost:8080/api'
const employees = ref([])
const leaves = ref([])
const employeeFilter = ref('')
const reasonFilter = ref('')
const statusFilter = ref('')
const loading = ref(false)
const error = ref('')

async function loadData() {
  loading.value = true
  error.value = ''
  try {
    const [employeeResponse, leaveResponse] = await Promise.all([
      fetch(`${API}/employees`),
      fetch(`${API}/leaves`)
    ])
    if (!employeeResponse.ok || !leaveResponse.ok) {
      throw new Error('Could not load data. Check that Spring Boot and MongoDB are running.')
    }
    employees.value = await employeeResponse.json()
    leaves.value = await leaveResponse.json()
  } catch (e) {
    error.value = e.message || 'Something went wrong.'
  } finally {
    loading.value = false
  }
}

const filteredLeaves = computed(() => {
  const reason = reasonFilter.value.trim().toLowerCase()
  return leaves.value.filter(leave => {
    const employeeMatches = !employeeFilter.value ||
      leave.employeeId === employeeFilter.value
    const reasonMatches = !reason ||
      (leave.reason || '').toLowerCase().includes(reason)
    const statusMatches = !statusFilter.value ||
      leave.status === statusFilter.value
    return employeeMatches && reasonMatches && statusMatches
  })
})

function employeeName(employeeId) {
  return employees.value.find(e => e.id === employeeId)?.name || 'Unknown employee'
}

onMounted(loadData)
</script>

<template>
  <main class="container">
    <header class="header">
      <div>
        <p class="eyebrow">SPRING BOOT + MONGODB + VUE</p>
        <h1>Employee Leave Manager</h1>
        <p class="subtitle">View leave records and filter by employee, reason, or status.</p>
      </div>
      <button class="refresh" @click="loadData">Refresh</button>
    </header>

    <p v-if="error" class="error">{{ error }}</p>
    <p v-if="loading">Loading data...</p>

    <section class="panel">
      <h2>Filters</h2>
      <div class="filters">
        <label>
          Employee
          <select v-model="employeeFilter">
            <option value="">All employees</option>
            <option v-for="employee in employees" :key="employee.id" :value="employee.id">
              {{ employee.name }}
            </option>
          </select>
        </label>

        <label>
          Reason contains
          <input v-model="reasonFilter" placeholder="e.g. personal, fever, family">
        </label>

        <label>
          Status
          <select v-model="statusFilter">
            <option value="">All statuses</option>
            <option value="PENDING">Pending</option>
            <option value="APPROVED">Approved</option>
            <option value="REJECTED">Rejected</option>
          </select>
        </label>

        <button class="secondary" @click="employeeFilter = ''; reasonFilter = ''; statusFilter = ''">
          Clear filters
        </button>
      </div>
    </section>

    <section class="stats">
      <article class="stat">
        <span>Employees</span>
        <strong>{{ employees.length }}</strong>
      </article>
      <article class="stat">
        <span>All leave records</span>
        <strong>{{ leaves.length }}</strong>
      </article>
      <article class="stat">
        <span>Filtered records</span>
        <strong>{{ filteredLeaves.length }}</strong>
      </article>
    </section>

    <section class="panel">
      <h2>Leave records</h2>
      <div class="table-wrap">
        <table>
          <thead>
            <tr>
              <th>Employee</th>
              <th>From</th>
              <th>To</th>
              <th>Reason</th>
              <th>Status</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="leave in filteredLeaves" :key="leave.id">
              <td>{{ employeeName(leave.employeeId) }}</td>
              <td>{{ leave.fromDate }}</td>
              <td>{{ leave.toDate }}</td>
              <td>{{ leave.reason }}</td>
              <td><span class="status" :class="(leave.status || '').toLowerCase()">{{ leave.status }}</span></td>
            </tr>
            <tr v-if="!filteredLeaves.length">
              <td colspan="5" class="empty">No leave records match these filters.</td>
            </tr>
          </tbody>
        </table>
      </div>
    </section>

    <section class="panel">
      <h2>Employees and leave balances</h2>
      <div class="table-wrap">
        <table>
          <thead>
            <tr>
              <th>Name</th>
              <th>Department</th>
              <th>Total leaves</th>
              <th>Leaves taken</th>
              <th>Remaining</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="employee in employees" :key="employee.id">
              <td>{{ employee.name }}</td>
              <td>{{ employee.department }}</td>
              <td>{{ employee.totalLeaves }}</td>
              <td>{{ employee.leavesTaken }}</td>
              <td>{{ employee.totalLeaves - employee.leavesTaken }}</td>
            </tr>
            <tr v-if="!employees.length">
              <td colspan="5" class="empty">No employees found.</td>
            </tr>
          </tbody>
        </table>
      </div>
    </section>
  </main>
</template>
