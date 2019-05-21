<template>
  <div class="set hzq-wiki-main" style="background:blue;">
    <div class="left" style="background: pink">
      <sidebar :create-bar="checkRightTemp" @getRightTemp="getRightTemp" />
    </div>
    <div class="right">
      <el-card>
        <user-set v-if="checkRightTemp === 'personal'" />
        <space v-if="checkRightTemp === 'space'" />
        <document v-if="checkRightTemp === 'document'" />
        <user v-if="checkRightTemp === 'user'" />
      </el-card>
    </div>
  </div>
</template>
<script>
import Sidebar from './components/Sidebar'
import UserSet from './components/UserSet'
import Space from '../space/admin/index'
import Document from '../doc/index'
import User from '../system/user/index'
export default {
  components: {
    Sidebar,
    UserSet,
    Space,
    Document,
    User
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
