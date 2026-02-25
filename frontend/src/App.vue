<template>
  <v-app>

    <v-app-bar flat color="primary">
      <v-toolbar-title class="font-weight-bold">
        Stock & Production System
      </v-toolbar-title>
    </v-app-bar>

    <v-main>
      <v-container class="fill-height d-flex align-center justify-center">
        <router-view />
      </v-container>
    </v-main>

  </v-app>
</template>

<script>
export default {
  name: 'App'
}
</script>