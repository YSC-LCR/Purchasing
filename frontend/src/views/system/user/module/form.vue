<template>
  <el-dialog :visible.sync="dialog" :title="isAdd ? '新增用戶' : '編輯用戶'" append-to-body width="500px">
    <el-form ref="form" :model="form" :rules="rules" size="small" label-width="66px">
      <el-form-item label="用戶名" prop="username">
        <el-input v-model="form.username" style="width: 370px;"/>
      </el-form-item>
      <!-- 
      <el-form-item label="郵箱" prop="email">
        <el-input v-model="form.email" style="width: 370px;"/>
      </el-form-item> 
      -->
      <el-form-item label="狀態" prop="enabled">
        <el-radio v-model="form.enabled" label="true">啟動</el-radio>
        <el-radio v-model="form.enabled" label="false" >鎖定</el-radio>
      </el-form-item>
      <el-form-item style="margin-bottom: 0px;" label="角色">
        <treeselect v-model="roleIds" :multiple="true" :options="roles" style="width: 370px;" placeholder="請選擇角色" />
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="text" @click="cancel">取消</el-button>
      <el-button :loading="loading" type="primary" @click="doSubmit">確認</el-button>
    </div>
  </el-dialog>
</template>

<script>
import { add, edit } from '@/api/user'
import Treeselect from '@riophae/vue-treeselect'
import '@riophae/vue-treeselect/dist/vue-treeselect.css'
export default {
  components: { Treeselect },
  props: {
    roles: {
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
      dialog: false, loading: false, 
      form: { 
        username: '', 
        // email: '', 
        enabled: 'false', 
        roles: [] 
      }, 
      roleIds: [],
      rules: {
        username: [
          { required: true, message: '請輸入用戶名', trigger: 'blur' },
          { min: 3, max: 20, message: '長度在 3 到 20 個字元', trigger: 'blur' }
        ],
        // email: [
        //   { required: true, message: '請輸入郵箱位址', trigger: 'blur' },
        //   { type: 'email', message: '請輸入正確的郵箱位址', trigger: 'blur' }
        // ],
        enabled: [
          { required: true, message: '狀態不能為空', trigger: 'blur' }
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
          this.form.roles = []
          const _this = this
          this.roleIds.forEach(function(data, index) {
            const role = { id: data }
            _this.form.roles.push(role)
          })
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
          message: '預設密碼：123456',
          type: 'success',
          duration: 2500
        })
        this.loading = false
        this.$parent.$parent.init()
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
      }).catch(err => {
        this.loading = false
        console.log(err.response.data.message)
      })
    },
    resetForm() {
      this.dialog = false
      this.$refs['form'].resetFields()
      this.roleIds = []
      this.form = { 
        username: '', 
        // email: '', 
        enabled: 'false', 
        roles: [] 
      }
    }
  }
}
</script>

<style scoped>

</style>

