<template>
  <el-dialog :visible.sync="viewDialog" title="發卡銀行 / 機構 / 卡片" append-to-body width="700px">
    <el-form size="small" label-width="120px" >
      <el-form-item label="發卡單位" prop="seq">
        <span>{{ viewForm.bankName }}</span>
      </el-form-item>
      <el-form-item label="卡片類型" prop="floorId">
        <span>{{ viewForm.category === '0'?'信用卡':'聯名卡' }}</span>
      </el-form-item>
        <!---------------------- dataTable ---------------------->
        <!---------------------- dataTable -------------------- v-model="form.cardDetailData" -->
        <el-table v-loading="loading" :data="viewForm.cardDetailData" size="small" border stripe style="width: 100%;">
          <el-table-column prop="cardTypeName" label="卡別" min-width="25%" align="center"/>
          <el-table-column prop="orgTypeName" label="發卡組織" min-width="25%" align="center"/>
          <el-table-column prop="cardBin" label="CARD BIN" min-width="25%" align="center"/>
        </el-table>
        <!---------------------- dataTable ---------------------->
        <!---------------------- dataTable ---------------------->
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
        bankId: '',
        bankName: '',
        category: '',
        cardDetailData: []
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

