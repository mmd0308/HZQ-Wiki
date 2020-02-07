<template>
  <div class="hzq-admin">
    <div class="headerForm">
      <el-form :inline="true" :model="listQuery" class="demo-form-inline">
        <el-form-item label="关键字">
          <el-input v-model="listQuery.title" size="small" placeholder="请输入标题关键字"/>
        </el-form-item>
        <el-form-item style="float:right">
          <el-button type="primary" size="small" @click="handleQuery">查询</el-button>
          <el-button type="info" size="small" @click="handleReset" >重置</el-button>
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
      <el-table-column
        type="selection"
        width="55"/>
      <el-table-column
        label="标题">
        <template slot-scope="scope">
          <router-link :to="{ path:'/read/article/' + scope.row.id }">
            {{ scope.row.title }}
          </router-link>
        </template>
      </el-table-column>
      <el-table-column
        label="状态"
        width="80">
        <template slot-scope="scope">
          <el-tag :type="articleState[scope.row.hwState].status" size="small" >{{ articleState[scope.row.hwState].text }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column
        prop="hwUp"
        width="100"
        label="是否置顶">
        <template slot-scope="scope">
          <el-tag :type="articleUp[scope.row.hwUp].status" size="small" >{{ articleUp[scope.row.hwUp].text }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column
        width="155"
        prop="createTime"
        label="创建时间"/>
      <el-table-column
        fixed="right"
        label="操作"
        width="100">
        <template slot-scope="scope">
          <router-link :to="{ path:'/read/article/' + scope.row.id }">
            <el-button type="text" size="small">查看</el-button>
          </router-link>
          <el-popconfirm
            confirm-button-text="删除"
            confirm-button-type="danger"
            cancel-button-text="不用了"
            cancel-button-type="text"
            icon="el-icon-info"
            icon-color="red"
            title="您确定删除该条数据吗？"
            @onConfirm="handleRemoveById(scope.row.id)"
          >
            <el-button slot="reference" style="color: red;" type="text" size="small">删除</el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
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
import { page, deleteById } from '@/api/index'
import { articleState, articleUp } from '@/api/article/articleConstants'
export default {
  data() {
    return {
      moudle: 'articles',
      tableLists: [],
      tableLoading: false,
      total: 0,
      listQuery: {
        pageNum: 1,
        pageSize: 10,
        title: null
      },
      formInline: {
        user: '',
        region: ''
      },
      articleState: articleState,
      articleUp: articleUp
    }
  },
  methods: {
    init() {
      this.getPage()
    },
    handleRemoveById(id) {
      deleteById(this.moudle, id).then(() => {
        this.getPage()
      })
    },
    handleQuery() {
      this.getPage()
    },
    handleReset() {
      this.listQuery.title = null
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
