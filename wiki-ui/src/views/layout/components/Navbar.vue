<template>
  <div class="navbar">
    <div class="menu">
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
      </el-menu>
    </div>
  </div>
</template>
<script>
export default {
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
  .menu{
    width: 984px;
    margin: 0 auto;

  }
}
</style>
