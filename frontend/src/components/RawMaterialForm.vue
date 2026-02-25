<template>
  <v-card elevation="3">
    <v-card-title>Create Raw Material</v-card-title>

    <v-card-text>
      <v-form @submit.prevent="createRawMaterial">

        <v-text-field
          label="Code"
          v-model="rawMaterial.code"
          required
        />

        <v-text-field
          label="Name"
          v-model="rawMaterial.name"
          required
        />

        <v-text-field
          label="Stock Quantity"
          type="number"
          step="0.01"
          v-model.number="rawMaterial.stockQuantity"
          required
        />

        <v-text-field
          label="Unit (kg, m, L...)"
          v-model="rawMaterial.unit"
          required
        />

        <v-btn type="submit" color="primary" class="mt-4">
          Save
        </v-btn>

      </v-form>
    </v-card-text>
  </v-card>
</template>

<script>
import RawMaterialService from '@/services/RawMaterialService'

export default {
  data() {
    return {
      rawMaterial: {
        code: '',
        name: '',
        stockQuantity: null,
        unit: ''
      }
    }
  },

  methods: {
    createRawMaterial() {
      RawMaterialService.create(this.rawMaterial)
        .then(() => {
          this.$emit('created')
        })
    }
  }
}
</script>