<template>
  <div>
    <el-button size="mini" type="success" @click="to">編輯</el-button>
    <editForm ref="form" :sup_this="sup_this" :is-add="false"/> <!-- -->
  </div>
</template>
<script>
import editForm from './editForm'
export default {
  components: { editForm },
  props: {
    data: {
      type: Object,
      required: true
    }
    ,
    sup_this: {
      type: Object,
      required: true
    }
  },
  methods: {
    to() {
      const _this = this.$refs.form
      _this.dialog = true
      _this.form = {
        paramId: this.data.paramId,
        paramName: this.data.paramName,
        paramDesc: this.data.paramDesc,
        paramValue: this.data.paramValue
      }

    }
  }
}
</script>

<style scoped>
  div{display: inline-block;margin-right: 3px;}
</style>
