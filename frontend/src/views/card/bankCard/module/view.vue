<template>
  <div>
    <el-button size="mini" type="info" @click="to">檢視</el-button>
    <detailForm ref="viewForm"/>
  </div>
</template>
<script>
import detailForm from './detailForm'
import { getToken } from '@/utils/auth'
import $ from 'jquery'
const baseUrl = process.env.BASE_API
export default {
  components: { detailForm },
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
  methods: {
    to() {
      const _this = this.$refs.viewForm
      _this.viewForm = {
        bankId: this.data.bankId,
        bankName: this.data.bankName,
        category: this.data.category,
        //bankSettingOptions: this.findAllBankSetting(),
        cardDetailData: this.getCardDetailByBankIdAndCategory(this.data.bankId, this.data.category)
      },
      _this.viewDialog = true
    },
    findAllBankSetting(){
      var options = [];
      var maxLen = 3;

      $.ajax({
        url:  baseUrl + '/card/bankCard/findAllBankSetting',
        type: 'GET', //GET
        async: false, //或false,是否非同步
        headers: { "Authorization": 'Bearer ' + getToken() },
        dataType : 'json', // 預期從server接收的資料型態
        success : function(data) {
          $.each(data, function(i, item) {
            var option = {
              value: item.bankId,
              label: item.bankId + ' ' + item.bankName
            }
            
            options.push(option)
          })
        }
      })
      return options
    },
    getCardDetailByBankIdAndCategory(bankId, category) {
      var cards = []
      $.ajax({
        url: baseUrl + '/card/bankCard/getCardDetailByBankIdAndCategory',
        type: 'POST', //GET
        async: false, //或false,是否非同步
        headers: { "Authorization": 'Bearer ' + getToken() },
        cache: false,
        data: { 
          'bankId': bankId,
          'category': category
        },
        dataType : 'json', // 預期從server接收的資料型態
        success : function(data) {
          $.each(data, function(i, item) {
            cards.push({
              "bankId": item.bankId,
              "cardType": item.cardType,
              "cardTypeName": item.cardTypeName,
              "orgType": item.orgType,
              "orgTypeName": item.orgTypeName,
              "cardBin": item.cardBin,
              "category": item.category
            })
          })
        }
      })
      return cards
    }
  }
}
</script>

<style scoped>
  div{display: inline-block;margin-right: 3px;}
</style>
