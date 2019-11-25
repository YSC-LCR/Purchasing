<template>
  <div class="app-container">
    <eHeader :query="query"/>
    <!--表格渲染  -->
    <el-table v-loading="loading" :data="data" size="small" border stripe style="width: 100%;">
      <el-table-column prop="bankName" label="發卡單位" min-width="18%" align="center"/>
      <el-table-column prop="categoryName" label="卡片類型" min-width="18%" align="center"/>
      <el-table-column prop="cardTypeName" label="卡別" min-width="18%" align="center"/>
      <el-table-column prop="cardBin" label="CARD_BIN" min-width="18%" align="center"/>
      <el-table-column label="操作" min-width="18%" align="center">
        <template slot-scope="scope">
          <detail :data="scope.row" :sup_this="sup_this"/>
          &nbsp;
          <edit :data="scope.row" :sup_this="sup_this"/>&nbsp;<el-popover
            :ref="scope.$index"
            placement="top"
            width="180">
            <p>確定刪除嗎？</p>
            <div style="text-align: right; margin: 0">
              <el-button size="mini" type="text" @click="$refs[scope.$index].doClose()">取消</el-button>
              <el-button :loading="delLoading" type="primary" size="mini" @click="subDelete(scope.$index, scope.row.bankId, scope.row.category, scope.row.cardBin)">確定</el-button>
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
import { deleteData } from '@/api/common'
import initData from '@/mixins/initData'
//import { getPermission } from '@/utils/permission'
import eHeader from './module/header'
import edit from './module/edit'
import detail from './module/view'
export default {
  components: { eHeader, edit, detail },
  mixins: [initData],
  data() {
    return {
      delLoading: false,
      sup_this: this,
      // isEdit: this.getPermission(['ADMIN', 'BANKCARD_ALL']),
    }
  },
  created() {
    this.$nextTick(() => {
      this.init()
    })
  },
  methods: {
    //getPermission,
    beforeInit() {
      this.url = 'card/bankCard/queryAll'
      const sort = 'createTime,desc'
      const query = this.query
      const bankName = query.bankName
      this.params = { page: this.page, size: this.size, sort: sort }
      if (bankName) { this.params['bankName'] = bankName }
      return true
    },
    subDelete(index, bankId, category, cardBin) {
      this.delLoading = true
      var formData = new FormData()
      formData.append('bankId', bankId)
      formData.append('category', category)
      formData.append('cardBin', cardBin)
      deleteData('card/bankCard', formData).then(res => {
        this.delLoading = false
        this.$refs[index].doClose()
        this.init()
        this.$notify({
          title: '删除成功',
          type: 'success',
          duration: 2500
        })
      }).catch(err => {
        this.delLoading = false
        this.$refs[index].doClose()
        console.log(err.response.data.message)
      })
    }
  }
}
</script>
<style scoped>

</style>
