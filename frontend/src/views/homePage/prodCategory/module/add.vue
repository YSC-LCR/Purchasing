<template>
  <div>
    <el-button size="mini" type="primary" @click="to">設定子類別</el-button>
    <subList ref="subList" :sup_this="sup_this" :category-id="categoryId" />
  </div>
</template>
<script>
import subList from './subList'
import { getToken } from '@/utils/auth'
import $ from 'jquery'
const baseUrl = process.env.BASE_API
export default {
  components: { subList },
  props: {
    data: {
      type: Object,
      required: true
    },
    sup_this: {
      type: Object,
      required: true
    }
  },
  data(){
    return{
      categoryId:this.data.categoryId
    }
  },
  methods: {
    to() {
      const _this = this.$refs.subList
      _this.form = {
        categoryId: this.data.categoryId,
        categoryName: this.data.categoryName
      }
      _this.initList()
      _this.viewEventDialog = true
      // _this.beforeInitList()
    }
  }
}
</script>

<style scoped>
  div{display: inline-block;margin-right: 3px;}
</style>
