
<template>
  <div class="set hzq-wiki-main-width" style="background:blue;">
    <div class="left" style="background: pink">
      <hw-article />
    </div>
    <div class="right">
      <sidebar :create-bar="checkRightTemp" @getRightTemp="getRightTemp" />
    </div>
  </div>
</template>
<script>
import Sidebar from './components/Sidebar'
import HwArticle from './components/Article'
export default {
  components: {
    Sidebar,
    HwArticle
  },
  data() {
    return {
      checkRightTemp: 'personal'
    }
  },
  methods: {
    getRightTemp(data) {
      this.checkRightTemp = data
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.set {
    .left{
        width: 787px;
        float: left;
    }
    .right{
        float: right;
        width: 180px;
    }
}
</style>
