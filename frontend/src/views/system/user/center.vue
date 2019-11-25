<template>
  <div class="app-container">
    <div id="content-main" style="width:600px;">
      <el-card shadow="never" class="box-card reset-pass">
        <div><h4 class="account-label-icon">登錄帳號：{{ name }}</h4></div>
        <div><h4 class="account-label-icon">註冊時間：{{ createTime }}</h4></div>
        <div><h4 class="account-label-icon">帳號狀態：<span style="color: #909399">正常</span></h4></div>
      </el-card>
      <el-card shadow="never" class="box-card reset-pass">
        <h4 class="account-label-icon">登錄密碼：</h4>
        <updatePass/>
        <p>安全性高的密碼可使帳號更安全，建議設置同時包含字母，數位元，符號的密碼。</p>
      </el-card>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import { regEmail } from '@/utils/index'
import updatePass from './center/updatePass'
import { getToken } from '@/utils/auth'
import store from '@/store'
export default {
  name: 'Center',
  components: { updatePass },
  data() {
    return {
      headers: {
        'Authorization': 'Bearer ' + getToken()
      }
    }
  },
  computed: {
    ...mapGetters([
      'avatar',
      'name',
      'email',
      'createTime',
      'updateAvatarApi'
    ])
  },
  methods: {
    formatEmail(mail) {
      return regEmail(mail)
    },
    handleSuccess(response, file, fileList) {
      this.$notify({
        title: '頭像修改成功',
        type: 'success',
        duration: 2500
      })
      store.dispatch('GetInfo').then(() => {})
    },
    // 監聽上傳失敗
    handleError(e, file, fileList) {
      const msg = JSON.parse(e.message)
      this.$notify({
        title: msg.message,
        type: 'error',
        duration: 2500
      })
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss">
  .box-card{border:0;border-bottom:1px solid #ECEDFE;border-radius:unset;h4{height:26px;margin:0 0 7px;line-height:26px;font-size:1.1rem;font-weight:500;color:#444242;display:inline-block}p{font-family:Lantinghei;font-size:90%;color:#747474}ul{padding:0;margin:7px 0 0;list-style:none;font-size:80%}li{float:left;margin:0 30px 10px 0!important}li:before{width:8px;height:8px;background-color:#52acd9;color:#52acd9;display:inline-block;border-radius:50%;margin-right:5px;content:'';box-sizing:border-box}}.user-info{height:170px}.reset-email{border-bottom:0}.avatar-content,.user-info-content{float:left}.user-info-content{font-family:Lantinghei;position:relative;font-size:14px;margin:25px;div{margin-bottom:15px}}.avatar-uploader-icon{font-size:28px;width:120px;height:120px;line-height:120px;text-align:center}.avatar{width:120px;height:120px;display:block;border-radius:50%}
</style>

