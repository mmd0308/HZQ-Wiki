<template>
  <div class="app-wrapper">
    <el-container>
      <el-header>
        <navbar/>
      </el-header>
      <el-main>
        <app-main />
      </el-main>
    </el-container>

  </div>
</template>

<script>
import { Navbar, AppMain } from './components'
export default {
  name: 'Layout',
  components: {
    Navbar,
    AppMain
  },
  props: {
    docId: {
      type: Number,
      required: true
    }
  },
  data() {
    return {

    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
@import "src/styles/mixin.scss";

.el-footer {
  background-color: #b3c0d1;
  color: #333;
  text-align: center;
  line-height: 60px;
}

// .app-wrapper {
//   @include clearfix;
//   position: relative;
//   height: 100%;
//   width: 100%;
//   &.mobile.openSidebar {
//     position: fixed;
//     top: 0;
//   }
// }
// .drawer-bg {
//   background: #000;
//   opacity: 0.3;
//   width: 100%;
//   top: 0;
//   height: 100%;
//   position: absolute;
//   z-index: 999;
// }
</style>
