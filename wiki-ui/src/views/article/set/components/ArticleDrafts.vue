<template>
  <div class="space">
    <el-table
      :data="artilceLists"
      style="width: 100%">
      <el-table-column
        label="文章标题">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.title }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" width="180">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.createTime }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="150">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" icon="el-icon-edit" @click="articleEdit(scope.$index, scope.row)" />
          <el-button type="danger" size="mini" icon="el-icon-delete" @click="articleDelete(scope.$index, scope.row)" />
        </template>
      </el-table-column>
    </el-table>

    <div style="margin:10px 0 0 0;">
      <el-pagination
        :current-page="listQuery.pageNum"
        :page-size="listQuery.pageSize"
        :total="total"
        layout="total,  prev, pager, next"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"/>
    </div>
  </div>
</template>

<script>
import { page } from '@/api/article/article'
export default {
  data() {
    return {
      total: 0,
      listQuery: {
        pageNum: 1,
        pageSize: 10,
        hwState: '1'
      },
      artilceLists: []
    }
  },
  created() {
    this.page()
  },
  methods: {
    page() {
      page(this.listQuery).then(response => {
        this.artilceLists = response.data
        this.total = response.total
      })
    },
    handleCurrentChange(val) {
      this.listQuery.pageNum = val
      this.page()
    },
    cancel() {
      this.dialogUserVisible = false
      this.userForm = this.init()
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss">
.space {
    .header{
        margin-bottom: 15px;
    }
}
</style>
