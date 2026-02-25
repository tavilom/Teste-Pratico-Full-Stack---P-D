import api from './api'

export default {
  optimizeProduction() {
    return api.get('/production/optimize')
  }
}