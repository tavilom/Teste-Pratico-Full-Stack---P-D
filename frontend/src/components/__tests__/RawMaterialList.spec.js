import { describe, it, expect, vi, beforeEach } from 'vitest'
import { nextTick } from 'vue'
import flushPromises from 'flush-promises'
import { mountWithVuetify } from '@/test-utils'
import RawMaterialList from '@/components/RawMaterialList.vue'
import RawMaterialService from '@/services/RawMaterialService'

vi.mock('@/services/RawMaterialService')

describe('RawMaterialList', () => {

  const mockData = [
    {
      id: 1,
      code: 'RM01',
      name: 'Steel',
      stockQuantity: 100,
      unit: 'kg'
    }
  ]

  beforeEach(() => {
    RawMaterialService.getAll.mockResolvedValue({ data: mockData })
    RawMaterialService.update.mockResolvedValue({})
    RawMaterialService.delete.mockResolvedValue({})
  })

  it('renders raw materials list', async () => {
    const wrapper = mountWithVuetify(RawMaterialList)

    await flushPromises()
    await nextTick()

    expect(wrapper.html()).toContain('Steel')
  })

  it('switches to edit mode when Edit is clicked', async () => {
    const wrapper = mountWithVuetify(RawMaterialList)

    await flushPromises()
    await nextTick()

    const buttons = wrapper.findAllComponents({ name: 'VBtn' })
    
    await buttons[0].trigger('click')

    await nextTick()

    expect(wrapper.html()).toContain('Save')
  })

})