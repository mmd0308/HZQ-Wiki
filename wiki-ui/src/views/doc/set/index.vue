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

export default {
  components: {
    Sidebar,
    UserSet
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
        width: 180px;
        float: left;
    }
    .right{
        float: right;
        width: 780px;
    }
}
</style>
