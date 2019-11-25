<template>
  <div class="app-container">
    <div id="tab-demo">
    <ul class="tab-title">
        <li><a href="#tab01(tab1)">tab01</a></li>
        <li><a href="#tab02">tab02</a></li>
        <li><a href="#tab03">tab03</a></li>
    </ul>
    <div id="tab01" class="tab-inner">
        <p>tab01的內容</p>
    </div>
    <div id="tab02" class="tab-inner">
        <p>tab02的內容</p>
    </div>
    <div id="tab03" class="tab-inner">
        <p>tab03的內容</p>
    </div>
</div>
  </div>
</template>

<script>
import { getPermission } from '@/utils/permission'
import store from '@/store'
import $ from 'jquery'

export default {
  //components: { eHeader, edit, detail },
  data() {
    return {
      delLoading: false,
      sup_this: this,
      isReview: this.getPermission(['ADMIN', 'REVIEW']),
      isEdit: this.getPermission(['ADMIN', 'FLOOR_ALL', 'FLOOR_EDIT']),
      isDelete: this.getPermission(['ADMIN', 'FLOOR_ALL', 'FLOOR_DELETE']),
    }
  },
  created() {
    this.$nextTick(() => {
      this.init()
    })
  },
  mounted(){
    var $li = $('ul.tab-title li');
    $($li.eq(0).addClass('active').find('a').attr('href')).siblings('.tab-inner').hide();
  },
  methods: {
    parseTime,
    getPermission,
    liClick() {
      $($(this).find('a').attr('href')).show().siblings('.tab-inner').hide();
            $(this).addClass('active').siblings('.active').removeClass('active');
    },
    beforeInit() {
      this.url = 'homePage/floor/queryAll'
      const sort = 'seq,desc'
      const query = this.query
      const floorName = query.floorName
      this.params = { page: this.page, size: this.size, sort: sort }
      if (floorName) { this.params['floorName'] = floorName }
      return true
    },
    subDelete(seq) {
      this.delLoading = true
      del('floor', seq).then(res => {
        this.delLoading = false
        this.$refs[seq].doClose()
        this.init()
        this.$notify({
          title: '删除成功',
          type: 'success',
          duration: 2500
        })
      }).catch(err => {
        this.delLoading = false
        this.$refs[seq].doClose()
        console.log(err.response.data.message)
      })
    }
  }
}
</script>
<style scoped>

</style>
