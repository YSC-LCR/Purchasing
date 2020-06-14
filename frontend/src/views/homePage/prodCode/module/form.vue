<template>
  <el-dialog :close-on-click-modal="false" :visible.sync="dialog" :title="isAdd ? '新增商品' : '編輯商品'" append-to-body width="420px">
    <el-form ref="form" :show-message="false" :model="form" size="small" label-width="120px" >
      <el-form-item label="商品類別" prop="subCategoryId" required >
        <el-cascader
          v-model="form.subCategoryInfo"
          :options="cateView"
          :props="{ expandTrigger: 'hover' }"
          @change="handleChange">
        </el-cascader>
      </el-form-item>
      <el-form-item label="商品名稱" prop="name" required >
        <el-input id="name" v-model="form.name" style="width: 200px;"/>
      </el-form-item>
      <el-form-item label="商品重量" prop="weight" required >
        <el-input id="weight" v-model="form.weight" style="width: 200px;"/>
      </el-form-item>
      <el-form-item label="可空運" prop="canFly" required >
        <el-checkbox v-model="form.canFly"></el-checkbox>
      </el-form-item>
      <el-form-item label="可海運" prop="canSea" required >
        <el-checkbox v-model="form.canSea"></el-checkbox>
      </el-form-item>
      <el-form-item label="商品描述" prop="desc" required >
        <el-input
          type="textarea"
          autosize
          placeholder="请輸入內容"
          v-model="form.desc">
        </el-input>
      </el-form-item>
      <el-form-item label="商品圖片" prop="images" required >
        <vue-upload-multiple-image
          @upload-success="uploadImageSuccess"
          @before-remove="beforeRemove"
          @edit-image="editImage"
          :data-images="images"
          ></vue-upload-multiple-image>
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
import VueUploadMultipleImage from 'vue-upload-multiple-image'
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
    cateView: {
      type: Array, 
      required: false
    },
  },
  data() {
    return {
      dialog: false,
      loading: false,
      form: {
        subCategoryInfo:[14,19],
        name: '',
        weight: '',        
        canFly: false,
        canSea: false,
        desc: '',
        images:[]
      },
      images: []
    }
  },
  components: {
    VueUploadMultipleImage
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
      console.log('this.images')
      console.log(this.form.images)
      // console.log('Json images')
      // console.log(JSON.stringify(this.form.images))
    
      var formData = new FormData()
      formData.append('subCategoryInfo', this.form.subCategoryInfo)
      formData.append('name', this.form.name)
      formData.append('weight', this.form.weight)
      formData.append('canFly', this.form.canFly)
      formData.append('canSea', this.form.canSea)
      formData.append('desc', this.form.desc)
      formData.append('images', JSON.stringify(this.form.images))
      var thisObj = this
      $.ajax({
        url:baseUrl + '/homePage/prod/prodMain/create',
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
      return true
    },
    handleChange(value) {
      console.log(this.subCategoryInfo)
      console.log(value);
    },
    uploadImageSuccess(formData, index, fileList) {
      console.log('formData', formData)
      console.log('index', index)
      console.log('fileList',fileList)
      this.form.images = []
      this.form.images.push(fileList)
    },
    beforeRemove (index, done, fileList) {
      console.log('index', index, fileList)
      var r = confirm("remove image")
      if (r == true) {
        done()
      } else {
      }
    },
    editImage (formData, index, fileList) {
      console.log('edit data', formData, index, fileList)
    }
  }
}
</script>

<style scoped>
#my-strictly-unique-vue-upload-multiple-image {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
 
h1, h2 {
  font-weight: normal;
}
 
ul {
  list-style-type: none;
  padding: 0;
}
 
li {
  display: inline-block;
  margin: 0 10px;
}
 
a {
  color: #42b983;
}
</style>
