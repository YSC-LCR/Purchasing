<template>
  <div>
    <el-button size="mini" type="success" @click="to">編輯</el-button>
    <eForm ref="form" :sup_this="sup_this" :is-add="false" :cateView = cateGoryInfos />
  </div>
</template>
<script>
import eForm from './form'
import { getToken } from '@/utils/auth'
import $ from 'jquery'
const baseUrl = process.env.BASE_API
export default {
  components: { eForm },
  props: {
    data: {
      type: Object,
      required: true
    },
    sup_this: {
      type: Object,
      required: true
    }
  },
  data() {
    return {
       cateGoryInfos:[],
    }
  },
  methods: {
    to() {
      console.log('==================================')
      console.log('to() start')
      const _this = this.$refs.form
      console.log(this.data.categoryId)
      console.log(this.data.subCategoryId)
      console.log(this.data.weight)
      console.log(this.data.canSea)
      console.log(this.data.canFly)
      _this.dialog = true
      _this.form = {
        subCategoryInfo:[this.data.categoryId,this.data.subCategoryId],
        name: this.data.prodName,
        weight: this.data.weight,        
        canFly: this.data.canFly,
        canSea: this.data.canSea,
        desc: this.data.prodDesc,
      }
      this.initCateInfo()
      console.log('to() end')
      console.log('==================================')
    },
    initCateInfo() {
     var thisObj = this
     $.ajax({
       url:baseUrl + '/homePage/prod/prodMain/initProdForm',
       type:'GET', //GET
       async:false, //或false,是否非同步
       contentType: false,
       processData: true,
       cache: false,
       headers: {
         "Authorization": 'Bearer ' + getToken()
       },
       timeout:5000,    //超时时间
       dataType:'json',    //返回的数据格式：
       success:function(data, textStatus, jqXHR){
          for (var i = 0; i < data.cateGoryView.length; i++) {
              var cateGory = data.cateGoryView[i];
              thisObj.cateGoryInfos.push({
                "label": cateGory.label,
                "value": cateGory.value,
                "children": cateGory.children
              })
          }         
       },
       error:function(xhr,textStatus){
         thisObj.$notify({
           title: '查無資料',
           type: 'error',
           duration: 2500
         })
       }
     })
   },
  }
}
</script>

<style scoped>
  div{display: inline-block;margin-right: 3px;}
</style>
