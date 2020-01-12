<template>
  <el-dialog :close-on-click-modal="false" :visible.sync="dialog" :title="isAdd ? '新增計量類別' : '編輯計量類別'" append-to-body width="420px">
    <el-form ref="form" :show-message="false" :model="form" size="small" label-width="120px" >
      <el-form-item label="主類別名稱" prop="unitName" required >
        <el-input id="name" v-model="form.unitName" style="width: 200px;"/>
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
        unitName: ''
      }
    }
  },
  methods: {
    cancel() {
      this.resetForm()
    },
    doSubmit() {
      var checkFlag = this.checkData()
      if (checkFlag) {
        this.loading = true
         if (this.isAdd) {
          this.doCreate()
        } else {
          this.doUpdate()
        }
      } else {
        return false
      }
    },
    doCreate() {
      var formData = new FormData()
      formData.append('unitName', this.form.unitName)
      var thisObj = this
      $.ajax({
        url:baseUrl + '/homePage/prod/prodUnit/create',
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
     doUpdate() {
      var formData = new FormData()
      formData.append('unitId', this.form.unitId)
      formData.append('unitName', this.form.unitName)
      var thisObj = this
      $.ajax({
        url: baseUrl + '/homePage/prod/prodUnit/update',
        type: 'POST', //GET
        async: false, //或false,是否非同步
        enctype: 'multipart/form-data',
        contentType: false,
        processData: false,
        cache: false,
        data: formData,
        headers: {
          "Authorization": 'Bearer ' + getToken()
        },
        timeout : 5000,    //超时时间
        dataType : 'json',    //返回的数据格式：
        success: function (data, textStatus, jqXHR) {
          if (data.code === '0000') {
            thisObj.$notify({
              title: '修改成功',
              type: 'success',
              duration: 2500
            })
            // this.$message.success('新增成功！')
          } else {
            thisObj.$notify({
              title: '修改失敗',
              type: 'error',
              duration: 2500
            })
            // this.$message.error('新增失敗！')
          }
        },
        error: function (xhr, textStatus) {
          thisObj.$notify({
            title: '修改失敗',
            type: 'error',
            duration: 2500
          })
        }
        //,complete:function(){}
      })
      this.loading = false
      this.dialog = false
      this.sup_this.init()
      this.resetForm()
    },
    
    resetForm() {
      this.dialog = false
      this.$refs['form'].resetFields()
    },

    checkData() {
      if (!this.form.unitName) {
        this.$notify({
          title: '請輸入計量單位名稱',
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
