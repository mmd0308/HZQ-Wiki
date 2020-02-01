import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)
import Layout from '../views/layout/Layout'
// import DocLayout from '../views/document/layout/Layout'
import EmptyLayout from '../views/layout/EmptyLayout'

export const constantRouterMap = [
  { path: '/login', component: () => import('@/views/login/index'), hidden: true },
  { path: '/404', component: () => import('@/views/404'), hidden: true },

  {
    path: '/',
    component: Layout,
    redirect: '/index',
    name: 'Index',
    hidden: true,
    children: [
      { path: 'index', component: () => import('@/views/index/index') },
      // { path: 'test', component: () => import('@/views/dashboard/test') },
      { path: 'space', component: () => import('@/views/space/show/index') },
      { path: ':spaceId/doc', component: () => import('@/views/doc/show/index') },
      { path: 'doc', component: () => import('@/views/doc/show/index') },
      { path: 'articles', component: () => import('@/views/article/show/Layout') },
      { path: 'admin/:name', component: () => import('@/views/admin/index') }
    ]
  },
  {
    path: '/read',
    component: Layout,
    hidden: true,
    children: [
      { path: 'articles/:id', component: () => import('@/views/article/read/Article') }
    ]
  },
  {
    path: '/write',
    component: EmptyLayout,
    name: 'Layout',
    hidden: true,
    children: [
      // { path: 'doc', component: () => import('@/views/doc/edit/Layout') },
      { path: ':spaceId/doc/:id', component: () => import('@/views/doc/edit/Layout') },
      { path: 'article', component: () => import('@/views/article/edit/Layout') },
      { path: 'article/:id', component: () => import('@/views/article/edit/Layout') }
    ]
  },
  {
    path: '/set',
    component: Layout,
    hidden: true,
    children: [
      { path: 'space', component: () => import('@/views/space/set/Layout') },
      { path: ':spaceId/doc/:id', component: () => import('@/views/doc/set/Layout') },
      { path: 'user', component: () => import('@/views/system/user/set/Layout') },
      { path: 'system', component: () => import('@/views/setting/Layout') },
      { path: 'article', component: () => import('@/views/article/set/Layout') }
    ]
  },
  { path: '*', redirect: '/404', hidden: true }
]

export default new Router({
  mode: 'history', // 后端支持可开
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})
