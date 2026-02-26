import api from './api'  

export default {

  getAll() {
    return api.get('/raw-materials')
  },

  getById(id) {
    return api.get(`/raw-materials/${id}`)
  },

  create(rawMaterial) {
    return api.post('/raw-materials', rawMaterial)
  },

  update(id, rawMaterial) {
    return api.put(`/raw-materials/${id}`, rawMaterial)
  },

  delete(id) {
    return api.delete(`/raw-materials/${id}`)
  }

}