<template>
  <v-container>
    <v-row class="mb-4">
      <v-col>
        <v-btn variant="text" @click="$router.push('/')">
          ← Back to Home
        </v-btn>
      </v-col>
    </v-row>
    <ProductionDashboard />
  </v-container>
</template>

<script>
import ProductionDashboard from '@/components/ProductionDashboard.vue'

export default {
  components: { ProductionDashboard },

  handleCreated() {
    this.$router.push('/')
  }
}
</script>