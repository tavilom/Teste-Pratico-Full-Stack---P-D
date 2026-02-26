<template>
  <v-card elevation="3">
    <v-card-title>Product List</v-card-title>

    <v-table>
      <thead>
        <tr>
          <th>ID</th>
          <th>Code</th>
          <th>Name</th>
          <th>Price</th>
          <th>Actions</th>
        </tr>
      </thead>

      <tbody>
        <tr v-for="product in products" :key="product.id">
          <td>{{ product.id }}</td>

          <td v-if="editingId !== product.id">
            {{ product.code }}
          </td>
          <td v-else>
            <v-text-field v-model="product.code" density="compact" />
          </td>

          <td v-if="editingId !== product.id">
            {{ product.name }}
          </td>
          <td v-else>
            <v-text-field v-model="product.name" density="compact" />
          </td>

          <td v-if="editingId !== product.id">
            {{ product.price }}
          </td>
          <td v-else>
            <v-text-field type="number"
                          v-model.number="product.price"
                          density="compact" />
          </td>

          <td>
            <v-btn size="small"
                   color="primary"
                   v-if="editingId !== product.id"
                   @click="editingId = product.id">
              {{ $t('buttons.edit') }}
            </v-btn>

            <v-btn size="small"
                   color="success"
                   v-else
                   @click="updateProduct(product)">
              {{ $t('buttons.save') }}
            </v-btn>

            <v-btn size="small"
                   color="error"
                   class="ml-2"
                   @click="deleteProduct(product.id)">
              {{ $t('buttons.delete') }}
            </v-btn>
          </td>
        </tr>
      </tbody>
    </v-table>

    <v-card-text v-if="products.length === 0">
      No products found.
    </v-card-text>

  </v-card>
</template>

<script>
import ProductService from '@/services/ProductService'

export default {
  props: ['products'],

  data() {
    return {
      editingId: null
    }
  },

  methods: {
    updateProduct(product) {
      ProductService.update(product.id, product)
        .then(() => {
          this.editingId = null
          this.$emit('product-updated')
        })
    },

    deleteProduct(id) {
      ProductService.delete(id)
        .then(() => this.$emit('product-deleted'))
    }
  }
}
</script>