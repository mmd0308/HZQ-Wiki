<template>
  <div class="set" >
    <div class="left">
      <sidebar :create-bar="checkRightTemp" @getRightTemp="getRightTemp" />
    </div>
    <div class="right">
      <el-card>
        <article-drafts v-if="checkRightTemp === 'drafts'"/>
      </el-card>
    </div>
  </div>
</template>
<script>
import Sidebar from './components/Sidebar'
import ArticleDrafts from './components/ArticleDrafts'
export default {
  components: {
    Sidebar,
    ArticleDrafts
  },
  data() {
    return {
      checkRightTemp: 'drafts'
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
