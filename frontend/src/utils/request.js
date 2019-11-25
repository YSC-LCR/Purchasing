import axios from 'axios'
import router from '@/router'
import { Notification, MessageBox } from 'element-ui'
import store from '../store'
import { getToken } from '@/utils/auth'

// 創建axios實例
const service = axios.create({
  baseURL: process.env.BASE_API, // api 的 base_url
  timeout: 900000 // 請求超時時間
})

// request攔截器
service.interceptors.request.use(
  config => {
    if (getToken()) {
      config.headers['Authorization'] = 'Bearer ' + getToken() // 讓每個請求攜帶自訂token 請根據實際情況自行修改
    }
    // config.headers['Content-Type'] = 'application/json'
    return config
  },
  error => {
    // Do something with request error
    console.log(error) // for debug
    Promise.reject(error)
  }
)

// response 攔截器
service.interceptors.response.use(
  response => {
    const code = response.status
    if (code < 200 || code > 300) {
      Notification.error({
        title: response.message
      })
      return Promise.reject('error')
    } else {
      return response.data
    }
  },
  error => {
    let code = 0
    try {
      code = error.response.data.status
    } catch (e) {
      if (error.toString().indexOf('timeout')) {
        Notification.error({
          title: '請求超時',
          duration: 2500
        })
        return Promise.reject(error)
      }
    }
    if (code === 401) {
      MessageBox.confirm(
        '登錄狀態已過期，您可以繼續留在該頁面，或者重新登錄',
        '系統提示',
        {
          confirmButtonText: '重新登錄',
          cancelButtonText: '取消',
          type: 'warning'
        }
      ).then(() => {
        store.dispatch('LogOut').then(() => {
          location.reload() // 為了重新產生實體vue-router物件 避免bug
        })
      })
    } else if (code === 403) {
      router.push({ path: '/401' })
    } else {
      const errorMsg = error.response.data.message
      if (errorMsg !== undefined) {
        Notification.error({
          title: errorMsg,
          duration: 2500
        })
      }
    }
    return Promise.reject(error)
  }
)
export default service

