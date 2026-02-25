<template>
  <v-container>

    <v-row class="mb-4">
      <v-col>
        <v-btn
          variant="text"
          @click="$router.push('/raw-materials')"
        >
          ← Back to Raw Materials
        </v-btn>
      </v-col>
    </v-row>

    <RawMaterialForm @created="handleCreated" />

  </v-container>
</template>

<script>
import RawMaterialForm from '@/components/RawMaterialForm.vue'

export default {
  components: { RawMaterialForm },

  methods: {
    handleCreated() {
      this.$router.push('/raw-materials')
    }
  }
}
</script>