<template>
  <div class="sidebar">
    <ul>
      <li :class="{clickSiber:clickSidber === 'personal'}" @click="clickSiberItem('personal')">
        <span>个人中心</span>
      </li>
      <li :class="{clickSiber:clickSidber === 'user'}" @click="clickSiberItem('user')">
        <span>用户管理</span>
      </li>
      <li :class="{clickSiber:clickSidber === 'space'}" @click="clickSiberItem('space')">
        <span>空间管理</span>
      </li>
      <li :class="{clickSiber:clickSidber === 'config'}" @click="clickSiberItem('config')">
        <span>配置管理</span>
      </li>
      <li :class="{clickSiber:clickSidber === 'weibo'}" @click="clickSiberItem('weibo')">
        <span>微博认证</span>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  props: {
    createBar: {
      type: String,
      default: ''
    }
  },
  data() {
    return {
      clickSidber: this.createBar
    }
  },
  methods: {
    clickSiberItem(item) {
      this.clickSidber = item
      this.$emit('getRightTemp', item)
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss">
.sidebar{
    padding: 0px;
    background: #fff;
    overflow: auto;
    height: fit-content;
    ul {
        padding: 0px;
        margin: 0px;
        li{
            height: 50px;
            border-bottom: 1px solid #e5e5e5;
            font-size: 14px;
            line-height: 50px;
        }
        li:hover{
            background: #ff7d44;
        }
        .clickSiber{
            background: #ff7d44;
        }
        span {
            padding: 0px 15px;
            display: block;
        }
    }
}
</style>
