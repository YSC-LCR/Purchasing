import router from './router'
import store from './store'
import NProgress from 'nprogress' // progress bar
import 'nprogress/nprogress.css'// progress bar style
import { getToken } from '@/utils/auth' // getToken from cookie
import { buildMenus } from '@/api/menu'
import { filterAsyncRouter } from './store/modules/permission'

NProgress.configure({ showSpinner: false })// NProgress Configuration

const whiteList = ['/login']// no redirect whitelist

router.beforeEach((to, from, next) => {
  if (to.meta.title) {
    document.title = to.meta.title + ' - frontend'
  }
  NProgress.start() // start progress bar
  if (getToken()) {
    // 已登錄且要跳轉的頁面是登錄頁
    if (to.path === '/login') {
      next({ path: '/' })
      NProgress.done() // if current page is dashboard will not trigger	afterEach hook, so manually handle it
    } else {
      if (store.getters.roles.length === 0) { // 判斷當前用戶是否已拉取完user_info資訊
        store.dispatch('GetInfo').then(res => { // 拉取user_info
          // 動態路由，拉取選單
          loadMenus(next, to)
        }).catch((err) => {
          console.log(err)
          store.dispatch('LogOut').then(() => {
            location.reload() // 為了重新產生實體vue-router物件 避免bug
          })
        })
      // 登錄時未拉取 功能表，在此處拉取
      } else if (store.getters.loadMenus) {
        // 修改成false，防止閉環
        store.dispatch('updateLoadMenus').then(res => {})
        loadMenus(next, to)
      } else {
        next()
      }
    }
  } else {
    /* has no token*/
    if (whiteList.indexOf(to.path) !== -1) { // 在免登錄白名單，直接進入
      next()
    } else {
      next(`/login?redirect=${to.path}`) // 否則全部重定向到登錄頁
      NProgress.done()
    }
  }
})

export const loadMenus = (next, to) => {
  buildMenus().then(res => {
    const asyncRouter = filterAsyncRouter(res)
    asyncRouter.push({ path: '*', redirect: '/404', hidden: true })
    store.dispatch('GenerateRoutes', asyncRouter).then(() => { // 存儲路由
      router.addRoutes(asyncRouter) // 動態添加可訪問路由表
      next({ ...to, replace: true })// hack方法 確保addRoutes已完成
    })
  })
}

router.afterEach(() => {
  NProgress.done() // finish progress bar
})

