<template>
  <div class="app-wrapper">
    <el-container>
      <el-header>
        <navbar class="hzq-wiki-main-width"/>
      </el-header>
      <el-main class="hzq-wiki-main-width">
        <app-main />
      </el-main>
      <el-footer class="hzq-wiki-main-width">
        <w-footer />
      </el-footer>
    </el-container>
  </div>
</template>

<script>
import { Navbar, AppMain, Footer } from './components'
export default {
  name: 'Layout',
  components: {
    Navbar,
    AppMain,
    WFooter: Footer
  }
}
</script>
<style rel="stylesheet/scss" lang="scss" scoped>
@import "src/styles/mixin.scss";
.el-footer {
  padding: 20px 0px 0px 0px;
}
.el-main {
  padding: 20px 0px 0px 0px;
}

</style>
