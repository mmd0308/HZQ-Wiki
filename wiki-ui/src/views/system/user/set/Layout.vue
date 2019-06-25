
<template>
  <div class="set hzq-wiki-main-width" style="background:blue;">
    <div class="left" style="background: pink">
      <sidebar :create-bar="checkRightTemp" @getRightTemp="getRightTemp" />
    </div>
    <div class="right">
      <el-card>
        <user-set v-if="checkRightTemp === 'personal'" />
        <edit-pass v-if="checkRightTemp === 'pass'" />
      </el-card>
    </div>
  </div>
</template>
<script>
import Sidebar from './components/Sidebar'
import UserSet from './components/UserSet'
import EditPass from './components/EditPass'
export default {
  components: {
    Sidebar,
    UserSet,
    EditPass
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
        width: 200px;
        float: left;
    }
    .right{
        float: right;
        width: 765px;
    }
}
</style>
