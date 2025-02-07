import { createRouter, createWebHistory } from 'vue-router'

import HomeView from './views/HomeView.vue'
import AllaStugorView from './views/AllaStugorView.vue'
import FAQView from './views/FAQView.vue'
import SupportView from './views/SupportView.vue'
import AdminView from './views/AdminView.vue'
import UserView from './views/UserView.vue'
import SingleItemView from './views/SingleItemView.vue'
import HyraSummeringView from './views/HyraSummeringView.vue'

const routes = [
  { path: '/', name: 'Fjällstugan', component: HomeView },
  { path: '/alla', name:'Alla stugor', component: AllaStugorView},
  { path: '/hyra/:id', name: 'Stuga', component: SingleItemView, props:true },
  { path: '/faq', name: 'FAQ', component: FAQView },
  { path: '/support', name: 'Support', component: SupportView },
  { path: '/hyra', name:'hyra', component: HyraSummeringView},
  { path: '/admin', name: 'Admin', component: AdminView },
  { path: '/user', name: 'User', component: UserView },
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
