<template>
  <div class="head-container">
    <!-- 搜索 -->
    <el-input v-model="query.value" clearable placeholder="輸入用戶名搜索" style="width: 200px;" class="filter-item" @keyup.enter.native="toQuery"/>
    <!-- <el-select v-model="query.type" clearable placeholder="類型" class="filter-item" style="width: 130px">
      <el-option v-for="item in queryTypeOptions" :key="item.key" :label="item.display_name" :value="item.key"/>
    </el-select> -->
    <el-select v-model="query.enabled" clearable placeholder="狀態" class="filter-item" style="width: 90px" @change="toQuery">
      <el-option v-for="item in enabledTypeOptions" :key="item.key" :label="item.display_name" :value="item.key"/>
    </el-select>
    <el-button class="filter-item" size="mini" type="primary" icon="el-icon-search" @click="toQuery">搜索</el-button>
    <!-- 新增 -->
    <div style="display: inline-block;margin: 0px 2px;">
      <el-button
        v-if="checkPermission(['ADMIN','USER_ALL','USER_CREATE'])"
        class="filter-item"
        size="mini"
        type="primary"
        icon="el-icon-plus"
        @click="$refs.form.dialog = true">新增</el-button>
      <eForm ref="form" :roles="roles" :is-add="true"/>
    </div>
    <!-- 匯出 -->
    <el-button
      v-if="checkPermission(['ADMIN'])"
      :loading="downloadLoading"
      size="mini"
      class="filter-item"
      type="primary"
      icon="el-icon-download"
      @click="download">匯出</el-button>
  </div>
</template>

<script>
import checkPermission from '@/utils/permission' // 許可權判斷函數
import { parseTime } from '@/utils/index'
import eForm from './form'
// 查詢準則
export default {
  components: { eForm },
  props: {
    roles: {
      type: Array,
      required: true
    },
    query: {
      type: Object,
      required: true
    }
  },
  data() {
    return {
      downloadLoading: false,
      // queryTypeOptions: [
      //   { key: 'username', display_name: '用戶名' },
      //   { key: 'email', display_name: '郵箱' }
      // ],
      enabledTypeOptions: [
        { key: 'true', display_name: '啟動' },
        { key: 'false', display_name: '鎖定' }
      ]
    }
  },
  methods: {
    checkPermission,
    // 去查詢
    toQuery() {
      this.$parent.page = 0
      this.$parent.init()
    },
    // 匯出
    download() {
      this.downloadLoading = true
      import('@/vendor/Export2Excel').then(excel => {
        // const tHeader = ['ID', '用戶名', '郵箱', '頭像位址', '狀態', '註冊日期', '最後修改密碼日期']
        // const filterVal = ['id', 'username', 'email', 'avatar', 'enabled', 'createTime', 'lastPasswordResetTime']
        const tHeader = ['ID', '用戶名', '狀態', '註冊日期', '最後修改密碼日期']
        const filterVal = ['id', 'username', 'enabled', 'createTime', 'lastPasswordResetTime']
        const data = this.formatJson(filterVal, this.$parent.data)
        excel.export_json_to_excel({
          header: tHeader,
          data,
          filename: 'table-list'
        })
        this.downloadLoading = false
      })
    },
    // 資料轉換
    formatJson(filterVal, jsonData) {
      return jsonData.map(v => filterVal.map(j => {
        if (j === 'createTime' || j === 'lastPasswordResetTime') {
          return parseTime(v[j])
        } else if (j === 'enabled') {
          return parseTime(v[j]) ? '啟用' : '禁用'
        } else {
          return v[j]
        }
      }))
    }
  }
}
</script>

