import { createRouter, createWebHistory } from 'vue-router'

import HomePage from '@/view/HomePage.vue'

import ProductsPage from '@/view/ProductsPage.vue'
import ProductCreatePage from '@/view/ProductCreatePage.vue'

import RawMaterialsPage from '@/view/RawMaterialsPage.vue'
import RawMaterialCreatePage from '@/view/RawMaterialCreatePage.vue'

import ProductionPage from '@/view/ProductionPage.vue'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: HomePage
  },
  {
    path: '/products',
    name: 'Products',
    component: ProductsPage
  },
  {
    path: '/products/create',
    name: 'ProductCreate',
    component: ProductCreatePage
  },
  {
    path: '/raw-materials',
    name: 'RawMaterials',
    component: RawMaterialsPage
  },
  {
    path: '/raw-materials/create',
    name: 'RawMaterialCreate',
    component: RawMaterialCreatePage
  },
  {
    path: '/production',
    name: 'Production',
    component: ProductionPage
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router