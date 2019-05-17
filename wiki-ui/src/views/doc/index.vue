<template>
  <div class="doc">
    <div class="header" style="margin:0 0 10px 0;">
      <el-input v-model="input5" style="width:300px" placeholder="请输入内容" class="input-with-select">
        <el-button slot="append" icon="el-icon-search"/>
      </el-input>
      <el-button style="float:right;margin-right:30px;" type="success" size="small" @click="spaceToAdd">添加项目</el-button>
    </div>
    <el-table
      :data="docLists"
      :show-header="false"
      style="width: 100%">
      <el-table-column>
        <template slot-scope="scope">
          <router-link to="/doc/set">
            <h3>{{ scope.row.name }}</h3>
          </router-link>
          <span>{{ scope.row.remark }}</span>
          <div>
            {{ scope.row.createTime }}
          </div>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="120">
        <template slot-scope="scope">
          <el-dropdown trigger="click">
            <el-button class="el-dropdown-link" type="hwbutton" size="small">
              设置<i class="el-icon-arrow-down el-icon--right"/>
            </el-button>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item icon="el-icon-plus">黄金糕</el-dropdown-item>
              <el-dropdown-item icon="el-icon-circle-check-outline">蚵仔煎</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
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

    <!-- 新增或者修改项目 -->
    <el-dialog :visible.sync="dialogDocVisible" :title="dialogTitle" width="40%">
      <el-form :model="spaceForm" label-width="100px" >
        <el-form-item label="空间名称" prop="name">
          <el-input v-model="spaceForm.name" style="width:80%"/>
        </el-form-item>
        <el-form-item label="访问级别" prop="visitLevel">
          <el-select v-model="spaceForm.visitLevel" placeholder="请选择" style="width:80%">
            <el-option
              v-for="item in visitLevels"
              :key="item.value"
              :label="item.label"
              :value="item.value"/>
          </el-select>
        </el-form-item>
        <el-form-item label="描述" prop="remark">
          <el-input v-model="spaceForm.remark" type="textarea" style="width:80%"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="addOrUpdate">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { page, addOrUpdate } from '@/api/doc/index'
export default {

  data() {
    return {
      dialogTitle: '新增空间',
      dialogDocVisible: false,
      spaceForm: {
        name: '',
        visitLevel: 0,
        remark: ''
      },
      visitLevels: [
        {
          value: 0,
          label: '私有空间'
        }, {
          value: 1,
          label: '共享空间'
        }],
      docLists: [],
      total: 0,
      listQuery: {
        pageNum: 1,
        pageSize: 6,
        name: ''
      }
    }
  },
  created() {
    this.page()
  },
  methods: {
    spaceToAdd() {
      this.dialogDocVisible = true
    },
    spaceToEdit(index, row) {
      this.dialogTitle = '修改空间'
      this.dialogDocVisible = true
    },
    page() {
      page(this.listQuery).then(response => {
        this.docLists = response.data
        this.total = response.total
      })
    },
    addOrUpdate() {
      addOrUpdate(this.spaceForm).then(() => {
        this.dialogDocVisible = false
        this.page()
      })
    },
    spaceDelete() {
      this.$confirm('此操作将永久删除[]用户?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        typr: 'warning'
      }).then(() => {
        alert('k')
      })
    },
    handleEdit(index, row) {
      console.log(index, row)
    },
    handleDelete(index, row) {
      console.log(index, row)
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

<style rel="stylesheet/scss" lang="scss">
.doc {
    .el-button--hwbutton{
        background: #ff7d44;
        border-color:#ff7d44;
    }
    .el-button--hwbutton:hover{
        background: #f98d5f;
        border-color:#f98d5f;
    }
}
</style>
