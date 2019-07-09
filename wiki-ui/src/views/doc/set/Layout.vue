<template>
  <div class="set" style="background:blue;">
    <div class="left" style="background: pink">
      <sidebar :create-bar="checkRightTemp" @getRightTemp="getRightTemp" />
    </div>
    <div class="right">
      <el-card>
        <base-set v-if="checkRightTemp === 'base'" />
        <member v-if="checkRightTemp === 'member'" />
      </el-card>
    </div>
  </div>
</template>
<script>
import Sidebar from './components/Sidebar'
import BaseSet from './components/BaseSet'
import Member from './components/Member'
export default {
  components: {
    Sidebar,
    BaseSet,
    Member
  },
  data() {
    return {
      checkRightTemp: 'base'
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
        width: 180px;
        float: left;
    }
    .right{
        float: right;
        width: 780px;
    }
}
</style>
