<template>
  <el-dialog :close-on-click-modal="false" :visible.sync="subDialog" :title="isAdd ? '新增子類別' : '編輯子類別'" append-to-body width="420px">
    <el-form ref="subform" :show-message="false" :model="subform" size="small" label-width="120px" >
      <el-form-item label="名稱" prop="subCategoryName" required >
        <el-input id="subCaregoryName" v-model="subform.subCategoryName" style="width: 200px;"/>
      </el-form-item>
      <el-form-item label="描述" prop="desc" required >
        <el-input id="desc" v-model="subform.desc" style="width: 200px;"/>
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
    },
    categoryId:{
      type: Number,
      required: true
    }
  },
  data() {
    return {
      subDialog: false,
      loading: false,
      subform: {  
        subCategoryId:'',
        subCategoryName: '',
        desc:''
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
      formData.append('subCategoryName', this.subform.subCategoryName)
      formData.append('desc', this.subform.desc)
      formData.append('categoryId', this.categoryId)
      var thisObj = this
      $.ajax({
        url:baseUrl + '/homePage/prod/prodSubCategory/create',
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
          } else {
            thisObj.$notify({
              title: '新增失敗',
              type: 'error',
              duration: 2500
            })
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
      this.subDialog = false
      this.$parent.$parent.$parent.initList()
      this.resetForm()
    },
     doUpdate() {
      var formData = new FormData()
      formData.append('subCategoryId', this.subform.subCategoryId)
      formData.append('subCategoryName', this.subform.subCategoryName)
      formData.append('desc', this.subform.desc)
      var thisObj = this
      $.ajax({
        url: baseUrl + '/homePage/prod/prodSubCategory/update',
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
      this.subDialog = false
      this.$parent.$parent.$parent.$parent.$parent.initList()
      this.resetForm()
    },
    
    resetForm() {
      this.subDialog = false
      this.$refs['subform'].resetFields()
    },

    checkData() {
      if (!this.subform.subCategoryName) {
        this.$notify({
          title: '請輸入主類別名稱',
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
