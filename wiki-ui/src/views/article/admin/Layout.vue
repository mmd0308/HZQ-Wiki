<template>
  <el-tabs v-model="tabName" tab-position="left" @tab-click="checkTab">

    <el-tab-pane :name="paramsConstants['article'].name">
      <span slot="label" >
        <i class="el-icon-edit-outline"/>
        博客管理
      </span>
      <article-admin :ref="paramsConstants['article'].ref"/>
    </el-tab-pane>

    <el-tab-pane :name="paramsConstants['tag'].name">
      <span slot="label">
        <i class="el-icon-edit-outline"/>
        标签管理
      </span>
      <tag-admin :ref="paramsConstants['tag'].ref"/>
    </el-tab-pane>

  </el-tabs>
</template>
<script>
import ArticleAdmin from './Index'
import TagAdmin from '../../tag/admin/Index'
export default {
  components: {
    ArticleAdmin,
    TagAdmin
  },
  data() {
    return {
      tabName: '',
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
    this.tabName = this.$route.fullPath.substr(7)
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
