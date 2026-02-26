<template>
  <v-app>

    <v-app-bar flat color="primary">
      
      <v-toolbar-title class="font-weight-bold">
        {{ $t('app.title') }}
      </v-toolbar-title>

      <v-spacer />

      <v-select
        v-model="selectedLanguage"
        :items="languages"
        item-title="label"
        item-value="value"
        density="compact"
        variant="outlined"
        hide-details
        style="max-width: 160px"
        @update:modelValue="changeLanguage"
      />

    </v-app-bar>

    <v-main>
      <v-container class="fill-height d-flex align-center justify-center">
        <router-view />
      </v-container>
    </v-main>

  </v-app>
</template>

<script setup>
import { ref } from 'vue'
import { useI18n } from 'vue-i18n'

const { locale } = useI18n()

const languages = [
  { label: 'Português', value: 'pt' },
  { label: 'English', value: 'en' }
]

const selectedLanguage = ref(locale.value)

function changeLanguage(lang) {
  locale.value = lang
}
</script>