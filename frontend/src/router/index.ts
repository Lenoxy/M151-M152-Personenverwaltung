import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'Home',
    component: () => import('../views/Home.vue')
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('../views/Login.vue')
  },
  {
    path: '/set-password',
    name: 'Set password',
    component: () => import('../views/SetPassword.vue')
  },
  {
    path: '/change-password',
    name: 'Change password',
    component: () => import('../views/ChangePassword.vue')
  },
  {
    path: '/verify-password',
    name: 'Verify password',
    component: () => import('../views/VerifyPassword.vue')
  },
  {
    path: '/create',
    name: 'Create person',
    component: () => import('../views/PersonCreate.vue')
  },
  {
    path: '/list',
    name: 'Person list',
    component: () => import('../views/PersonList.vue')
  },
  {
    path: '/edit/:id',
    name: 'Edit person',
    component: () => import('../views/PersonEdit.vue')
  },
  {
    path: '/selfedit',
    name: 'Edit Self',
    component: () => import('../views/PersonEdit.vue')
  },
  {
    path: '/detail/:id',
    name: 'Person detail',
    component: () => import('../views/PersonDetail.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
