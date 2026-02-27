<template>
  <v-card elevation="3" rounded="xl" class="pa-6">
    <v-card-title>{{ $t('RawMaterialForm.Title') }}</v-card-title>
    <v-form @submit.prevent="submitForm">

      <v-text-field v-model="form.code" :label="$t('RawMaterialForm.Code')" required />

      <v-text-field v-model="form.name" :label="$t('RawMaterialForm.Name')" required />

      <v-text-field v-model.number="form.stockQuantity" :label="$t('RawMaterialForm.Stock')" type="number" required />

      <v-text-field v-model="form.unit" :label="$t('RawMaterialForm.Unit')" required />

      <v-btn type="submit" color="primary" class="mt-4">
        {{ $t('buttons.save') }}
      </v-btn>

    </v-form>
  </v-card>
</template>

<script>
export default {
  props: {
    modelValue: Object
  },

  data() {
    return {
      form: { ...this.modelValue }
    }
  },

  methods: {
    submitForm() {
      this.$emit('submit', this.form)
    }
  }
}
</script>