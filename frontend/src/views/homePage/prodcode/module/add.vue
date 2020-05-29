<template>
  <div>
    <el-button size="mini" type="success" @click="to">新增商品</el-button>
    <subList ref="form" :sup_this="sup_this" :is-add="true"/>
  </div>
</template>
<script>
import eform from './form'
import { getToken } from '@/utils/auth'
import $ from 'jquery'
const baseUrl = process.env.BASE_API
export default {
  components: { eform },
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
  methods: {
    to() {
      const _this = this.$refs.form
      _this.dialog = true
    }
  }
}
</script>

<style scoped>
  div{display: inline-block;margin-right: 3px;}
</style>
