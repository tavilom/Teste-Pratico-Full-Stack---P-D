<template>
  <v-container>
    <v-row justify="center" align="center" class="text-center">

      <v-col cols="12" md="4">
        <v-card
          elevation="3"
          rounded="xl"
          class="pa-6 hover-card"
          @click="$router.push('/products')"
        >
          <v-icon size="40" color="primary">mdi-package-variant</v-icon>
          <h2 class="mt-4">{{ $t('home.Products') }}</h2>
          <p>{{ $t('home.Manage and register products') }}</p>
        </v-card>
      </v-col>

      <v-col cols="12" md="4">
        <v-card
          elevation="3"
          rounded="xl"
          class="pa-6 hover-card"
          @click="$router.push('/raw-materials')"
        >
          <v-icon size="40" color="secondary">mdi-cube-outline</v-icon>
          <h2 class="mt-4">{{ $t('home.Raw Materials') }}</h2>
          <p>{{ $t('home.Control stock and materials') }}</p>
        </v-card>
      </v-col>

      <v-col cols="12" md="4">
        <v-card
          elevation="3"
          rounded="xl"
          class="pa-6 hover-card"
          @click="$router.push('/production')"
        >
          <v-icon size="40" color="success">mdi-chart-line</v-icon>
          <h2 class="mt-4">{{ $t('home.Production') }}</h2>
          <p>{{ $t('home.Optimize and maximize profit') }}</p>
        </v-card>
      </v-col>

    </v-row>
  </v-container>
</template>

<style scoped>
.hover-card {
  cursor: pointer;
  transition: all 0.3s ease;
}

.hover-card:hover {
  transform: translateY(-6px);
}
</style>