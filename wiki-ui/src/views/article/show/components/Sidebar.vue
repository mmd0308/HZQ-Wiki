<template>
  <div class="sidebar">
    <ul>
      <li>
        <router-link to="/edit/article">
          <el-button type="primary" style="width:100%;">新增文档</el-button>
        </router-link>
      </li>
      <li>
        <div >
          待开发...
        </div>
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
    overflow: auto;
    height: fit-content;
    ul {
        padding: 0px;
        margin: 0px;
        li{
            height: 50px;
            font-size: 14px;
            line-height: 50px;
        }
        span {
            padding: 0px 15px;
            display: block;
        }
        li + li{
          margin-top: 10px;
          border: #ff7d44 1px solid;
        }
    }
}
</style>
