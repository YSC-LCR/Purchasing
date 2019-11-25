<template>
  <el-dialog :visible.sync="viewDialog" :title="'樓層內容'" append-to-body width="700px">
    <el-form size="small" label-width="120px" >
      <el-form-item label="流水號" prop="seq">
        <span>{{ viewForm.seq }}</span>
      </el-form-item>
      <el-form-item label="樓層代碼" prop="floorId">
        <span>{{ viewForm.floorId }}</span>
      </el-form-item>
      <el-form-item label="樓層名稱" prop="floorName">
        <span>{{ viewForm.floorName }}</span>
      </el-form-item>
      <el-form-item label="樓層簡介" prop="floorIntroduction">
        <span>{{ viewForm.floorIntroduction }}</span>
      </el-form-item>
      <el-form-item label="已上傳的icon">
        <img :src="viewForm.floorImageUrl" style="width: 500px; height: 120px;" />
      </el-form-item>
      <el-form-item label="新建時間" prop="createTime">
        <span>{{ parseTime(viewForm.createTime) }}</span>
      </el-form-item>
      <el-form-item label="修改時間" prop="modifyTime">
        <span>{{ parseTime(viewForm.modifyTime) }}</span>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="closeDialog">關閉</el-button>
    </div>
  </el-dialog>
</template>

<script>
import { parseTime } from '@/utils/index'

export default {
  data() {
    return {
      viewDialog: false,
      loading: false,
      viewForm: {
        seq: '0',
        floorId: '',
        floorName: '',
        floorIntroduction: '',
        floorImageUrl: '',
        createTime: '',
        modifyTime: ''
      }
    }
  },
  methods: {
    parseTime,
    closeDialog() {
      this.viewDialog = false
    }
  }
}
</script>

<style scoped>
</style>

