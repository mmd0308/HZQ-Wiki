<template>
  <div class="hzq-admin">
    <div class="headerForm">
      <el-form :inline="true" :model="queryForm" class="demo-form-inline">
        <el-form-item label="关键字">
          <el-input v-model="queryForm.keyword" size="small" placeholder="请输入检索内容"/>
        </el-form-item>
        <el-form-item style="float:right">
          <el-button type="primary" size="small" >查询</el-button>
          <el-button type="info" size="small" >重置</el-button>
        </el-form-item>
      </el-form>
    </div>

    <el-table
      v-loading="tableLoading"
      :data="tableLists"
      :header-cell-style="{background:'#545c64',color:'#ffffff'}"
      element-loading-text="拼命加载中"
      element-loading-spinner="el-icon-loading"
      element-loading-background="rgba(255, 255, 255, 0.83)"
      tooltip-effect="dark"
      style="width: 100%">
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" label-width="110px" class="demo-table-expand">
            <el-form-item label="登陆账户">
              <span>{{ props.row.username }}</span>
            </el-form-item>
            <el-form-item label="系统">
              <span>{{ props.row.system }}</span>
            </el-form-item>
            <el-form-item label="浏览器">
              <span>{{ props.row.browser }}</span>
            </el-form-item>
            <el-form-item label="IP">
              <span>{{ props.row.ip }}</span>
            </el-form-item>
            <el-form-item label="登陆时间">
              <span>{{ props.row.createTime }}</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>

      <el-table-column prop="username" label="登陆用户"/>
      <el-table-column prop="ip" label="登陆IP"/>
      <el-table-column prop="browser" label="浏览器"/>
      <el-table-column prop="createTime" label="登陆时间"/>
    </el-table>

    <div style="padding:15px 0px 0px;float:right">
      <el-pagination
        :current-page="listQuery.pageNum"
        :page-sizes="[10, 20, 30, 40]"
        :page-size="listQuery.pageSize"
        :total="total"
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
      moudle: 'login/logs',
      queryForm: {
        keyword: ''
      },
      tableLists: [],
      tableLoading: false,
      total: 0,
      listQuery: {
        pageNum: 1,
        pageSize: 10
      }
    }
  },
  methods: {
    init() {
      this.getPage()
    },
    getPage() {
      this.tableLoading = true
      page(this.moudle, this.listQuery).then(res => {
        this.tableLists = res.records
        this.total = res.total
        this.tableLoading = false
      })
    },
    handleSizeChange(val) {
      this.listQuery.pageSize = val
      this.getPage()
    },
    handleCurrentChange(val) {
      this.listQuery.pageNum = val
      this.getPage()
    }
  }
}
</script>
<style>
  .demo-table-expand {
    font-size: 0;
  }
  .demo-table-expand label {
    width: 90px;
    color: #99a9bf;
  }
  .demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 50%;
  }
</style>
