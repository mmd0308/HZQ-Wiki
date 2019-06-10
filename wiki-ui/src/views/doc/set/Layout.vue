<template>
  <div class="set" style="background:blue;">
    <div class="left" style="background: pink">
      <sidebar :create-bar="checkRightTemp" :doc-id="docId" @getRightTemp="getRightTemp" />
    </div>
    <div class="right">
      <el-card>
        <base-set v-if="checkRightTemp === 'base'" :doc-id="docId" />
        <member v-if="checkRightTemp === 'member'" :doc-id="docId"/>
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
      docId: this.$route.query.docId,
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
