<template>
  <div>
    <el-button size="mini" type="success" @click="to">編輯</el-button>
    <eForm ref="form" :sup_this="sup_this" :is-add="false" />
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
  data() {
    return {
      downloadLoading: false
    }
  },
  methods: {
    to() {
      const _this = this.$refs.form
      _this.form = {
        id: this.data.id,
        name: this.data.name
      }
      _this.cardTypeDialog = true
    }
  }
}
</script>

<style scoped>
  div{display: inline-block;margin-right: 3px;}
</style>
