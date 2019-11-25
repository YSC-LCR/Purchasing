<template>
  <div class="head-container">
    <el-input v-model="query.bankName" clearable placeholder="發卡單位" style="width: 200px;" class="filter-item" @keyup.enter.native="toQuery"/>
    <el-button class="filter-item" size="mini" type="primary" icon="el-icon-search" @click="toQuery">搜索</el-button>
    <div style="display: inline-block;margin: 0px 2px;">
      <el-button
        class="filter-item"
        size="mini"
        type="primary"
        icon="el-icon-plus"
        @click="resetForm()"
        >新增</el-button><!-- :disabled='name=!isCreate' :cardTypeSettingOptions="cardTypeSettingOptions" -->
      <eForm ref="form" :is-add="true" />
    </div>
  </div>
</template>

<script>
import eForm from './form'
import { findAllBankSetting, findAllCardTypeSetting, initCardTypeSettingMap, getCardSettingByType} from '@/api/card'

//import { getPermission } from '@/utils/permission'
export default {
  components: { eForm },
  props: {
    query: {
      type: Object,
      required: true
    }
  },
  data() {
    return {
      //isCreate: this.getPermission(['ADMIN', 'BANKCARD_ALL'])
      //cardTypeSettingOptions: this.findAllCardTypeSetting()
    }
  },
  methods: {
    //getPermission,
    toQuery() {
      this.$parent.page = 0
      this.$parent.init()
    },
    resetForm() {
      const _this = this.$refs.form
      _this.form = {
        bankSettingOptions: findAllBankSetting(),
        cardDetailData: []
      }
      _this.cardTypeSettingOptions = findAllCardTypeSetting(),
      _this.cardTypeSettingMap = initCardTypeSettingMap(),
      _this.cardSettingTypeMap = getCardSettingByType("org")
      _this.bankDialog = true
    }
  }
}
</script>
