<template>
  <v-card elevation="3" rounded="xl" class="pa-6">
    <v-card-title>{{ $t('ProductForm.Title') }}</v-card-title>

    <v-card-text>
      <v-alert
        v-if="rawMaterials.length === 0"
        type="error"
        variant="tonal"
        class="mb-4"
      >
        {{ $t('ProductForm.warning') }}
      </v-alert>

      <v-form @submit.prevent="createProduct">

        <v-text-field :label="$t('ProductForm.Code')" v-model="newProduct.code" required />
        <v-text-field :label="$t('ProductForm.Name')" v-model="newProduct.name" required />
        <v-text-field :label="$t('ProductForm.Price')" type="number"
                      v-model.number="newProduct.price" required />

        <v-select
          :label="$t('ProductForm.rawMaterial')"
          :items="rawMaterials"
          item-title="name"
          item-value="id"
          v-model="composition.rawMaterialId"
          required
        />

        <v-text-field
          :label="$t('ProductForm.requiredQuantity')"
          type="number"
          v-model.number="composition.requiredQuantity"
          required
        />

        <v-btn type="submit"
               color="primary"
               class="mt-4"
               :disabled="rawMaterials.length === 0">
          {{ $t('buttons.save') }}
        </v-btn>

      </v-form>
    </v-card-text>
  </v-card>
</template>

<script>
import ProductService from '@/services/ProductService'

export default {
  props: ['rawMaterials'],

  data() {
    return {
      newProduct: {
        code: '',
        name: '',
        price: null,
        composition: []
      },

      composition: {
        rawMaterialId: '',
        requiredQuantity: null
      }
    }
  },

  methods: {
    createProduct() {
      this.newProduct.composition = [this.composition]

      ProductService.create(this.newProduct)
        .then(() => {
          this.$emit('product-created')
          this.resetForm()
        })
    },

    resetForm() {
      this.newProduct = {
        code: '',
        name: '',
        price: null,
        composition: []
      }

      this.composition = {
        rawMaterialId: '',
        requiredQuantity: null
      }
    }
  }
}
</script>