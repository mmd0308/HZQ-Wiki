<template>
  <div class="set hzq-wiki-main-width" style="background:blue;">
    <div class="left" style="background: pink">
      <sidebar :create-bar="checkRightTemp" @getRightTemp="getRightTemp" />
    </div>
    <div class="right">
      <el-card>
        <user v-if="checkRightTemp === 'user'" />
        <login-log v-if="checkRightTemp === 'loginlog'" />
        <config-index v-if="checkRightTemp === 'config'" />
      </el-card>
    </div>
  </div>
</template>
<script>
import Sidebar from './components/Sidebar'
import ConfigIndex from './components/ConfigIndex'
import LoginLog from './components/LoginLog'
import User from '../system/user/index'
export default {
  components: {
    Sidebar,
    User,
    ConfigIndex,
    LoginLog
  },
  data() {
    return {
      checkRightTemp: 'user'
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
