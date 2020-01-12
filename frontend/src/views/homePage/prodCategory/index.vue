<template>
  <div class="app-container">
    <eHeader :query="query" :sup_this="sup_this" />
    <!--表格渲染  -->
    <el-table v-loading="loading" :data="data" size="small" border stripe style="width: 100%;">
      <el-table-column prop="categoryName" label="主類名稱" min-width="30%" align="center"/>
      <el-table-column label="操作" min-width="50%" align="center">
        <template slot-scope="scope">
          <edit :data="scope.row" :sup_this="sup_this" />
          <el-popover :ref="scope.row.categoryId" placement="top">
            <p>確定刪除嗎？</p>
            <div style="text-align: right; margin: 0">
              <el-button size="mini" type="text" @click="$refs[scope.row.categoryId].doClose()">取消</el-button>
              <el-button :loading="delLoading" type="primary" size="mini" @click="subDelete(scope.row.categoryId)">確定</el-button>
            </div>
            <el-button slot="reference" type="danger" size="mini">删除</el-button>
          </el-popover>
          <addSub :data="scope.row" :sup_this="sup_this" />
        </template>

      </el-table-column>
    </el-table>
    <!--分頁組件-->
    <el-pagination
      :total="total"
      style="margin-top: 8px;"
      layout="total, prev, pager, next, sizes"
      @size-change="sizeChange"
      @current-change="pageChange"
    />
  </div>
</template>
<script>
import { del } from "@/api/common";
import edit from './module/edit'
import eHeader from "./module/header";
import initData from "@/mixins/initData";
import { getToken } from "@/utils/auth";
import { mapGetters } from "vuex";
import addSub from './module/add'
import {
  parseTime,
  parseDate,
} from "@/utils/index";
import Cookies from "js-cookie";

const baseUrl = process.env.BASE_API;

export default {
  mixins: [initData],
  data() {
    return {
      delLoading: false,
      sup_this: this
    };
  },
  created() {
    this.$nextTick(() => {
      this.init();
    });
  },
  components: {
    eHeader,edit,addSub
  },
  computed: {
    ...mapGetters(["name"])
  },
  methods: {
    parseTime,
    parseDate,
   
    beforeInit() {
      this.url = "homePage/prod/prodCategory/queryAll";
      const sort = "categoryId,desc";
      const query = this.query;
      const column = query.column;
      const columnValue = query.columnValue;
      this.params = { page: this.page, size: this.size, sort: sort };
      this.params["columnValue"] = columnValue;
      // if (columnValue) {
      //   this.params["columnValue"] = this.convertDisplayStatus(column, columnValue);
      // }
      return true;
    },
    subDelete(categoryId) {
      this.delLoading = true
      del('homePage/prod/prodCategory', categoryId).then(res => {
        this.delLoading = false
        this.$refs[categoryId].doClose()
        this.init()
        this.$notify({
          title: '删除成功',
          type: 'success',
          duration: 2500
        })
      }).catch(err => {
        this.delLoading = false
        this.$refs[categoryId].doClose()
        console.log(err.response.data.message)
      })
    }
  }
};
</script>

<style scoped>

 .el-button {
  /* color: white; */
  margin-top: 5px;
  width: 90px;
 }
</style>
