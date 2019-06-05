<template>
  <div class="sidebar">
    <ul>
      <li :class="{clickSiber:clickSidber === 'base'}" @click="clickSiberItem('base')">
        <span>基础设置</span>
      </li>
      <li :class="{clickSiber:clickSidber === 'member'}" @click="clickSiberItem('member')">
        <span>成员</span>
      </li>
      <!-- <li :class="{clickSiber:clickSidber === 'space'}" @click="clickSiberItem('space')">
        <span>设置</span>
      </li> -->
    </ul>
  </div>
</template>
<script>
export default {
  props: {
    createBar: {
      type: String,
      default: ''
    },
    spaceId: {
      type: Number,
      required: true
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
