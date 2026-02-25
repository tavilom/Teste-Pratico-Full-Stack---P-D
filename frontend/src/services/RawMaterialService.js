import axios from 'axios'

const API_URL = 'http://localhost:8080/api/raw-materials'

export default {
  getAll() {
    return axios.get(API_URL)
  },

  create(rawMaterial) {
    return axios.post(API_URL, rawMaterial)
  },

  delete(id) {
    return axios.delete(`${API_URL}/${id}`)
  }
}