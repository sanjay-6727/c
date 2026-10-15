// Keep all fetch requests in one file so components stay simple.
const API = 'http://localhost:8080/students'

async function request(url, options = {}) {
  const response = await fetch(url, {
    ...options,
    headers: {
      'Content-Type': 'application/json',
      ...(options.headers || {})
    }
  })

  if (!response.ok) {
    throw new Error(`API error: ${response.status}. Check that Spring Boot is running.`)
  }

  // DELETE may return an empty response.
  if (response.status === 204) return null
  return response.json()
}

export const getStudents = () => request(API)

export const addStudent = (student) => request(API, {
  method: 'POST',
  body: JSON.stringify(student)
})

export const updateStudent = (id, student) => request(`${API}/${id}`, {
  method: 'PUT',
  body: JSON.stringify(student)
})

export const deleteStudent = (id) => request(`${API}/${id}`, {
  method: 'DELETE'
})
