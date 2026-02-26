import axios from 'axios'

const API_URL = 'http://localhost:8080/api/products'

export default {
  getAll() {
    return axios.get(API_URL)
  },

  create(product) {
    return axios.post(API_URL, product)
  },

  update(id, product) {
    return axios.put(`${API_URL}/${id}`, product)
  },

  delete(id) {
    return axios.delete(`${API_URL}/${id}`)
  }
}