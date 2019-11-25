<template>
  <div class="app-container">
    <eHeader :query="query"/>
    <!--表格渲染-->
    <el-table v-loading="loading" :data="data" size="small" border style="width: 100%;">
      <el-table-column prop="username" label="用戶名"/>
      <el-table-column prop="requestIp" label="IP"/>
      <el-table-column prop="description" label="描述"/>
      <!-- <el-table-column :show-overflow-tooltip="true" prop="method" label="方法名稱"/> -->
      <el-table-column :show-overflow-tooltip="true" prop="params" label="參數"/>
      <!-- <el-table-column prop="time" label="請求耗時" align="center">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.time <= 300">{{ scope.row.time }}ms</el-tag>
          <el-tag v-else-if="scope.row.time <= 1000" type="warning">{{ scope.row.time }}ms</el-tag>
          <el-tag v-else type="danger">{{ scope.row.time }}ms</el-tag>
        </template>
      </el-table-column> -->
      <el-table-column prop="createTime" label="創建日期" width="180px">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
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
import initData from '@/mixins/initData'
import { parseTime } from '@/utils/index'
import eHeader from './module/header'
export default {
  components: { eHeader },
  mixins: [initData],
  created() {
    this.$nextTick(() => {
      this.init()
    })
  },
  methods: {
    parseTime,
    beforeInit() {
      this.url = 'api/logs'
      const sort = 'id,desc'
      const query = this.query
      const username = query.username
      this.params = { page: this.page, size: this.size, sort: sort }
      if (username && username) { this.params['username'] = username }
      return true
    }
  }
}
</script>

<style scoped>

</style>

