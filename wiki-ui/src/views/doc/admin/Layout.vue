<template>
  <el-tabs v-model="tabName" tab-position="left" @tab-click="checkTab">

    <el-tab-pane :name="paramsConstants['doc'].name">
      <span slot="label">
        <i class="el-icon-collection="/>
        文档管理
      </span>
      <doc-admin :ref="paramsConstants['doc'].ref"/>
    </el-tab-pane>

    <el-tab-pane :name="paramsConstants['sapce'].name">
      <span slot="label">
        <i class="el-icon-collection="/>
        空间管理
      </span>
      <space-admin :ref="paramsConstants['sapce'].ref"/>
    </el-tab-pane>

  </el-tabs>
</template>
<script>
import SpaceAdmin from '../../space/admin/Index'
import DocAdmin from './Index'
export default {
  components: {
    SpaceAdmin,
    DocAdmin
  },
  data() {
    return {
      tabName: 'doc',
      paramsConstants: {
        dashboard: {
          name: 'dashboard',
          ref: 'dashboardAdmin'
        },
        article: {
          name: 'article',
          ref: 'articleAdmin'
        },
        tag: {
          name: 'tag',
          ref: 'tagAdmin'
        },
        sapce: {
          name: 'sapce',
          ref: 'sapceAdmin'
        },
        doc: {
          name: 'doc',
          ref: 'docAdmin'
        },
        user: {
          name: 'user',
          ref: 'userAdmin'
        },
        loginLog: {
          name: 'loginLog',
          ref: 'loginLogAdmin'
        },
        config: {
          name: 'config',
          ref: 'configAdmin'
        }
      }
    }
  },
  created() {
    // 获取tab标签名称
    // this.tabName = this.$route.fullPath.substr(7)
    var that = this
    // 延迟0.5秒加载,让页面渲染完毕
    setTimeout(function() {
      that.$refs[that.paramsConstants[that.tabName].ref].init()
    }, 500)
  },
  methods: {
    checkTab(item) {
      this.$refs[this.paramsConstants[item.name].ref].init()
    }
  }
}
</script>
