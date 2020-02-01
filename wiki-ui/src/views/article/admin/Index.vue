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
      :data="tableLists"
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
        label="状态"
        width="180">
        <template slot-scope="scope">
          <el-tag :type="articleState[scope.row.hwState].status" >{{ articleState[scope.row.hwState].text }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column
        prop="hwUp"
        label="是否置顶">
        <template slot-scope="scope">
          <el-tag :type="articleUp[scope.row.hwUp].status" >{{ articleUp[scope.row.hwUp].text }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column
        prop="address"
        label="标签"/>
      <el-table-column
        prop="createTime"
        width="160"
        label="创建时间"/>
      <el-table-column
        fixed="right"
        label="操作"
        width="100">
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="handleEditClick(scope.row)">编辑</el-button>
          <el-button type="text" size="small">删除</el-button>
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

    <!--编辑 -->
    <el-drawer
      :visible.sync="editDrawer"
      :with-header="false"
      class="hzq-drawer">
      <h4 class="header">文章编辑</h4>
      <el-divider/>
      <div class="content">
        <el-form :model="formLabelAlign" label-width="80px">
          <el-form-item label="名称">
            <el-input v-model="formLabelAlign.name"/>
          </el-form-item>
          <el-form-item label="活动区域">
            <el-input v-model="formLabelAlign.region"/>
          </el-form-item>
          <el-form-item label="活动形式">
            <el-input v-model="formLabelAlign.type"/>
          </el-form-item>
        </el-form>

        <div class="footer">
          <el-button size="medium" type="info" @click="cancelForm">保存草稿</el-button>
          <el-button :loading="loading" size="medium" type="primary" @click="release">{{ loading ? '提交中 ...' : '发  布' }}</el-button>
        </div>
      </div>
    </el-drawer>
  </div>
</template>

<script>
import { page } from '@/api/index'
import { articleState, articleUp } from '@/api/article/articleConstants'
export default {
  data() {
    return {
      moudle: 'articles',
      tableLists: [],
      total: 0,
      listQuery: {
        pageNum: 1,
        pageSize: 10
      },
      formInline: {
        user: '',
        region: ''
      },
      articleState: articleState,
      articleUp: articleUp,
      editDrawer: false,
      formLabelAlign: {
        name: '',
        region: '',
        type: ''
      }
    }
  },
  methods: {
    init() {
      this.getPage()
    },
    handleEditClick(row) {
      console.log(row)
      this.editDrawer = true
    },
    getPage() {
      page(this.moudle, this.listQuery).then(res => {
        this.tableLists = res.records
        this.total = res.total
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
