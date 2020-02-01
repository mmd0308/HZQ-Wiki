<template>
  <div>
    <el-form :inline="true" :model="formInline" class="demo-form-inline">
      <el-form-item label="关键字">
        <el-input v-model="formInline.user" size="small" placeholder="审批人"/>
      </el-form-item>
      <el-form-item label="文章状态">
        <el-select v-model="formInline.region" size="small" placeholder="活动区域">
          <el-option label="区域一" value="shanghai"/>
          <el-option label="区域二" value="beijing"/>
        </el-select>
      </el-form-item>
      <el-form-item style="float:right">
        <el-button type="primary" size="small" @click="onSubmit">查询</el-button>
        <el-button type="info" size="small" @click="onSubmit">重置</el-button>
      </el-form-item>
    </el-form>
    <el-table
      :data="articleLists"
      :header-cell-style="{background:'whitesmoke',color:'#000000'}"
      tooltip-effect="dark"
      style="width: 100%">
      <el-table-column
        type="selection"
        width="55"/>
      <el-table-column
        prop="title"
        label="标题"
        width="180"/>
      <el-table-column
        prop="hwState"
        label="状态"
        width="180"/>
      <el-table-column
        prop="address"
        label="标签"/>
      <el-table-column
        prop="address"
        label="发布时间"/>
      <el-table-column
        fixed="right"
        label="操作"
        width="100">
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="handleClick(scope.row)">编辑</el-button>
          <el-button type="text" size="small">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div style="padding:15px 0px 0px;float:right">
      <el-pagination
        :current-page="currentPage4"
        :page-sizes="[100, 200, 300, 400]"
        :page-size="100"
        :total="400"
        layout="total, sizes, prev, pager, next, jumper"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"/>
    </div>
  </div>
</template>

<script>
import { page } from '@/api/index'
export default {
  data() {
    return {
      moudle: 'articles',
      articleLists: [],
      total: 0,
      listQuery: {
        pageNum: 1,
        pageSize: 10
      },
      formInline: {
        user: '',
        region: ''
      },
      currentPage4: 4
    }
  },
  methods: {
    init() {
      this.getPage()
    },
    getPage() {
      page(this.moudle, this.listQuery).then(res => {
        this.articleLists = res.records
        this.total = res.total
      })
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`)
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`)
    }
  }
}
</script>
