<template>
  <el-tabs type="border-card" @tab-click="checkTab">
    <el-tab-pane>
      <span slot="label"><i class="el-icon-odometer"/> 仪表盘</span>
      仪表盘
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

    <el-tab-pane>
      <span slot="label">
        <i class="el-icon-collection="/>
        空间管理
      </span>
      <space-admin />
    </el-tab-pane>
    <el-tab-pane>
      <span slot="label">
        <i class="el-icon-collection="/>
        用户管理
      </span>
      <space-admin />
    </el-tab-pane>
    <el-tab-pane>
      <span slot="label">
        <i class="el-icon-collection="/>
        登陆日志
      </span>
      <space-admin />
    </el-tab-pane>
    <el-tab-pane>
      <span slot="label">
        <i class="el-icon-collection="/>
        配置管理
      </span>
      <space-admin />
    </el-tab-pane>
  </el-tabs>
</template>

<script>
import ArticleAdmin from '../article/admin/Index'
import TagAdmin from '../tag/admin/Index'
import SpaceAdmin from '../space/admin/Index'
export default {
  components: {
    ArticleAdmin,
    SpaceAdmin,
    TagAdmin
  },
  data() {
    return {
      paramsConstants: {
        article: {
          name: 'article',
          ref: 'articleAdmin'
        },
        tag: {
          name: 'tag',
          ref: 'tagAdmin'
        }
      }
    }
  },
  methods: {
    checkTab(item) {
      this.$refs[this.paramsConstants[item.name].ref].init()
    }
  }
}
</script>
