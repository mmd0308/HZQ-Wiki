<template>
  <div class="dashboard-container">
    <doc-dashbord />
  </div>
</template>
<script>
import DocDashbord from '../doc/index'
export default {
  name: 'Dashboard',
  components: {
    DocDashbord
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.dashboard {
  &-container {
    padding: 1px 00px 40px 00px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
</style>
