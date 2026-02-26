<template>
  <v-container>

    <v-row class="mb-4">
      <v-col class="d-flex justify-end">

        <v-btn variant="text" @click="$router.push('/')">
          ← Back to Home
        </v-btn>

        <v-btn color="primary" :to="{ path: '/products/create' }">
          New Product
        </v-btn>

      </v-col>
    </v-row>

    <ProductList :products="products" @product-updated="loadProducts" @product-deleted="loadProducts" />

  </v-container>
</template>

<script>
import ProductList from '@/components/ProductList.vue'
import ProductService from '@/services/ProductService'

export default {
  components: { ProductList },

  data() {
    return {
      products: []
    }
  },

  mounted() {
    this.loadProducts()
  },

  methods: {
    loadProducts() {
      ProductService.getAll()
        .then(res => this.products = res.data)
    }
  },

  handleCreated() {
    this.$router.push('/')
  }
}
</script>