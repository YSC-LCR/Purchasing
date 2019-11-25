<template>
 <div class="app-container">
    <eHeader :sup_this="sup_this"/>
    <p>otp密碼 : {{otp_info}}</p>
    <p>到期日: {{expire_date}}</p>
  </div>
</template>

<script>
import eHeader from './module/header'
export default {
  components: { eHeader},
  data() {
    return {
      loading: false,
      sup_this : this,
      otp_info : '',
      expire_date : ''
    }
  },
  methods: {
  }
}
</script>
