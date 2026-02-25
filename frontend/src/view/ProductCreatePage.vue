<template>
  <v-container>

    <v-row class="mb-4">
      <v-col>
        <v-btn
          variant="text"
          @click="$router.push('/products')"
        >
          ← Back to Products
        </v-btn>
      </v-col>
    </v-row>

    <ProductForm
      :rawMaterials="rawMaterials"
      @product-created="handleCreated"
    />

  </v-container>
</template>

<script>
import ProductForm from '@/components/ProductForm.vue'
import RawMaterialService from '@/services/RawMaterialService'

export default {
  components: { ProductForm },

  data() {
    return {
      rawMaterials: []
    }
  },

  mounted() {
    this.loadRawMaterials()
  },

  methods: {
    loadRawMaterials() {
      RawMaterialService.getAll()
        .then(res => this.rawMaterials = res.data)
    },

    handleCreated() {
      this.$router.push('/products')
    }
  }
}
</script>