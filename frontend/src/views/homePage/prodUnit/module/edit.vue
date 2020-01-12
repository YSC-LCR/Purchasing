<template>
  <div>
    <el-button size="mini" type="success" @click="to">編輯</el-button>
    <eForm ref="form" :sup_this="sup_this" :is-add="false"/>
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
      console.log('==================================')
      console.log('to() start')
      const _this = this.$refs.form
      _this.dialog = true
      _this.form = {
        unitId : this.data.unitId,
        unitName: this.data.unitName
      }
      console.log('to() end')
      console.log('==================================')
    }
  }
}
</script>

<style scoped>
  div{display: inline-block;margin-right: 3px;}
</style>
