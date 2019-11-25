<template>
  <div>
    <el-button size="mini" type="success" @click="to()">編輯</el-button>
    <eForm ref="form" :sup_this="sup_this" :is-add="false"/>
  </div>
</template>
<script>
import eForm from './form'
import { findAllBankSetting, findAllCardTypeSetting, getCardDetailByBankIdAndCategory, initCardTypeSettingMap, getCardSettingByType} from '@/api/card'
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
    }
  },
  data() {
    return {
    }
  },
  methods: {
    to() {
      const _this = this.$refs.form
      _this.form = {
        bankId: this.data.bankId,
        bankName: this.data.bankName,
        category: this.data.category,
        bankSettingOptions: findAllBankSetting(),
        cardDetailData: getCardDetailByBankIdAndCategory(this.data.bankId, this.data.category)
      },
      _this.cardTypeSettingOptions = findAllCardTypeSetting(),
      _this.cardTypeSettingMap = initCardTypeSettingMap(),
      _this.cardSettingTypeMap = getCardSettingByType("org")
      _this.bankDialog = true
    },
    
  }
}
</script>

<style scoped>
  div{display: inline-block;margin-right: 3px;}
</style>
