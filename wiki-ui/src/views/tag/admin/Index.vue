<template>
  <div>
    <el-table
      :data="tableLists"
      :header-cell-style="{background:'whitesmoke',color:'#000000'}"
      tooltip-effect="dark"
      style="width: 100%">
      <el-table-column
        type="selection"
        width="55"/>
      <el-table-column
        prop="name"
        label="名称"/>
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
      <h4 class="header">标签编辑</h4>
      <el-divider/>
      <div class="content">
        <el-form :model="editTagForm" label-width="80px">
          <el-form-item label="标签名称">
            <el-input v-model="editTagForm.name"/>
          </el-form-item>
        </el-form>

        <div class="footer">
          <el-button size="medium" type="info" @click="editDrawer = false">取消</el-button>
          <el-button size="medium" type="primary" @click="handleSaveClick"> 保存 </el-button>
        </div>
      </div>
    </el-drawer>
  </div>
</template>

<script>
import { page, updateById } from '@/api/index'
export default {
  data() {
    return {
      moudle: 'tags',
      tableLists: [],
      total: 0,
      listQuery: {
        pageNum: 1,
        pageSize: 10
      },
      editDrawer: false,
      editTagForm: this.initForm()
    }
  },
  methods: {
    init() {
      this.getPage()
    },
    initForm() {
      return {
        id: null,
        name: null
      }
    },
    handleEditClick(row) {
      this.editDrawer = true
      this.editTagForm = this.initForm()
      this.editTagForm = row
    },
    handleSaveClick() {
      // 编辑保存
      updateById(this.moudle, this.editTagForm).then(() => {
        this.editDrawer = false
      })
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
