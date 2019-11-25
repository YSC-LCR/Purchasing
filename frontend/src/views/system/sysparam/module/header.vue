<template>
  <div class="head-container">
    <el-input v-model="query.paramName" clearable placeholder="參數名稱" style="width: 200px;" class="filter-item" @keyup.enter.native="toQuery"/>
    <el-button class="filter-item" size="mini" type="primary" icon="el-icon-search" @click="toQuery">搜索</el-button>
    <div style="display: inline-block;margin: 0px 2px;">
      <el-button
        class="filter-item"
        size="mini"
        type="primary"
        icon="el-icon-plus"
        @click="resetForm($refs.form)">新增</el-button>
      <eForm ref="form" :is-add="true"/>
    </div>
  </div>
</template>

<script>
import eForm from './form'
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
    }
  },
  methods: {
    resetForm(dialogFrom) {
      //if (document.getElementById('bannerImageFile')) {
      //  document.getElementById('bannerImageFile').value = ''
      //}
      dialogFrom.dialog = true
    },
    toQuery() {
      this.$parent.page = 0
      this.$parent.init()
    }
  }
}
</script>
