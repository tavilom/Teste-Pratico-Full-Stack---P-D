<template>
  <v-card elevation="3">
    <v-card-title>Raw Materials</v-card-title>

    <v-table>
      <thead>
        <tr>
          <th>ID</th>
          <th>Code</th>
          <th>Name</th>
          <th>Stock</th>
          <th>Unit</th>
          <th>Action</th>
        </tr>
      </thead>

      <tbody>
        <tr v-for="rm in rawMaterials" :key="rm.id">
          <td>{{ rm.id }}</td>
          <td>{{ rm.code }}</td>
          <td>{{ rm.name }}</td>
          <td>{{ rm.stockQuantity }}</td>
          <td>{{ rm.unit }}</td>
          <td>
            <v-btn
              size="small"
              color="error"
              @click="deleteRawMaterial(rm.id)"
            >
              Delete
            </v-btn>
          </td>
        </tr>
      </tbody>
    </v-table>

    <v-card-text v-if="rawMaterials.length === 0">
      No raw materials found.
    </v-card-text>

  </v-card>
</template>

<script>
import RawMaterialService from '@/services/RawMaterialService'

export default {
  props: ['rawMaterials'],

  methods: {
    deleteRawMaterial(id) {
      RawMaterialService.delete(id)
        .then(() => this.$emit('deleted'))
    }
  }
}
</script>