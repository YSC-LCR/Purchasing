<template>
  <div class="app-container">
    <eHeader :query="query"/>
    <!--表格渲染  -->
    <el-table v-loading="loading" :data="data" size="small" border stripe style="width: 100%;">
      <el-table-column prop="paramId" label="參數代碼" sortable min-width="15%" align="center"/>

      <el-table-column prop="paramName" label="參數名稱" sortable min-width="15%" align="center"/>

      <el-table-column prop="paramDesc" label="參數說明" min-width="25%" align="center">
        <template slot-scope="scope">
          <div style="text-align:left;">{{ scope.row.paramDesc }}</div>
        </template>
      </el-table-column>

      <!-- <el-table-column prop="paramType" label="參數類別" min-width="10%" align="center"/> -->

      <el-table-column prop="paramValue" label="參數值" min-width="25%" align="center"/>
      
      <!-- <el-table-column prop="valueType" label="參數資料型別" min-width="10%" align="center"/> -->
      
      <!-- <el-table-column prop="valueSize" label="參數長度" min-width="10%" align="center"/> -->
      
      <el-table-column label="操作" min-width="20%" align="center">
        <template slot-scope="scope">
          <detail :data="scope.row" :sup_this="sup_this"/>
          <edit :data="scope.row" :sup_this="sup_this"/>
          <el-popover
            :ref="scope.row.paramId"
            placement="top"
            width="180">
            <p>是否刪除資料？</p>
            <div style="text-align: right; margin: 0">
              <!-- <el-button size="mini" type="text" @click="test($refs[scope.row.paramId])">取消</el-button> -->
              <el-button size="mini" type="text" @click="$refs[scope.row.paramId].doClose()">取消</el-button>
              <el-button :loading="delLoading" type="primary" size="mini" @click="subDelete(scope.row.paramId)">確定</el-button>
            </div><el-button slot="reference" type="danger" size="mini">删除</el-button>
          </el-popover>
        </template>
      </el-table-column>
    </el-table>
    <!--分頁組件-->
    <el-pagination
      :total="total"
      style="margin-top: 8px;"
      layout="total, prev, pager, next, sizes"
      @size-change="sizeChange"
      @current-change="pageChange"/>
  </div>
</template>



<script>

import { del } from '@/api/common'
import { getToken } from '@/utils/auth'
import initData from '@/mixins/initData'
import { parseTime, parseDate } from '@/utils/index'
import eHeader from './module/header'
import edit from './module/edit'
import detail from './module/view'
const baseUrl = process.env.BASE_API

export default {
  components: { eHeader, edit, detail },
  mixins: [initData],
  data() {
    return {
      delLoading: false,
      sup_this: this
    }
  },
  created() {
    this.$nextTick(() => {
      this.init()
    })
  },
  methods: {
    parseTime,
    parseDate,
    beforeInit() {
      this.url = 'system/sysparam/queryAll'
      const sort = 'paramId'
      const query = this.query
      const paramName = query.paramName
      this.params = { page: this.page, size: this.size, sort: sort }
      if (paramName) { this.params['paramName'] = paramName }
      return true
    },
    test(input){
      console.log(input)
    },
    subDelete(paramId) {
      this.delLoading = true
      del('system/sysparam', paramId).then(res => {
        console.log('res: ', res.test_key)
        this.delLoading = false
        this.$refs[paramId].doClose()
        this.init()
        if (res.errCode === '0000') {
          this.$notify({
            title: '删除成功',
            type: 'success',
            duration: 2500
          })
        } else {
          this.$notify({
            title: res.errMsg,
            type: 'error',
            duration: 5000
          })
        }
      }).catch(err => {
        this.delLoading = false
        this.$refs[paramId].doClose()
        console.log(err.response.data.message)
        this.$notify({
          title: '刪除失敗',
          type: 'error',
          duration: 2500
        })
      })
    }
  }
}
</script>
<style scoped>

</style>
