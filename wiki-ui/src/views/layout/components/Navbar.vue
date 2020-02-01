<template>
  <div class="navbar">
    <h1 class="show-navbar-title" style="margin:0px;line-height:60px;float:left;font-size:20px;padding-right:40px;">
      <a href="/" class="router-link-active">
        {{ title }}
      </a>
    </h1>
    <div class="menu" style="float:left">
      <el-menu
        :default-active="activeIndex"
        :router="true"
        class="el-menu-demo"
        mode="horizontal"
        background-color="#545c64"
        text-color="#fff"
        active-text-color="#ffd04b"
        @select="handleSelect">
        <el-menu-item index="/dashboard">
          首页
        </el-menu-item>
        <el-menu-item index="/articles" >
          文章
        </el-menu-item>
        <el-menu-item index="/doc" >
          文档
        </el-menu-item>
        <el-menu-item index="/space" >
          空间
        </el-menu-item>
        <!-- 只有admin拥有超级管理员权限 -->
        <el-menu-item v-if="userId == 1 && username == 'admin'" index="/set/system" >
          系统管理-old
        </el-menu-item>
        <el-menu-item v-if="userId == 1 && username == 'admin'" index="/admin" >
          系统管理
        </el-menu-item>
      </el-menu>
    </div>

    <div style="float:right;" >
      <el-menu
        v-if="token != '' && token != null"
        :default-active="activeIndex"
        :router="true"
        class="el-menu-demo"
        mode="horizontal"
        background-color="#545c64"
        text-color="#fff"
        active-text-color="#ffd04b"
        @select="handleSelect">
        <el-submenu index="/">
          <template slot="title">
            <avatar :username="username" :src="avatar" :size="40" style="float:left;margin-top:10px;" />
          </template>
          <el-menu-item index="/set/user" >个人中心</el-menu-item>
          <el-menu-item index="/set/article" >文章管理</el-menu-item>
          <el-menu-item index="/login" @click="logout">退出登陆</el-menu-item>
        </el-submenu>
      </el-menu>
      <div v-else>
        <el-button type="text" >登陆</el-button>
        <el-divider direction="vertical"/>
        <el-button type="text" >注册</el-button>
      </div>
    </div>
  </div>
</template>
<script>
import Avatar from 'vue-avatar'
import { mapGetters } from 'vuex'
import { getToken } from '@/utils/auth'
export default {
  components: {
    Avatar
  },
  data() {
    return {
      token: getToken(),
      activeIndex: this.$router.currentRoute.path
    }
  },
  computed: {
    ...mapGetters([
      'userId',
      'username',
      'avatar',
      'title'
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
