<template>
  <el-tabs v-model="tabName" type="border-card" @tab-click="checkTab">
    <el-tab-pane :name="paramsConstants['dashboard'].name">
      <span slot="label">
        <i class="el-icon-odometer"/>
        仪表盘
      </span>
      <dashboard-admin :ref="paramsConstants['dashboard'].ref" />
    </el-tab-pane>

    <el-tab-pane :name="paramsConstants['article'].name">
      <span slot="label" >
        <i class="el-icon-edit-outline"/>
        博客管理
      </span>
      <article-admin :ref="paramsConstants['article'].ref" />
    </el-tab-pane>

    <el-tab-pane :name="paramsConstants['tag'].name">
      <span slot="label">
        <i class="el-icon-collection="/>
        标签管理
      </span>
      <tag-admin :ref="paramsConstants['tag'].ref"/>
    </el-tab-pane>

    <el-tab-pane :name="paramsConstants['sapce'].name">
      <span slot="label">
        <i class="el-icon-collection="/>
        空间管理
      </span>
      <space-admin :ref="paramsConstants['sapce'].ref"/>
    </el-tab-pane>

    <!-- <el-tab-pane :name="paramsConstants['space'].name">
      <span slot="label">
        <i class="el-icon-collection="/>
        空间管理
      </span>
      <space-admin :ref="paramsConstants['space'].ref"/>
    </el-tab-pane> -->

  </el-tabs>
</template>

<script>
import DashboardAdmin from './Dashboard'
import ArticleAdmin from '../article/admin/Index'
import TagAdmin from '../tag/admin/Index'
import SpaceAdmin from '../space/admin/Index'
export default {
  components: {
    DashboardAdmin,
    ArticleAdmin,
    TagAdmin,
    SpaceAdmin
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
