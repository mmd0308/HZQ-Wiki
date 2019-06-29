<template>
  <div class="footer">
    <div class="row  border-top">
      <span><a href="https://mmd0308.github.io/HZQ-Wiki/" target="_blank">官方网站</a></span>
      <span>&nbsp;·&nbsp;</span>
      <span><a href="https://github.com/mmd0308/HZQ-Wiki/issues" target="_blank">意见反馈</a></span>
      <span>&nbsp;·&nbsp;</span>
      <span><a href="https://github.com/mmd0308/HZQ-Wiki" target="_blank">项目源码</a></span>
      <span>&nbsp;·&nbsp;</span>
      <span><a href="https://github.com/mmd0308/HZQ-Wiki" target="_blank">{{ keepToRecord }}</a></span>
    </div>
  </div>
</template>
<script>
import { mapGetters } from 'vuex'
export default {
  computed: {
    ...mapGetters([
      'keepToRecord'
    ])
  }
}
</script>
<style rel="stylesheet/scss" lang="scss" scoped>
.footer {
    line-height: 40px;
    text-align: center;
    .border-top{
        border-top: 2px solid #DDDDD9;
    }
    .row {
        font-size: 13px;
    }

}
</style>
