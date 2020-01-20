<template>
  <div class="index-container">
    <doc-dashbord />
  </div>
</template>
<script>
import DocDashbord from '../doc/index'
export default {
  name: 'Index',
  components: {
    DocDashbord
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.index {
  &-container {
    padding: 1px 00px 40px 00px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
</style>
