<template>
  <div class="app-container">
    <eHeader :query="query"/>
    <!--表格渲染  -->
    <el-table v-loading="loading" :data="data" size="small" border stripe style="width: 100%;">
      <el-table-column prop="id" label="流水號" min-width="20%" align="center"/>
      <el-table-column prop="name" label="卡別名稱" min-width="40%" align="center"/>
      <el-table-column label="操作" min-width="40%" align="center">
        <template slot-scope="scope">
          <edit :data="scope.row" :sup_this="sup_this"/>&nbsp;<el-popover
            :ref="scope.row.id"
            placement="top"
            width="180">
            <p>確定刪除嗎？</p>
            <div style="text-align: right; margin: 0">
              <el-button size="mini" type="text" @click="$refs[scope.row.id].doClose()">取消</el-button>
              <el-button :loading="delLoading" type="primary" size="mini" @click="subDelete(scope.row.id)">確定</el-button>
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
import initData from '@/mixins/initData'
import eHeader from './module/header'
import edit from './module/edit'
export default {
  components: { eHeader, edit },
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
    beforeInit() {
      this.url = 'card/cardType/queryAll'
      const sort = 'id,desc'
      const query = this.query
      const name = query.name
      this.params = { page: this.page, size: this.size, sort: sort }
      if (name) { this.params['name'] = name }
      return true
    },
    subDelete(id) {
      del('card/cardType', id).then(res => {
        this.delLoading = false
        this.$refs[id].doClose()
        this.init()
        this.$notify({
          title: '删除成功',
          type: 'success',
          duration: 2500
        })
      }).catch(err => {
        this.delLoading = false
        this.$refs[id].doClose()
        console.log(err.response.data.message)
      })
    }
  }
}
</script>
<style scoped>
</style>