<template>
  <div>

    <v-row class="mb-6">
      <v-col>
        <h1 class="text-h4 font-weight-bold">
          Production Optimization
        </h1>
        <p class="text-subtitle-1">
          Analyze current stock and calculate the most profitable production plan.
        </p>
      </v-col>

      <v-col class="d-flex justify-end align-center">
        <v-btn
          color="primary"
          size="large"
          @click="optimize"
          :loading="loading"
        >
          Calculate Production Plan
        </v-btn>
      </v-col>
    </v-row>

    <!-- KPI Cards -->
    <v-row v-if="result" class="mb-6">

      <v-col cols="12" md="4">
        <v-card elevation="3">
          <v-card-title>Total Products</v-card-title>
          <v-card-text class="text-h5">
            {{ result.producedProducts.length }}
          </v-card-text>
        </v-card>
      </v-col>

      <v-col cols="12" md="4">
        <v-card elevation="3">
          <v-card-title>Total Units Produced</v-card-title>
          <v-card-text class="text-h5">
            {{ totalUnits }}
          </v-card-text>
        </v-card>
      </v-col>

      <v-col cols="12" md="4">
        <v-card elevation="3">
          <v-card-title>Total Profit</v-card-title>
          <v-card-text class="text-h5 text-success">
            {{ formatCurrency(result.totalValue) }}
          </v-card-text>
        </v-card>
      </v-col>

    </v-row>

    <v-card v-if="result" elevation="3">

      <v-card-title>Production Plan</v-card-title>

      <v-table v-if="result.producedProducts.length > 0">
        <thead>
          <tr>
            <th>Product</th>
            <th>Quantity</th>
            <th>Total Value</th>
          </tr>
        </thead>

        <tbody>
          <tr v-for="item in result.producedProducts"
              :key="item.productId">

            <td>{{ item.productName }}</td>
            <td>{{ item.producedQuantity }}</td>
            <td class="text-success">
              {{ formatCurrency(item.totalProductValue) }}
            </td>
          </tr>
        </tbody>
      </v-table>

      <v-card-text v-else>
        No production possible with current stock.
      </v-card-text>

    </v-card>

  </div>
</template>

<script>
import productionService from '@/services/production'

export default {
  data() {
    return {
      result: null,
      loading: false
    }
  },

  computed: {
    totalUnits() {
      if (!this.result) return 0
      return this.result.producedProducts.reduce(
        (sum, item) => sum + item.producedQuantity,
        0
      )
    }
  },

  methods: {
    optimize() {
      this.loading = true

      productionService.optimizeProduction()
        .then(res => {
          this.result = res.data
        })
        .finally(() => {
          this.loading = false
        })
    },

    formatCurrency(value) {
      if (!value) return '$0.00'
      return new Intl.NumberFormat('en-US', {
        style: 'currency',
        currency: 'USD'
      }).format(value)
    }
  }
}
</script>