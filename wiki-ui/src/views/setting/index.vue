<template>
  <div class="set" style="background:blue;">
    <div class="left" style="background: pink">
      <sidebar :create-bar="checkRightTemp" @getRightTemp="getRightTemp" />
    </div>
    <div class="right">
      <el-card>
        <user-set v-if="checkRightTemp === 'personal'" />
        <space v-if="checkRightTemp === 'space'" />
        <document v-if="checkRightTemp === 'document'" />
      </el-card>
    </div>
  </div>
</template>
<script>
import Sidebar from './components/Sidebar'
import UserSet from './components/UserSet'
import Space from '../space/index'
import Document from '../doc/index'
export default {
  components: {
    Sidebar,
    UserSet,
    Space,
    Document
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
    width: 984px;
    margin: 0 auto;
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
