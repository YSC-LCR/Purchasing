<template>
  <el-dialog :visible.sync="dialog" :title="isAdd ? '新增選單' : '編輯選單'" append-to-body width="600px">
    <el-form ref="form" :model="form" :rules="rules" size="small" label-width="80px">
      <el-form-item label="功能表圖示">
        <el-popover
          placement="bottom-start"
          width="460"
          trigger="click"
          @show="$refs['iconSelect'].reset()">
          <IconSelect ref="iconSelect" @selected="selected" />
          <el-input slot="reference" v-model="form.icon" style="width: 460px;" placeholder="點擊選擇圖示" readonly>
            <svg-icon v-if="form.icon" slot="prefix" :icon-class="form.icon" class="el-input__icon" style="height: 32px;width: 16px;" />
            <i v-else slot="prefix" class="el-icon-search el-input__icon"/>
          </el-input>
        </el-popover>
      </el-form-item>
      <el-form-item label="選單名稱" prop="name">
        <el-input v-model="form.name" placeholder="名稱" style="width: 460px;"/>
      </el-form-item>
      <el-form-item label="選單排序" prop="sort">
        <el-input v-model.number="form.sort" placeholder="序號越小越靠前" style="width: 460px;"/>
      </el-form-item>
      <el-form-item label="內部選單" prop="iframe">
        <el-radio v-model="form.iframe" label="false">是</el-radio>
        <el-radio v-model="form.iframe" label="true" >否</el-radio>
      </el-form-item>
      <el-form-item label="連結位址">
        <el-input v-model="form.path" placeholder="功能表路徑" style="width: 460px;"/>
      </el-form-item>
      <el-form-item v-if="form.iframe === 'false'" label="元件路徑">
        <el-input v-model="form.component" placeholder="功能表路徑" style="width: 460px;"/>
      </el-form-item>
      <el-form-item label="上級類目">
        <treeselect v-model="form.pid" :options="menus" style="width: 460px;" placeholder="選擇上級類目" />
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="text" @click="cancel">取消</el-button>
      <el-button :loading="loading" type="primary" @click="doSubmit">確認</el-button>
    </div>
  </el-dialog>
</template>

<script>
import { add, edit } from '@/api/menu'
import Treeselect from '@riophae/vue-treeselect'
import IconSelect from '@/components/IconSelect'
import '@riophae/vue-treeselect/dist/vue-treeselect.css'
export default {
  components: { Treeselect, IconSelect },
  props: {
    menus: {
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
      form: { name: '', sort: 999, path: '', component: '', iframe: 'false', roles: [], pid: 0, icon: '' },
      rules: {
        name: [
          { required: true, message: '請輸入名稱', trigger: 'blur' }
        ],
        sort: [
          { required: true, message: '請輸入序號', trigger: 'blur', type: 'number' }
        ],
        iframe: [
          { required: true, message: '請選擇功能表類型', trigger: 'blur' }
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
        this.$parent.$parent.getMenus()
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
        this.sup_this.getMenus()
      }).catch(err => {
        this.loading = false
        console.log(err.response.data.message)
      })
    },
    resetForm() {
      this.dialog = false
      this.$refs['form'].resetFields()
      this.form = { name: '', sort: 999, path: '', component: '', iframe: 'false', roles: [], pid: 0, icon: '' }
    },
    selected(name) {
      this.form.icon = name
    }
  }
}
</script>

<style scoped>

</style>

