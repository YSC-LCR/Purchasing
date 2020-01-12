<template>
  <div class="app-container">
    <el-form ref="form" :show-message="false" :model="form" size="small" label-width="150px" >
      <el-form-item label="抽獎活動" prop="eventId">
        <el-select id="eventId" v-model="form.eventId" clearable>
            <el-option
                v-for="item in form.activeEventOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value">
            </el-option>
        </el-select>
      </el-form-item>    
      <el-form-item label="抽獎區間起日" prop="startDate" required>
        <el-date-picker id="startDate" v-model="form.startDate" type="date" placeholder="選擇日期" format="yyyy-MM-dd"  style="width: 200px;" />       
      </el-form-item>
      <el-form-item label="抽獎區間訖日" prop="endDate" required >
        <el-date-picker id="endDate" v-model="form.endDate" type="date" placeholder="選擇日期" format="yyyy-MM-dd" style="width: 200px;" />
      </el-form-item>
      <el-form-item label="獎品名稱" prop="giftName" required >
        <el-input id="giftName" v-model="form.giftName" style="width: 200px;"/>
      </el-form-item>
      <el-form-item label="抽獎人數" prop="lotteryNumber" required >
        <el-input id="lotteryNumber" v-model="form.lotteryNumber" style="width: 200px;"/>
        <span style="color: green;">備取為正取2倍</span>
      </el-form-item>
      <el-form-item>
        <el-button type="danger" size="mini" @click="cancel">清除</el-button>
        <el-button type="primary" size="mini" @click="doDraw" :loading="delLoading">抽獎</el-button>
      </el-form-item>
      <detail ref="detail" :sup_this="sup_this" />
    </el-form>
  </div>
</template>
<script>
import { parseDate } from '@/utils/index'
import { getToken } from '@/utils/auth'
import axios from 'axios'
import $ from 'jquery'
import detail from './module/detailForm'

const baseUrl = process.env.BASE_API
export default {
  components: { detail },  
  data() {
    return {
      delLoading: false,
      dDialog : false,
      sup_this: this,
      form:{
        startDate: '123',
        endDate: '123',
        eventId: '123',
        eventName:'抽獎測試拉',
        giftName: '13',
        lotteryNumber: '10',
        primaryWin:'123,123',
        prepareWin:'345,345',
        activeEventOptions : []
      }
    }
  },
  created() {
  },
  methods: {
    parseDate,
    cancel(){
       this.$refs['form'].resetFields()
    },
    doDraw() {
      var formData = new FormData()
      formData.append('eventId', this.form.eventId)
      formData.append('startDate',parseDate( this.form.startDate))
      formData.append('endDate', parseDate(this.form.endDate))
      formData.append('giftName', this.form.giftName)
      formData.append('lotteryNumber', this.form.lotteryNumber)
      this.$refs.detail.doDraw(formData);
    },
    findAllAativeEvent() {
        var options = [];
        $.ajax({
            url:  baseUrl + '/homePage/lottery/findAllAativeEvent',
            type: 'GET', //GET
            async: false, //或false,是否非同步
            headers: { "Authorization": 'Bearer ' + getToken() },
            dataType : 'json', // 預期從server接收的資料型態
            success : function(data) {
            $.each(data, function(i, item) {
                var option = {
                    value: item.eventId,
                    label: item.eventName
                }
            options.push(option)
            })
          }
        })
      return options    
    }
  }
}
</script>
<style scoped>

</style>
