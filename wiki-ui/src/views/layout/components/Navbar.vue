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
        router="true"
        active-text-color="#ffd04b"
        @select="handleSelect">
        <el-menu-item index="/">
          首页
        </el-menu-item>
        <el-menu-item index="/space" >
          空间
        </el-menu-item>
        <el-menu-item index="/set/index" >
          系统管理
        </el-menu-item>
      </el-menu>

    </div>
    <div style="float:right;" >
      <!-- <el-dropdown placement="bottom" trigger="click">
        <avatar username="heng zhao qing" size="40" />
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item>退出登陆</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown> -->
      <el-menu
        :default-active="activeIndex"
        class="el-menu-demo"
        mode="horizontal"
        background-color="#545c64"
        text-color="#fff"
        router="true"
        active-text-color="#ffd04b"
        @select="handleSelect">
        <el-submenu>
          <template slot="title">
            <avatar style="float:left;margin-top:10px;" username="heng zhao qing" size="40" />
          </template>
          <el-menu-item index="/" >个人中心</el-menu-item>
          <el-menu-item index="/login" @click="logout">退出登陆</el-menu-item>
        </el-submenu>
    </el-menu></div>
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
      activeIndex: '/'
    }
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath)
    },
    logout() {
      this.$store.dispatch('LogOut').then(() => {
        this.$router.push({ path: this.redirect || '/login' })
      })
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
