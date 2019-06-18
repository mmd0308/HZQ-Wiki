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
        <el-menu-item index="/dashboard">
          首页
        </el-menu-item>
        <el-menu-item index="/space" >
          空间
        </el-menu-item>
        <!-- 只有admin拥有超级管理员权限 -->
        <el-menu-item v-if="userId == 1 && username == 'admin'" index="/set/system" >
          系统管理
        </el-menu-item>
      </el-menu>
    </div>
    <div style="float:right;" >
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
            <avatar :username="username" :src="avatar" style="float:left;margin-top:10px;" size="40" />
          </template>
          <el-menu-item index="/set/user" >个人中心</el-menu-item>
          <el-menu-item index="/login" @click="logout">退出登陆</el-menu-item>
        </el-submenu>
    </el-menu></div>
  </div>
</template>
<script>
import Avatar from 'vue-avatar'
import { mapGetters } from 'vuex'
export default {
  components: {
    Avatar
  },
  data() {
    return {
      activeIndex: this.$router.currentRoute.path
    }
  },
  computed: {
    ...mapGetters([
      'userId',
      'username',
      'avatar'
    ])
  },
  methods: {
    handleSelect(key, keyPath) {
      this.activeIndex = key
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
}
</style>
