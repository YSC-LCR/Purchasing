<template>
  <div>
    <!----------------------  新增發卡銀行/機構 dialog ---------------------->
    <!----------------------  新增發卡銀行/機構 dialog ---------------------->
    <!----------------------  新增發卡銀行/機構 dialog -------------------- -->
    <el-dialog :visible.sync="bankDialog" :title="isAdd ? '新增發卡銀行 / 機構 / 卡片' : '編輯發卡銀行 / 機構 / 卡片'" append-to-body width="800px">
      <el-form ref="form" :show-message="false" :model="form" size="small" label-width="120px" >
        <el-form-item label="發卡單位" prop="bankId" required>
          <el-select id="bankId" :disabled="name=!isAdd" v-model="form.bankId" clearable style="width: 200px;">
            <el-option
              v-for="item in form.bankSettingOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="卡片類型" prop="category" required >
          <el-select id="category" :disabled="name=!isAdd" v-model="form.category" clearable style="width: 200px;">
            <el-option value="0" label="信用卡"></el-option>
            <el-option value="1" label="聯名卡"></el-option>
          </el-select>
        </el-form-item>
        <div style="display: inline-block;margin: 0px 2px;">
          <el-button
            class="filter-item"
            size="mini"
            type="primary"
            icon="el-icon-plus"
            @click="showCardDialog(true)">新增卡片</el-button>
        </div>
        <br/><br/>
        <!---------------------- dataTable ---------------------->
        <!---------------------- dataTable ---------------------->
        <el-table v-loading="loading" :data="form.cardDetailData" size="small" border stripe style="width: 100%;">
          <el-table-column prop="cardTypeName" label="卡別" min-width="25%" align="center"/>
          <el-table-column prop="orgTypeName" label="發卡組織" min-width="25%" align="center"/>
          <el-table-column prop="cardBin" label="CARD BIN" min-width="25%" align="center"/>
          <el-table-column prop="operation" label="操作" min-width="25%" align="center">
            <template slot-scope="scope">
              <el-button slot="reference" type="warning" size="mini" @click="editCardDetail(scope.$index)">編輯</el-button>
              <el-button slot="reference" type="danger" size="mini" @click="deleteCardDetail(scope.$index)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <!---------------------- dataTable ---------------------->
        <!---------------------- dataTable ---------------------->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="text" @click="cancelBankDialog()">取消</el-button>
        <el-button :loading="loading" type="primary" @click="addBankAndCard()">確認</el-button>
      </div>
    </el-dialog>
    <!---------------------- 卡片 dialog ---------------------->
    <!---------------------- 卡片 dialog ---------------------->
    <!---------------------- 卡片 dialog ---------------------->
    <el-dialog :visible.sync="cardDialog" :title="isAddCard ? '新增卡片' : '編輯卡片'" append-to-body width="500px">
      <el-form ref="cardForm" :model="cardForm" :show-message="false" size="small" label-width="120px" >
        <el-form-item label="卡別" >
          <el-select id="cardType" v-model="cardForm.cardType" clearable style="width: 200px;">
            <el-option
              v-for="item in cardTypeSettingOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="發卡組織" prop="orgType">
          <el-select id="orgType" v-model="cardForm.orgType" clearable style="width: 200px;">
            <el-option
              v-for="item in orgOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="CARD BIN" prop="cardBin">
          <el-input id="cardBin" v-model="cardForm.cardBin" maxlength="8" style="width: 200px;"/>
        </el-form-item>
        <el-input type="hidden" id="rowIndex" v-model="rowIndex"/>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="text" @click="cancelCardDialog()">取消</el-button>
        <el-button type="primary" @click="addCard()">確認</el-button>
      </div>
    </el-dialog>
    <!---------------------- 卡片 dialog ---------------------->
    <!---------------------- 卡片 dialog ---------------------->
    <!---------------------- 卡片 dialog ---------------------->
  </div>
</template>

<script>
import { getToken } from '@/utils/auth'
import $ from 'jquery'
const baseUrl = process.env.BASE_API
export default {
  components: { },
  props: {
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
      bankDialog: false,
      cardDialog: false,
      isAddCard: false,
      loading: false,
      rowIndex: 0,
      form: {
        bankId: 0,
        bankName: '',
        category: '',
        cardDetailData: [],
        bankSettingOptions: []
      },
      
      cardForm: {
        cardType: '',
        orgType: '',
        cardBin: ''
      },
      orgOptions: [
        { value: 5, label: 'Master' }, 
        { value: 6, label: 'Visa'}, 
        { value: 7, label: 'JCB'}
      ],
      cardTypeSettingOptions: [],
      //bankCardSettingMap: this.initBankCardSettingMap(),
      //cardTypeSettingMap: this.initCardTypeSettingMap()
      cardSettingTypeMap: null,
      cardTypeSettingMap: null
    }
  },
 
  methods: {
    showCardDialog(isAddCard) {
      this.isAddCard = isAddCard
      if(isAddCard){
        this.clearCardDialog()
      }
      this.cardDialog = true
    },   
    editCardDetail(index){
      console.log("cardDetailData:", this.form.cardDetailData)
      this.rowIndex = index
      this.cardForm.cardType = this.form.cardDetailData[index].cardType
      this.cardForm.orgType = this.form.cardDetailData[index].orgType
      this.cardForm.cardBin = this.form.cardDetailData[index].cardBin
      this.showCardDialog(false)
      this.cardDialog = true
    },
    deleteCardDetail(index){
      console.log("index:", index);
      this.form.cardDetailData.splice(index, 1); 
      console.log(this.form.cardDetailData)
    },
    cancelBankDialog() {
      this.resetForm()
    },
    addBankAndCard() {
      if(!this.checkBankAndCardData()){
        return false;
      }
      this.loading = true
      
      try {
        if(this.isAdd){
          this.doCreate();
        }else{
          this.doUpdate();
        }
        this.clearCardDialog()
        this.cardDialog = false
        this.form.cardDetailData = []
        this.bankDialog = false
        if(this.isAdd){
          this.$parent.$parent.init()
        }else{
          this.sup_this.init()
        }       
        this.resetForm()
      } catch(e) {  
        console.log('catch:', e)
      } finally {
        this.loading = false
      }
    },
    doCreate(){
      var formData = new FormData()
      formData.append('bankId', this.form.bankId)
      formData.append('category', this.form.category)
      var cardDetailDataJSON = JSON.stringify(this.form.cardDetailData);
      formData.append('cardDetailData', cardDetailDataJSON)   
      var thisObj = this
      $.ajax({
        url:baseUrl + '/card/bankCard/create',
        type:'POST', //GET
        async:false, //是否非同步
        processData : false, 
        contentType : false,
        cache: false,
        data: formData,
        headers: {
          "Authorization": 'Bearer ' + getToken()
        },
        timeout:5000,    //超时时间
        dataType:'json',    //返回的数据格式：
        success:function(data, textStatus, jqXHR){
          if (data.code === '0000') {
            thisObj.$notify({
              title: '新增成功',
              type: 'success',
              duration: 2500
            })
          } else {
            thisObj.$notify({
              title: '新增失敗',
              type: 'error',
              duration: 2500
            })
          }
        },
        error:function(xhr, textStatus){
          thisObj.$notify({
            title: '新增失敗',
            type: 'error',
            duration: 2500
          })
        }
      })
    }, 
    doUpdate(){
      var formData = new FormData()
      formData.append('bankId', this.form.bankId)
      formData.append('category', this.form.category)
      var cardDetailDataJSON = JSON.stringify(this.form.cardDetailData);
      formData.append('cardDetailData', cardDetailDataJSON)   
      var thisObj = this
      $.ajax({
        url:baseUrl + '/card/bankCard/update',
        type:'POST', //GET
        async:false, //是否非同步
        processData : false, 
        contentType : false,
        cache: false,
        data: formData,
        headers: {
          "Authorization": 'Bearer ' + getToken()
        },
        timeout:5000,    //超时时间
        dataType:'json',    //返回的数据格式：
        success:function(data, textStatus, jqXHR){
          if (data.code === '0000') {
            thisObj.$notify({
              title: '編輯成功',
              type: 'success',
              duration: 2500
            })
          } else {
            thisObj.$notify({
              title: '編輯失敗',
              type: 'error',
              duration: 2500
            })
          }
        },
        error:function(xhr, textStatus){
          thisObj.$notify({
            title: '編輯失敗',
            type: 'error',
            duration: 2500
          })
        }
      })
    },
    resetForm() {
      this.bankDialog = false
      this.$refs['form'].resetFields()
    },
    cancelCardDialog(){
      this.clearCardDialog()
      this.cardDialog = false
    },
    addCard(){
      //檢查卡片資料
      if(!this.checkCardData()){
        return false
      }
      console.log("cardBin:",this.cardForm.cardBin, 
        "cardType:",this.cardForm.cardType, "orgType:",this.cardForm.orgType)
        for(var i = 0; i < this.form.cardDetailData.length; i++) {
          if(this.cardForm.cardBin === this.form.cardDetailData[i].cardBin){
            this.$notify({
              title: 'CARD BIN已存在',
              type: 'error',
              duration: 2500
            })
            return false
          }
        };
      if(this.isAddCard){  
        this.form.cardDetailData.push({
          "cardType": this.cardForm.cardType,
          "orgType": this.cardForm.orgType,
          "cardBin": this.cardForm.cardBin,
          "cardTypeName": this.cardTypeSettingMap.get(this.cardForm.cardType),
          "orgTypeName": this.cardSettingTypeMap.get(this.cardForm.orgType)
        })
      }else{
        this.form.cardDetailData[this.rowIndex].cardType = this.cardForm.cardType,
        this.form.cardDetailData[this.rowIndex].orgType = this.cardForm.orgType,
        this.form.cardDetailData[this.rowIndex].cardBin = this.cardForm.cardBin,
        this.form.cardDetailData[this.rowIndex].cardTypeName = this.cardTypeSettingMap.get(this.cardForm.cardType),
        this.form.cardDetailData[this.rowIndex].orgTypeName = this.cardSettingTypeMap.get(this.cardForm.orgType)
      }
      this.clearCardDialog()
      this.cardDialog = false
    },
    clearCardDialog(){
      this.cardForm.cardType = ''
      this.cardForm.orgType = ''
      this.cardForm.cardBin = ''
    },
    checkCardData() {
      if (!this.cardForm.cardType) {
        this.$notify({
          title: '請選擇卡別',
          type: 'error',
          duration: 2500
        })
        return false
      }
      if (!this.cardForm.orgType) {
        this.$notify({
          title: '請選擇發卡組織',
          type: 'error',
          duration: 2500
        })
        return false
      }
      console.log("this.cardForm.cardBin", this.cardForm.cardBin);
      var re = /^[0-9]{6,8}$/;
      if (!this.cardForm.cardBin || !re.test(this.cardForm.cardBin)) {
        this.$notify({
          title: '請確認CARD BIN, 格式:6~8碼數字',
          type: 'error',
          duration: 2500
        })
        return false
      }
      
      return true
    },
    checkBankAndCardData(){
       if (!this.form.bankId) {
        this.$notify({
          title: '請選擇發卡銀行',
          type: 'error',
          duration: 2500
        })
        return false
      }
      if (!this.form.category) {
        this.$notify({
          title: '請選擇卡片類型',
          type: 'error',
          duration: 2500
        })
        return false
      }
      if(this.isAdd && this.form.cardDetailData.length === 0){
        this.$notify({
          title: '請新增卡片',
          type: 'error',
          duration: 2500
        })
        return false
      }
      return true
    }
  }
}
</script>

<style scoped>
</style>
