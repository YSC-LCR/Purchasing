<template>
  <div style="display: inline-block">
    <el-button size="mini" class="button" type="info" @click="dialog = true">修改</el-button>
    <el-dialog :visible.sync="dialog" :title="title" append-to-body width="500px" @close="cancel">
      <el-form ref="form" :model="form" :rules="rules" size="small" label-width="88px">
        <el-form-item label="舊密碼" prop="oldPass">
          <el-input v-model="form.oldPass" type="password" auto-complete="on" style="width: 370px;"/>
        </el-form-item>
        <el-form-item label="新密碼" prop="newPass">
          <el-input v-model="form.newPass" type="password" auto-complete="on" style="width: 370px;"/>
        </el-form-item>
        <el-form-item label="確認密碼" prop="confirmPass">
          <el-input v-model="form.confirmPass" type="password" auto-complete="on" style="width: 370px;"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="text" @click="cancel">取消</el-button>
        <el-button :loading="loading" type="primary" @click="doSubmit">確認</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import store from '@/store'
import { md5 } from '@/utils/md5'
import { validPass, updatePass } from '@/api/user'
export default {
  data() {
    const validatePass = (rule, value, callback) => {
      validPass(md5(value)).then(res => {
        if (res.status === 200) {
          callback()
        } else {
          callback(new Error('舊密碼錯誤，請檢查'))
        }
      })
    }
    const confirmPass = (rule, value, callback) => {
      if (this.form.newPass !== value) {
        callback(new Error('兩次輸入的密碼不一致'))
      } else {
        callback()
      }
    }
    return {
      loading: false, dialog: false, title: '修改密碼', form: { oldPass: '', newPass: '', confirmPass: '' },
      rules: {
        oldPass: [
          { required: true, validator: validatePass, trigger: 'blur' }
        ],
        newPass: [
          { required: true, message: '請輸入新密碼', trigger: 'blur' },
          { min: 6, max: 20, message: '長度在 6 到 20 個字元', trigger: 'blur' }
        ],
        confirmPass: [
          { required: true, validator: confirmPass, trigger: 'blur' }
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
          updatePass(md5(this.form.confirmPass)).then(res => {
            this.resetForm()
            this.$notify({
              title: '密碼修改成功，請重新登錄',
              type: 'success',
              duration: 1500
            })
            setTimeout(() => {
              store.dispatch('LogOut').then(() => {
                location.reload() // 為了重新產生實體vue-router物件 避免bug
              })
            }, 1500)
          }).catch(err => {
            this.loading = false
            console.log(err.response.data.message)
          })
        } else {
          return false
        }
      })
    },
    resetForm() {
      this.dialog = false
      this.$refs['form'].resetFields()
      this.form = { oldPass: '', newPass: '', confirmPass: '' }
    }
  }
}
</script>

<style scoped>

</style>

