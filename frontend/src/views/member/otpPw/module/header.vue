<template>
  <div class="head-container">
    <el-input v-model="form.mobile" clearable placeholder="手機號碼" maxlength="10" style="width: 200px;" class="filter-item" @keyup.enter.native="doSubmit"/>
    <el-button class="filter-item" size="mini" type="primary" icon="el-icon-edit" @click="doSubmit">重新取得OTP驗證碼</el-button>
  </div>
</template>

<script>
import { getToken } from '@/utils/auth'
import { parseTime } from '@/utils/index'
const baseUrl = process.env.BASE_API
export default {
  props: {
    sup_this: {
      type: Object,
      required: true
    }
  },
  data() {
    return {
      form : {
        mobile :''
      }
    }
  },
  methods: {
    parseTime,
    doSubmit() {
      var thisObj = this
      thisObj.sup_this.otp_info = '';
      thisObj.sup_this.expire_date = '';

     if (!/09[0-9]{8}/.test(this.form.mobile)) {
        thisObj.$notify({
         title: '門號格式錯誤',
         type: 'error',
         duration: 2500})
         return false
      }

      var formData = new FormData()
      formData.append('mobile', this.form.mobile)     
      $.ajax({
        url:baseUrl + '/member/otp/queryInfoByMobile',
        type:'POST', //GET
        async:false, //或false,是否非同步
        enctype: 'multipart/form-data',
        contentType: false,
        processData: false,
        cache: false,
        data: formData,
        headers: {
          "Authorization": 'Bearer ' + getToken()
        },
        timeout:5000,    //超时时间
        dataType:'json',    //返回的数据格式：
        success:function(data, textStatus, jqXHR){
            if('Y' == data.status){
                thisObj.$notify({
                title: '此門號已驗證',
                type: 'error',
                duration: 2500
              })
            } else if ('F' == data.status) {
                thisObj.$notify({
                title: '查無門號資訊',
                type: 'error',
                duration: 2500
              })
            } else {
              thisObj.sup_this.otp_info = data.otp
              thisObj.sup_this.expire_date =  parseTime(data.expireTime)
            }
        },
        error:function(xhr,textStatus){
          thisObj.$notify({
            title: '取得OTP失敗',
            type: 'error',
            duration: 2500
          })
        }
      })
      this.loading = true
    }, 
  }
}

</script>
