<template>
  <div>
    <el-button size="mini" type="success" @click="to" :disabled='name=!isEdit'>編輯</el-button>
    <eForm ref="form" :sup_this="sup_this" :is-add="false" />
  </div>
</template>
<script>
import eForm from './form'
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
    },
    isEdit: {
      type: Boolean,
      required: true
    }
  },
  methods: {
    to() {
      const _this = this.$refs.form
      _this.form = {
        seq: this.data.seq,
        floorId: this.data.floorId,
        floorName: this.data.floorName,
        floorIntroduction: this.data.floorIntroduction,
        floorImageUrl: this.data.floorImageUrl,
        createTime: this.data.createTime,
        modifyTime: this.data.modifyTime
      }
      _this.dialog = true
    }
  }
}
</script>

<style scoped>
  div{display: inline-block;margin-right: 3px;}
</style>
