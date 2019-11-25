<template>
  <el-dialog :visible.sync="dialog" :title="isAdd ? '新增樓層' : '編輯樓層'" append-to-body width="700px">
    <el-form ref="form" :show-message="false" :model="form" size="small" label-width="120px" >
      <el-form-item label="樓層代碼" prop="floorId" required >
        <el-input id="floorId" v-model="form.floorId" maxlength="10" style="width: 200px;"/>
      </el-form-item>
      <el-form-item label="樓層名稱" prop="floorName" required >
        <el-input id="floorName" v-model="form.floorName" maxlength="40" style="width: 500px;"/>
      </el-form-item>
      <el-form-item label="樓層簡介" prop="floorIntroduction" required >
        <el-input id="floorIntroduction" v-model="form.floorIntroduction" style="width: 500px;"/>
      </el-form-item>
      <!-- 樓層icon start -->
      <el-form-item v-if="!isAdd" label="已上傳的icon"><!-- 修改的時候才會顯示 -->
        <img :src="form.floorImageUrl" style="width: 500px; height: 120px;" />
      </el-form-item>
      <el-form-item :required="isAdd == true" label="樓層icon" prop="floorImageFile"> <!-- 新增 -->
        <input id="floorImageFile" type="file" name="floorImageFile" accept=".jpg, .jpeg, .png" @change="handleIconFileUpload" />
      </el-form-item>
      <!-- 樓層icon end -->
      <el-input v-model="form.seq" type="hidden" />
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
var iconFile = ''
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
        seq: '0',
        floorId: '',
        floorName: '',
        floorIntroduction: '',
        floorImageUrl: '',
        createTime: '',
        modifyTime: ''
      }
    }
  },
  methods: {
    parseDate,
    handleIconFileUpload(e) {
      iconFile = e.target.files || e.dataTransfer.files;
      console.info(iconFile[0])
    },
    cancel() {
      this.resetForm()
    },
    doSubmit() {
      var checkFlag = this.checkData(this.isAdd)
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
      formData.append('floorId', this.form.floorId)
      formData.append('floorName', this.form.floorName)
      formData.append('floorIntroduction', this.form.floorIntroduction)
      formData.append('floorImageFile', iconFile[0])

      var thisObj = this
      $.ajax({
        url:baseUrl + '/homePage/floor/create',
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
        complete:function(){

        }
      })
      this.loading = false
      this.dialog = false
      this.$parent.$parent.init()
      this.resetForm()
    },

    doUpdate() {
      var formData = new FormData()

      if (iconFile[0]) {
        formData.append('floorImageFile', iconFile[0])
      }
      formData.append('seq', this.form.seq)
      formData.append('floorId', this.form.floorId)
      formData.append('floorName', this.form.floorName)
      formData.append('floorIntroduction', this.form.floorIntroduction)

      var thisObj = this
      $.ajax({
        url: baseUrl + '/homePage/floor/update',
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
        timeout:5000,    //超时时间
        dataType:'json',    //返回的数据格式：
        success:function(data, textStatus, jqXHR){
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
        error:function(xhr,textStatus){
          thisObj.$notify({
            title: '修改失敗',
            type: 'error',
            duration: 2500
          })
        },
        complete:function(){

        }
      })
      this.loading = false
      this.dialog = false
      this.sup_this.init()
      this.resetForm()
    },

    resetForm() {
      this.dialog = false
      this.$refs['form'].resetFields()
      $('#floorImageFile').val('')
    },

    checkData(isAdd) {
      if (!this.form.floorName) {
        this.$notify({
          title: '請輸入樓層名稱',
          type: 'error',
          duration: 2500
        })
        return false
      }
      if (!this.form.floorIntroduction) {
        this.$notify({
          title: '請輸入樓層簡介',
          type: 'error',
          duration: 2500
        })
        return false
      }

      if (isAdd) {
        if (!this.form.floorId) {
          this.$notify({
            title: '請輸入樓層代碼',
            type: 'error',
            duration: 2500
          })
          return false
        }

        if (!iconFile[0]) {
          this.$notify({
            title: '請選擇上傳的樓層icon',
            type: 'error',
            duration: 2500
          })
          this.loading = false
          return false
        }
      }
      return true
    }
  }
}
</script>

<style scoped>
</style>
