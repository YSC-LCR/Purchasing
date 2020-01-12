<template>
  <div class="head-container">
  <div style="display: inline-block;margin: 0px 2px;">
      <el-button
        class="filter-item"
        size="mini"
        type="primary"
        icon="el-icon-plus"
        @click="resetForm($refs.subform)">新增</el-button>
      <esForm ref="subform" :is-add="true" :unitId ="unitId"/>
  </div>
  <div class='popContainer' v-show="showFlag">loading</div>
  </div>
</template>

<script>
import esForm from './formSub'
import { getToken } from '@/utils/auth'
const baseUrl = process.env.BASE_API
export default {
  components: { esForm },
  props: {
    sup_this: {
      type: Object,
      required: true
    },
    unitId:{
      type: Number,
      required: true
    }
  },
  data() {
    return {
      showFlag: false,
      delLoading: false,
      downloadLoading: false,
    }
  },
  methods: {
    resetForm(dialogFrom) {
      const _sthis = this.$refs.subform
      _sthis.subform = {
        unitInfoId:'',
        unitInfoName: '',
        desc:''
      }
      dialogFrom.subDialog = true
    },
  }
}
</script>
<style scoped>
div.popContainer{
    position: fixed;
    top:0;
    left:0;
    width:100%;
    height:100%;
    background-color:rgba(0,0,0,0.7);
    z-index:99;
}
</style>