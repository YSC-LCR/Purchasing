<template>
  <el-dialog :close-on-click-modal="false" :visible.sync="dDialog" title="抽獎結果" append-to-body width="700px">
    <el-form size="small" label-width="120px" >
    <p>活動名稱 : {{sup_this.form.eventName}}</p>
    <p>抽獎區間起日 : {{parseDate(sup_this.form.startDate)}}</p>
    <p>抽獎區間訖日 : {{parseDate(sup_this.form.endDate)}}</p>
    <p>獎品名稱 : {{sup_this.form.giftName}}</p>
    <p>活動名稱 : {{sup_this.form.lotteryNumber}}</p>
    <p>正取 :</p>
    <el-input
      type="textarea"
      autosize
      v-model="sup_this.form.primaryWin">
    </el-input>
    <p>備取 :</p>
    <el-input
      type="textarea"
      autosize
      v-model="sup_this.form.prepareWin">
    </el-input>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="closeDialog">關閉</el-button>
    </div>
  </el-dialog>
</template>

<script>
import { parseDate } from '@/utils/index'
export default {
  props: {
    sup_this: {
      type: Object,
      required: true
    }  
  },
  data() {
    return {
      loading: false,
      dDialog : this.dDialog
    }
  },
  methods: {
    parseDate,
    closeDialog() {
      this.dDialog = false
    },
    doDraw(formData) {
      console.log(formData)
      this.dDialog = true
    },
  }
}
</script>

<style scoped>
</style>
