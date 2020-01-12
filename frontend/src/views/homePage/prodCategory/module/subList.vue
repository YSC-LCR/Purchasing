<template>
    <el-dialog :close-on-click-modal="false" :visible.sync="viewEventDialog" title="設定子類別" append-to-body width="90%">
      <div><span style="font-size:16px;">主類名稱： {{ form.categoryName }}</span></div>
      <esHeader :sup_this="sup_this" :categoryId ="categoryId"/>
    <el-table v-loading="loading" :data="tableData" size="small" border stripe style="width: 100%;">
      <el-table-column prop="subCategoryName" label="子類名稱" min-width="30%" align="center"/>
      <el-table-column prop="desc" label="描述" min-width="30%" align="center"/>
      <el-table-column label="操作" min-width="50%" align="center">
        <template slot-scope="scope">
          <editSub :data="scope.row" :sup_this="sup_this" :categoryId ="categoryId"/>
          <el-popover :ref="scope.row.subCategoryId" placement="top">
            <p>確定刪除嗎？</p>
            <div style="text-align: right; margin: 0">
              <el-button size="mini" type="text" @click="$refs[scope.row.subCategoryId].doClose()">取消</el-button>
              <el-button :loading="delLoading" type="primary" size="mini" @click="subDeleteSub(scope.row.subCategoryId)">確定</el-button>
            </div>
            <el-button slot="reference" type="danger" size="mini">删除</el-button>
          </el-popover>
        </template>
      </el-table-column>
    </el-table>
      <br/>
    </el-dialog>

</template>
<script>
import { del } from "@/api/common";
import esHeader from "./headerSub";
import { getToken } from '@/utils/auth'
import $ from 'jquery'
import editSub from './editSub'
const baseUrl = process.env.BASE_API
export default {
  props: {
    sup_this: {
      type: Object,
      required: true
    },
    categoryId:{
      type: Number,
      required: true
    }
  },
  data() {
    return {
      subList_this: this,
      delLoading: false,
      viewEventDialog: false,
      loading: false,
      tableData : [],
      form: {
        categoryId: 0,
        categoryName:''
      } 
    }
  },
  components: {
    esHeader,editSub
  },
  methods: {
   initList() {
     var thisObj = this
     $.ajax({
       url:baseUrl + '/homePage/prod/prodSubCategory/querySubAll',
       type:'GET', //GET
       async:false, //或false,是否非同步
       contentType: false,
       processData: true,
       cache: false,
       data: {
         categoryId: thisObj.form.categoryId
       },
       headers: {
         "Authorization": 'Bearer ' + getToken()
       },
       timeout:5000,    //超时时间
       dataType:'json',    //返回的数据格式：
       success:function(data, textStatus, jqXHR){
          thisObj.tableData = data
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
    subDeleteSub(subCategoryId) {
      this.delLoading = true
      del('homePage/prod/prodSubCategory', subCategoryId).then(res => {
        this.delLoading = false
        this.$refs[subCategoryId].doClose()
        this.initList()
        this.$notify({
          title: '删除成功',
          type: 'success',
          duration: 2500
        })
      }).catch(err => {
        this.delLoading = false
        this.$refs[subCategoryId].doClose()
        console.log(err)
      })
    }    
  },  
}
</script>

<style scoped>
  div{display: inline-block;margin-right: 3px;}
</style>
