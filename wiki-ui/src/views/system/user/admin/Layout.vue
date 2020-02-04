<template>
  <el-tabs v-model="tabName" tab-position="left" @tab-click="checkTab">

    <el-tab-pane :name="paramsConstants['baseUser'].name">
      <span slot="label">
        <i class="el-icon-collection="/>
        用户管理
      </span>
      <base-user :ref="paramsConstants['baseUser'].ref"/>
    </el-tab-pane>

    <el-tab-pane :name="paramsConstants['editPass'].name">
      <span slot="label">
        <i class="el-icon-collection="/>
        修改密码
      </span>
      <edit-pass :ref="paramsConstants['editPass'].ref"/>
    </el-tab-pane>

  </el-tabs>
</template>
<script>
import BaseUser from './components/BaseUser'
import EditPass from './components/EditPass'
export default {
  components: {
    BaseUser,
    EditPass
  },
  data() {
    return {
      tabName: 'baseUser',
      paramsConstants: {
        baseUser: {
          name: 'baseUser',
          ref: 'baseUserRef'
        },
        editPass: {
          name: 'editPass',
          ref: 'editPassRef'
        }
      }
    }
  },
  created() {
    // 获取tab标签名称
  //  this.tabName = this.$route.fullPath.substr(7)
  },
  methods: {
    checkTab(item) {
      // this.$refs[this.paramsConstants[item.name].ref].init()
    }
  }
}
</script>
