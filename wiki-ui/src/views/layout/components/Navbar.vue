<template>
  <div class="navbar">
    <h1 class="show-navbar-title" style="margin:0px;line-height:60px;float:left;font-size:20px;padding-right:40px;"><a href="/" class="router-link-active">
      HZQ-Wiki
    </a></h1>
    <div class="menu" style="float:left">
      <el-menu
        :default-active="activeIndex"
        class="el-menu-demo"
        mode="horizontal"
        background-color="#545c64"
        text-color="#fff"
        active-text-color="#ffd04b"
        @select="handleSelect">
        <el-menu-item index="1">
          <router-link to="/">
            首页
          </router-link>
        </el-menu-item>
        <el-menu-item index="3" disabled>
          空间
        </el-menu-item>
        <el-menu-item index="4" >
          <router-link to="/set/index" >

            设置
          </router-link>
        </el-menu-item>
      </el-menu>

    </div>
    <div style="float:right;margin:10px 0px;" >
      <avatar username="heng zhao qing" size="40" />
    </div>
  </div>
</template>
<script>
import Avatar from 'vue-avatar'
export default {
  components: {
    Avatar
  },
  data() {
    return {
      activeIndex: '1'
    }
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath)
    }
  }
}
</script>
<style rel="stylesheet/scss" lang="scss" scoped>
.navbar{
    width: 984px;
    margin: 0 auto;
  .menu{
  }
}
</style>
