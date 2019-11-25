<template>
  <el-dialog :visible.sync="dialog" :title="isAdd ? '新增許可權' : '編輯許可權'" append-to-body width="500px">
    <el-form ref="form" :model="form" :rules="rules" size="small" label-width="80px">
      <el-form-item label="名稱" prop="name">
        <el-input v-model="form.name" style="width: 360px;"/>
      </el-form-item>
      <el-form-item label="別名" prop="alias">
        <el-input v-model="form.alias" style="width: 360px;"/>
      </el-form-item>
      <el-form-item style="margin-bottom: 0px;" label="上級類目">
        <treeselect v-model="form.pid" :options="permissions" style="width: 360px;" placeholder="選擇上級類目" />
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="text" @click="cancel">取消</el-button>
      <el-button :loading="loading" type="primary" @click="doSubmit">確認</el-button>
    </div>
  </el-dialog>
</template>

<script>
import { add, edit } from '@/api/permission'
import Treeselect from '@riophae/vue-treeselect'
import '@riophae/vue-treeselect/dist/vue-treeselect.css'
export default {
  components: { Treeselect },
  props: {
    permissions: {
      type: Array,
      required: true
    },
    isAdd: {
      type: Boolean,
      required: true
    },
    sup_this: {
      type: Object,
      default: null
    }
  },
  data() {
    return {
      loading: false, dialog: false,
      form: { name: '', alias: '', pid: 0 },
      rules: {
        name: [
          { required: true, message: '請輸入名稱', trigger: 'blur' }
        ],
        alias: [
          { required: true, message: '請輸入別名', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    cancel() {
      this.resetForm()
    },
    doSubmit() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          this.loading = true
          if (this.isAdd) {
            this.doAdd()
          } else this.doEdit()
        } else {
          return false
        }
      })
    },
    doAdd() {
      add(this.form).then(res => {
        this.resetForm()
        this.$notify({
          title: '增加成功',
          type: 'success',
          duration: 2500
        })
        this.loading = false
        this.$parent.$parent.init()
        this.$parent.$parent.getPermissions()
      }).catch(err => {
        this.loading = false
        console.log(err.response.data.message)
      })
    },
    doEdit() {
      edit(this.form).then(res => {
        this.resetForm()
        this.$notify({
          title: '修改成功',
          type: 'success',
          duration: 2500
        })
        this.loading = false
        this.sup_this.init()
        this.sup_this.getPermissions()
      }).catch(err => {
        this.loading = false
        console.log(err.response.data.message)
      })
    },
    resetForm() {
      this.dialog = false
      this.$refs['form'].resetFields()
      this.form = { name: '', alias: '', pid: 0 }
    }
  }
}
</script>

<style scoped>

</style>

