<template>
  <div>
    <el-button size="mini" type="info" @click="to">檢視</el-button>
    <detailForm ref="viewForm"/>
  </div>
</template>
<script>
import detailForm from './detailForm'
export default {
  components: { detailForm },
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
      const _this = this.$refs.viewForm
      _this.viewForm = {
        paramId: this.data.paramId,
        paramName: this.data.paramName,
        paramDesc: this.data.paramDesc,
        paramValue: this.data.paramValue
      }
      _this.viewDialog = true
    }
  }
}
</script>

<style scoped>
  div{display: inline-block;margin-right: 3px;}
</style>
