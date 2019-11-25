<template>
  <div class="head-container">
    <el-input v-model="query.floorName" clearable placeholder="樓層名稱" style="width: 200px;" class="filter-item" @keyup.enter.native="toQuery"/>
    <el-button class="filter-item" size="mini" type="primary" icon="el-icon-search" @click="toQuery">搜索</el-button>
    <div style="display: inline-block;margin: 0px 2px;">
      <el-button
        class="filter-item"
        size="mini"
        type="primary"
        icon="el-icon-plus"
        @click="resetForm($refs.form)"
        :disabled='name=!isCreate'
        >新增</el-button>
      <eForm ref="form" :is-add="true"/>
    </div>
  </div>
</template>

<script>
import eForm from './form'
import { getPermission } from '@/utils/permission'
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
      downloadLoading: false,
      isCreate: this.getPermission(['ADMIN', 'FLOOR_ALL', 'FLOOR_CREATE'])
    }
  },
  methods: {
    getPermission,
    resetForm(dialogFrom) {
      if (document.getElementById('floorImageFile')) {
        document.getElementById('floorImageFile').value = ''
      }
      dialogFrom.dialog = true
    },
    toQuery() {
      this.$parent.page = 0
      this.$parent.init()
    }
  }
}
</script>
