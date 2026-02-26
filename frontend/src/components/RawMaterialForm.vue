<template>
  <v-card elevation="2" rounded="xl" class="pa-6">
    <v-form @submit.prevent="submitForm">

      <v-text-field
        v-model="form.code"
        label="Code"
        required
      />

      <v-text-field
        v-model="form.name"
        label="Name"
        required
      />

      <v-text-field
        v-model.number="form.stockQuantity"
        label="Stock Quantity"
        type="number"
        required
      />

      <v-text-field
        v-model="form.unit"
        label="Unit"
        required
      />

      <v-btn
        type="submit"
        color="primary"
        class="mt-4"
      >
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