import Vue from 'vue'
import Router from 'vue-router'

// in development-env not use lazy-loading, because lazy-loading too many pages will cause webpack hot update too slow. so only in production use lazy-loading;
// detail: https://panjiachen.github.io/vue-element-admin-site/#/lazy-loading

Vue.use(Router)

/* Layout */
import Layout from '../views/layout/Layout'

/**
* hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
* alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
*                                if not set alwaysShow, only more than one route under the children
*                                it will becomes nested mode, otherwise not show the root menu
* redirect: noredirect           if `redirect:noredirect` will no redirect in the breadcrumb
* name:'router-name'             the name is used by <keep-alive> (must set!!!)
* meta : {
    title: 'title'               the name show in subMenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar
    breadcrumb: false            if false, the item will hidden in breadcrumb(default is true)
  }
**/
export const constantRouterMap = [
  { path: '/login', component: () => import('@/views/login/index'), hidden: true },
  { path: '/404', component: () => import('@/views/404'), hidden: true },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    name: 'Dashboard',
    hidden: true,
    children: [{
      path: 'dashboard',
      component: () => import('@/views/dashboard/index')
    }]
  },

  {
    path: '/newsql',
    component: Layout,
    redirect: '/newsql/appList',
    name: 'Newsql',
    meta: { title: 'Newsql', icon: 'example' },
    children: [
      {
        path: 'appList',
        name: 'AppList',
        component: () => import('@/views/newsql/appList'),
        meta: { title: 'AppList', icon: 'table' }
      },
      {
        hidden: true,
        path: 'tree',
        name: 'Tree',
        component: () => import('@/views/tree/index'),
        meta: { title: 'Tree', icon: 'tree' }
      },
      {
        hidden: true,
        path: 'newSqlList',
        name: 'NewSqlList',
        component: () => import('@/views/newsql/newSqlList'),
        meta: { title: 'NewSqlList', icon: 'table' }
      },
      {
        hidden: true,
        path: 'sqlDetail',
        name: 'SqlDetail',
        component: () => import('@/views/newsql/sqlDetail'),
        meta: { title: 'SqlDetail', icon: 'table' }
      }
    ]
  },

  {
    path: '/form',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'Form',
        component: () => import('@/views/form/index'),
        meta: { title: 'Form', icon: 'form' }
      }
    ]
  },

  {
    path: '/notification',
    component: Layout,
    redirect: '/notification/settings',
    name: 'Notification',
    meta: {
      title: 'Notification',
      icon: 'nested'
    },
    children: [
      {
        path: 'settings',
        component: () => import('@/views/nested/menu2/index'),
        meta: { title: 'Notify Settings' }
      }
    ]
  },

  {
    path: 'external-link',
    component: Layout,
    children: [
      {
        path: 'https://bugVanisher.github.io',
        meta: { title: 'External Link', icon: 'link' }
      }
    ]
  },

  { path: '*', redirect: '/404', hidden: true }
]

export default new Router({
  // mode: 'history', //后端支持可开
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})
