<template>
  <div class="head-container">
    <el-input v-model="query.name" clearable placeholder="卡別名稱" style="width: 200px;" class="filter-item" @keyup.enter.native="toQuery"/>
    <el-button class="filter-item" size="mini" type="primary" icon="el-icon-search" @click="toQuery">搜索</el-button>
    <div style="display: inline-block;margin: 0px 2px;">
      <el-button
        class="filter-item"
        size="mini"
        type="primary"
        icon="el-icon-plus"
        @click="resetForm($refs.form)"
        >新增</el-button>
      <eForm ref="form" :is-add="true"/>
    </div>
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
    query: {
      type: Object,
      required: true
    }
  },
  data() {
    return {
      downloadLoading: false
    }
  },
  methods: {
    toQuery() {
      this.$parent.page = 0
      this.$parent.init()
    },
    resetForm(dialogFrom) {
      const _this = this.$refs.form
      _this.form = {
      }
      dialogFrom.cardTypeDialog = true
    }
  }
}
</script>
