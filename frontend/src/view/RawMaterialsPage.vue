<template>
  <v-container>

    <v-row class="mb-4">
      <v-col class="d-flex justify-end">
        <v-btn
          color="primary"
          :to="{ path: '/raw-materials/create' }"
        >
          New Raw Material
        </v-btn>
      </v-col>
    </v-row>

    <RawMaterialList
      :rawMaterials="rawMaterials"
      @deleted="loadRawMaterials"
    />

  </v-container>
</template>

<script>
import RawMaterialList from '@/components/RawmaterialList.vue';
import RawMaterialService from '@/services/RawMaterialService';

export default {
  components: { RawMaterialList },

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
    }
  }
}
</script>