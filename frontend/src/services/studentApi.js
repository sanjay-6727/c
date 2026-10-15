// This file contains HTTP requests. Components do not need to know fetch details.
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
    throw new Error(`API error: ${response.status}. Check that Spring Boot and MongoDB are running.`)
  }

  if (response.status === 204) return null
  return response.json()
}

// GET = read all records.
export const getStudents = () => request(API)

// POST = create a record. JSON.stringify turns the object into JSON text.
export const addStudent = (student) => request(API, {
  method: 'POST',
  body: JSON.stringify(student)
})

// PUT = update the record with this ID.
export const updateStudent = (id, student) => request(`${API}/${id}`, {
  method: 'PUT',
  body: JSON.stringify(student)
})

// DELETE = remove the record with this ID.
export const deleteStudent = (id) => request(`${API}/${id}`, {
  method: 'DELETE'
})
