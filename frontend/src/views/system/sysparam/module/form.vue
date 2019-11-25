<template>
  <el-dialog :visible.sync="dialog" :title="isAdd ? '新增系統參數' : '編輯系統參數'" append-to-body width="420px">
    <el-form ref="form" :show-message="false" :model="form" size="small" label-width="120px" >
      <el-form-item label="參數代碼" prop="paramId" required >
        <el-input id="paramId" v-model="form.paramId" style="width: 200px;"/>
      </el-form-item>
      <el-form-item label="參數名稱" prop="paramName" required >
        <el-input id="paramName" v-model="form.paramName" style="width: 200px;"/>
      </el-form-item>
      <el-form-item label="參數說明" prop="paramDesc" required>
        <el-input id="paramDesc" v-model="form.paramDesc" style="width: 200px;"/>
      </el-form-item>
      <el-form-item label="參數值" prop="paramValue" required >
        <el-input id="paramValue" v-model="form.paramValue" style="width: 200px;"/>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="text" @click="cancel">取消</el-button>
      <el-button :loading="loading" type="primary" @click="doSubmit">確認</el-button>
    </div>
  </el-dialog>
</template>

<script>
import { parseDate } from '@/utils/index'
import { getToken } from '@/utils/auth'
import $ from 'jquery'
const baseUrl = process.env.BASE_API
export default {
  props: {
    isAdd: {
      type: Boolean,
      required: true
    },
    sup_this: {
      type: Object,
      default: null
    }
  },
  data() {
    return {
      dialog: false,
      loading: false,
      form: {
        paramId: '',
        paramName: '',
        paramDesc: '',
        paramValue: ''
      }
    }
  },
  methods: {
    //parseDate,
    cancel() {
      this.resetForm()
    },
    doSubmit() {
      var checkFlag = this.checkData()
      if (checkFlag) {
        this.loading = true
        this.doCreate()
      } else {
        return false
      }
    },
    doCreate() {
      var formData = new FormData()
      formData.append('paramId', this.form.paramId)
      formData.append('paramName', this.form.paramName)
      formData.append('paramDesc', this.form.paramDesc)
      formData.append('paramValue', this.form.paramValue)
      var thisObj = this
      $.ajax({
        url:baseUrl + '/system/sysparam/create',
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
          if (data.code === '0000') {
            thisObj.$notify({
              title: '新增成功',
              type: 'success',
              duration: 2500
            })
            // this.$message.success('新增成功！')
          } else {
            thisObj.$notify({
              title: '新增失敗',
              type: 'error',
              duration: 2500
            })
            // this.$message.error('新增失敗！')
          }
        },
        error:function(xhr,textStatus){
          thisObj.$notify({
            title: '新增失敗',
            type: 'error',
            duration: 2500
          })
        },
        complete:function(){}
      })
      this.loading = false
      this.dialog = false
      this.$parent.$parent.init()
      this.resetForm()
    },
    
    handleImgFileUpload(e){
      imgFile = e.target.files || e.dataTransfer.files;
      console.info(imgFile[0])
    },

    resetForm() {
      this.dialog = false
      this.$refs['form'].resetFields()

      $('#imageFile').val('')
    },

    checkData() {
      if (!this.form.paramId) {
        this.$notify({
          title: '請輸入參數代碼',
          type: 'error',
          duration: 2500
        })
        return false
      }
      if (!this.form.paramName) {
        this.$notify({
          title: '請輸入參數名稱',
          type: 'error',
          duration: 2500
        })
        return false
      }
      if (!this.form.paramDesc) {
        this.$notify({
          title: '請輸入參數說明',
          type: 'error',
          duration: 2500
        })
        return false
      }
      if (!this.form.paramValue) {
        this.$notify({
          title: '請輸入參數值',
          type: 'error',
          duration: 2500
        })
        return false
      }
      return true
    }

  }
}
</script>

<style scoped>
</style>
