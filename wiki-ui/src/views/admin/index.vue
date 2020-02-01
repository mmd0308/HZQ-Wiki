<template>
  <el-tabs type="border-card" @tab-click="checkTab">
    <el-tab-pane>
      <span slot="label"><i class="el-icon-odometer"/> 仪表盘</span>
      仪表盘
    </el-tab-pane>
    <el-tab-pane :name="articleName">
      <span slot="label" >
        <i class="el-icon-edit-outline"/>
        博客管理
      </span>
      <article-admin :ref="articleRef" />
    </el-tab-pane>
    <el-tab-pane>
      <span slot="label">
        <i class="el-icon-collection="/>
        空间管理
      </span>
      <space-admin />
    </el-tab-pane>
  </el-tabs>
</template>

<script>
import ArticleAdmin from '../article/admin/Index'
import SpaceAdmin from '../space/admin/Index'
export default {
  components: {
    ArticleAdmin,
    SpaceAdmin
  },
  data() {
    return {
      articleName: 'article',
      articleRef: 'articleAdmin'
    }
  },
  methods: {
    checkTab(item) {
      if (item.name === this.articleName) {
        this.$refs[this.articleRef].init()
      }
    }
  }
}
</script>
