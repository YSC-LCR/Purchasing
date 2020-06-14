<template>
  <div>
    <el-button size="mini" type="primary" @click="to">設定規格/價格</el-button>
    <subList ref="subList" :sup_this="sup_this" />
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
      unitId:this.data.unitId
    }
  },
  methods: {
    to() {
      const _this = this.$refs.subList
      _this.form = {
        unitId: this.data.unitId,
        unitName: this.data.unitName
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
