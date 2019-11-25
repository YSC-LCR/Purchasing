<template>
  <div>
    <el-dialog :visible.sync="cardTypeDialog" :title="isAdd ? '新增卡別名稱' : '編輯卡別名稱'" append-to-body width="500px">
      <el-form ref="form" :show-message="false" :model="form" size="small" label-width="120px" >
        <!-- <el-form-item label="流水號" prop="id" >
          <el-input id="id" v-model="form.id" style="width: 200px;"/>
        </el-form-item> -->
        <el-form-item label="卡別名稱" prop="name">
          <el-input id="name" v-model="form.name" style="width: 200px;"/>
        </el-form-item>
        <el-input type="hidden" id="id" v-model="form.id"/>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="text" @click="cancelCardTypeDialog()">取消</el-button>
        <el-button :loading="loading" type="primary" @click="addBankAndCard()">確認</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { getToken } from '@/utils/auth'
import $ from 'jquery'
const baseUrl = process.env.BASE_API
export default {
  components: { },
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
      cardTypeDialog: false,
      loading: false,
      form: {
        id: 0,
        name: ''
      }
    }
  },
 
  methods: {
    cancelCardTypeDialog() {
      this.resetForm()
    },
    addBankAndCard() {
      if(!this.checkCardTypeData()){
        return false;
      }
      this.loading = true
      try {
        if(this.isAdd){
          this.doCreate();
        }else{
          this.doUpdate();
        }
        this.cardTypeDialog = false
        if(this.isAdd){
          this.$parent.$parent.init()
        }else{
          this.sup_this.init()
        }       
        this.resetForm()
      } catch(e) {  
        console.log('catch:', e)
      } finally {
        this.loading = false
      }
    },
    doCreate(){
      var formData = new FormData()
      formData.append('name', this.form.name)
      var thisObj = this
      $.ajax({
        url:baseUrl + '/card/cardType/create',
        type:'POST', //GET
        async:false, //是否非同步
        processData : false, 
        contentType : false,
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
        error:function(xhr, textStatus){
          thisObj.$notify({
            title: '新增失敗',
            type: 'error',
            duration: 2500
          })
        }
      })
    }, 
    doUpdate(){
      var formData = new FormData()
      formData.append('id', this.form.id)
      formData.append('name', this.form.name)
      var thisObj = this
      $.ajax({
        url:baseUrl + '/card/cardType/update',
        type:'POST', //GET
        async:false, //是否非同步
        processData : false, 
        contentType : false,
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
              title: '編輯成功',
              type: 'success',
              duration: 2500
            })
          } else {
            thisObj.$notify({
              title: '編輯失敗',
              type: 'error',
              duration: 2500
            })
          }
        },
        error:function(xhr, textStatus){
          thisObj.$notify({
            title: '編輯失敗',
            type: 'error',
            duration: 2500
          })
        }
      })
    },
    resetForm() {
      this.cardTypeDialog = false
      this.$refs['form'].resetFields()
    },
    checkCardTypeData(){
       if (!this.form.name) {
        this.$notify({
          title: '請輸入卡別名稱',
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
