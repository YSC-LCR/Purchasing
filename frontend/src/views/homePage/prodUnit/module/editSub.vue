<template>
  <div>
    <el-button size="mini" type="success" @click="to">編輯</el-button>
    <esForm ref="subform" :sup_this="sup_this" :is-add="false" :unitId ="unitId"/>
  </div>
</template>
<script>
import esForm from './formSub'
import { getToken } from '@/utils/auth'
import $ from 'jquery'
const baseUrl = process.env.BASE_API
export default {
  components: { esForm },
  props: {
    data: {
      type: Object,
      required: true
    },
    sup_this: {
      type: Object,
      required: true
    },
    unitId:{
      type: Number,
      required: true
    }
  },
  methods: {
    to() {
      const _sthis = this.$refs.subform
      _sthis.subDialog = true
      _sthis.subform = {
        unitInfoId: this.data.unitInfoId,
        unitInfoName:  this.data.unitInfoName,
        desc: this.data.desc
      }
    }
  }
}
</script>

<style scoped>
  div{display: inline-block;margin-right: 3px;}
</style>
