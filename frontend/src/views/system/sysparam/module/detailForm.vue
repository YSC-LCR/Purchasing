<template>
  <el-dialog :visible.sync="viewDialog" :title="'系統參數'" append-to-body width="700px">
    <el-form size="small" label-width="120px" >
      <el-form-item label="參數代碼" prop="paramId">
        <span>{{ viewForm.paramId }}</span>
      </el-form-item>
      <el-form-item label="參數名稱" prop="paramName">
        <span>{{ viewForm.paramName }}</span>
      </el-form-item>
      <el-form-item label="參數說明" prop="paramDesc">
        <span>{{ viewForm.paramDesc }}</span>
      </el-form-item>
      <el-form-item label="參數值" prop="paramValue">
        <span>{{ viewForm.paramValue }}</span>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="closeDialog">關閉</el-button>
    </div>
  </el-dialog>
</template>

<script>

export default {
  data() {
    return {
      viewDialog: false,
      loading: false,
      viewForm: {
        paramId: '',
        paramName: '',
        paramDesc: '',
        paramValue: ''
      }
    }
  },
  methods: {
    closeDialog() {
      this.viewDialog = false
    }
  }
}
</script>

<style scoped>
</style>

