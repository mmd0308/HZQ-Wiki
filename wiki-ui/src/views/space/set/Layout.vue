<template>
  <div class="set" style="background:blue;">
    <div class="left" style="background: pink">
      <sidebar :create-bar="checkRightTemp" :space-id="spaceId" @getRightTemp="getRightTemp" />
    </div>
    <div class="right">
      <el-card>
        <base-set v-if="checkRightTemp === 'base'" :space-id="spaceId" />
        <member v-if="checkRightTemp === 'member'" :space-id="spaceId"/>
        <document v-if="checkRightTemp === 'document'" :space-id="spaceId" />
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
      spaceId: this.$route.query.spaceId,
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
