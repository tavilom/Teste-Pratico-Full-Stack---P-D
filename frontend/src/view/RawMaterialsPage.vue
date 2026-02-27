<template>
  <v-container>

    <v-row class="mb-4">
      <v-col class="d-flex justify-end">

        <v-btn variant="text" @click="$router.push('/')">
          {{ $t('backHome') }}
        </v-btn>

        <v-btn color="primary" :to="{ path: '/raw-materials/create' }">
          {{ $t('NewRawMaterial') }}
        </v-btn>
      </v-col>
    </v-row>

    <RawMaterialList :rawMaterials="rawMaterials" @deleted="loadRawMaterials" />

  </v-container>
</template>

<script>
import RawMaterialList from '@/components/RawMaterialList.vue';
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
  },

  handleCreated() {
    this.$router.push('/')
  }
}
</script>