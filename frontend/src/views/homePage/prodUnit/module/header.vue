<template>
  <div class="head-container">
    <el-input v-model="query.columnValue" clearable style="width: 200px;" class="filter-item" @keyup.enter.native="toQuery"/>
       <!-- <el-select v-model="query.column" class="filter-item" style="width: 150px">
          <el-option v-for="item in columnTypeOptions" :key="item.key" :label="item.display_name" :value="item.key"  />
       </el-select> -->
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
  <div class='popContainer' v-show="showFlag">loading</div>
  </div>
</template>

<script>
import eForm from './form'
import { getToken } from '@/utils/auth'
const baseUrl = process.env.BASE_API
export default {
  components: { eForm },
  props: {
    query: {
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
      showFlag: false,
      delLoading: false,
      downloadLoading: false,
    }
  },
  methods: {
    toQuery() {
      if(!(this.query.columnValue)){
        console.log('欄位值為空')
        this.$notify({
        title: '搜尋欄位必需填入值',
        type: 'error',
        duration: 2500
        })
        return false
      }
        this.$parent.page = 0
        this.$parent.init()
    },
    resetForm(dialogFrom) {
      const _this = this.$refs.form
      _this.form = {
        unitName: ''
      }
      dialogFrom.dialog = true
    },
  }
}
</script>
<style scoped>
div.popContainer{
    position: fixed;
    top:0;
    left:0;
    width:100%;
    height:100%;
    background-color:rgba(0,0,0,0.7);
    z-index:99;
}
</style>