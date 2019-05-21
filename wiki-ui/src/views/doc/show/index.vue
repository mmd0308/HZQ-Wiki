<template>
  <div class="hzq-wiki-main">
    <div style="padding-bottom: 10px;">
      <el-radio-group v-model="spaceVisitLevel" @change="changeSpaceVisitLevel">
        <el-radio-button label="0">个人文档</el-radio-button>
        <el-radio-button label="1"> 共享文档</el-radio-button>
      </el-radio-group>
      <el-button type="success" style="float:right">
        新增文档
      </el-button>
    </div>

    <el-table
      :data="spaceLists"
      :show-header="false"
      style="width: 100%">
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item label="空间名称">
              <span>{{ props.row.name }}</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column
        label="空间名称"
        prop="name">
        <template slot-scope="scope">
          <router-link to="/">
            {{ scope.row.name }}
          </router-link>
        </template>
      </el-table-column>
      <el-table-column
        label="操作"
        width="50">
        <template slot-scope="scope">
          <i class="el-icon-setting"/>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>
<script>
import { page, addOrUpdate, deletedById } from '@/api/doc/index'
export default {
  data() {
    return {
      spaceVisitLevel: '0',
      spaceLists: [],
      total: 0,
      listQuery: {
        pageNum: 1,
        pageSize: 10,
        name: '',
        visitLevel: this.spaceVisitLevel
      }
    }
  },
  created() {
    this.page()
  },
  methods: {
    page() {
      page(this.listQuery).then(response => {
        this.spaceLists = response.data
        this.total = response.total
      })
    },
    changeSpaceVisitLevel(val) {
      this.listQuery.visitLevel = val
      this.page()
    }
  }
}
</script>
<style rel="stylesheet/scss" lang="scss" scoped>

</style>
