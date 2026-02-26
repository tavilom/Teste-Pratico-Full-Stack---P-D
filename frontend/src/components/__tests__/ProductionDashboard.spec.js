import { describe, it, expect, vi } from 'vitest'
import flushPromises from 'flush-promises'
import { mount } from '@vue/test-utils'
import ProductionDashboard from '../ProductionDashboard.vue'
import productionService from '@/services/production'

vi.mock('@/services/production', () => ({
  default: {
    optimizeProduction: vi.fn()
  }
}))

describe('ProductionDashboard', () => {

  it('calls optimizeProduction when optimize method is executed', async () => {

    productionService.optimizeProduction.mockResolvedValue({
      data: {
        producedProducts: [],
        totalValue: 0
      }
    })

    const wrapper = mount(ProductionDashboard, {
      global: {
        stubs: {
          'v-btn': true,
          'v-row': true,
          'v-col': true,
          'v-card': true,
          'v-card-title': true,
          'v-card-text': true,
          'v-table': true
        }
      }
    })

    await wrapper.vm.optimize()

    await flushPromises()

    expect(productionService.optimizeProduction).toHaveBeenCalledTimes(1)
  })

})