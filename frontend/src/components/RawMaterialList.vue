<template>
  <v-card elevation="2" rounded="xl" class="pa-4">

    <v-table>
      <thead>
        <tr>
          <th>ID</th>
          <th>Code</th>
          <th>Name</th>
          <th>Stock Quantity</th>
          <th>Unit</th>
          <th>Actions</th>
        </tr>
      </thead>

      <tbody>
        <tr v-for="rm in rawMaterials" :key="rm.id">


          <td>{{ rm.id }}</td>

          <td>
            <template v-if="editingId === rm.id">
              <v-text-field
                v-model="rm.code"
                density="compact"
                hide-details
              />
            </template>
            <template v-else>
              {{ rm.code }}
            </template>
          </td>

          <!-- Name -->
          <td>
            <template v-if="editingId === rm.id">
              <v-text-field
                v-model="rm.name"
                density="compact"
                hide-details
              />
            </template>
            <template v-else>
              {{ rm.name }}
            </template>
          </td>

          <td>
            <template v-if="editingId === rm.id">
              <v-text-field
                v-model.number="rm.stockQuantity"
                type="number"
                density="compact"
                hide-details
              />
            </template>
            <template v-else>
              {{ rm.stockQuantity }}
            </template>
          </td>

          <td>
            <template v-if="editingId === rm.id">
              <v-text-field
                v-model="rm.unit"
                density="compact"
                hide-details
              />
            </template>
            <template v-else>
              {{ rm.unit }}
            </template>
          </td>

          <!-- Actions -->
          <td>
            <v-btn
              size="small"
              color="primary"
              v-if="editingId !== rm.id"
              @click="editingId = rm.id"
            >
              Edit
            </v-btn>

            <v-btn
              size="small"
              color="success"
              v-else
              @click="updateRawMaterial(rm)"
            >
              Save
            </v-btn>

            <v-btn
              size="small"
              color="error"
              class="ml-2"
              @click="deleteRawMaterial(rm.id)"
            >
              Delete
            </v-btn>
          </td>

        </tr>
      </tbody>
    </v-table>

  </v-card>
</template>

<script>
import RawMaterialService from '@/services/RawMaterialService'

export default {
  name: 'RawMaterialList',

  data() {
    return {
      rawMaterials: [],
      editingId: null
    }
  },

  mounted() {
    this.loadRawMaterials()
  },

  methods: {

    loadRawMaterials() {
      RawMaterialService.getAll()
        .then(res => {
          this.rawMaterials = res.data
        })
    },

    updateRawMaterial(rawMaterial) {
      RawMaterialService.update(rawMaterial.id, rawMaterial)
        .then(() => {
          this.editingId = null
          this.loadRawMaterials()
        })
        .catch(error => {
          console.error("Update error:", error)
        })
    },

    deleteRawMaterial(id) {
      RawMaterialService.delete(id)
        .then(() => {
          this.loadRawMaterials()
        })
        .catch(error => {
          console.error("Delete error:", error)
        })
    }

  }
}
</script>