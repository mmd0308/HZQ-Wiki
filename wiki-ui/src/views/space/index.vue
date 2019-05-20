<template>
  <div class="space">
    <div class="header">
      <el-input v-model="listQuery.name" style="width:300px" placeholder="请输入空间名称" class="input-with-select" @keyup.enter.native="page">
        <el-button slot="append" icon="el-icon-search"/>
      </el-input>
      <el-button style="float:right;margin-right:30px;" type="success" @click="spaceToAdd">新增</el-button>
    </div>
    <el-table
      :data="spaceLists"
      style="width: 100%">
      <el-table-column
        label="空间名称">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="项目级别">
        <template slot-scope="scope">
          <span style="margin-left: 10px">
            <el-tag v-if="scope.row.visitLevel == 0">
              私有项目
            </el-tag>
            <el-tag v-if="scope.row.visitLevel == 1" type="warning">
              共享项目
            </el-tag>
          </span>
        </template>
      </el-table-column>
      <el-table-column
        label="创建时间"
        width="180">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.createTime }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="160">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="primary"
            @click="spaceToEdit(scope.$index, scope.row)">编辑</el-button>
          <el-button
            size="mini"
            type="danger"
            @click="spaceDelete(scope.$index, scope.row)">删除</el-button>
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
    <el-dialog :visible.sync="dialogSpaceVisible" :title="dialogTitle" width="40%">
      <el-form :ref="ruleForm" :model="spaceForm" :rules="rules" label-width="100px" >
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
        <el-button @click="cancel">取 消</el-button>
        <el-button type="primary" @click="addOrUpdate">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { page, addOrUpdate, deletedById } from '@/api/space/index'
export default {
  data() {
    return {
      dialogTitle: '新增空间',
      dialogSpaceVisible: false,
      spaceForm: this.init(),
      visitLevels: [
        {
          value: 0,
          label: '私有空间'
        }, {
          value: 1,
          label: '共享空间'
        }],
      spaceLists: [],
      total: 0,
      listQuery: {
        pageNum: 1,
        pageSize: 10,
        name: ''
      },
      ruleForm: 'ruleForm',
      rules: {
        name: [
          { required: true, message: '请输入空间名称', trigger: 'blur' },
          { min: 2, max: 5, message: '长度在 2 到 5 个字符', trigger: 'blur' }
        ]
      }
    }
  },
  created() {
    this.page()
  },
  methods: {
    init() {
      return {
        name: '',
        visitLevel: 0,
        remark: ''
      }
    },
    spaceToAdd() {
      this.dialogSpaceVisible = true
    },
    spaceToEdit(index, row) {
      this.dialogTitle = '修改空间'
      this.dialogSpaceVisible = true
      this.spaceForm = row
    },
    page() {
      page(this.listQuery).then(response => {
        this.spaceLists = response.data
        this.total = response.total
      })
    },
    addOrUpdate() {
      this.$refs[this.ruleForm].validate((valid) => {
        if (valid) {
          addOrUpdate(this.spaceForm).then(() => {
            this.dialogSpaceVisible = false
            this.spaceForm = this.init()
            this.page()
          })
        } else {
          return false
        }
      })
    },
    spaceDelete(index, row) {
      this.$confirm('此操作将永久删除[' + row.name + ']空间?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        typr: 'warning'
      }).then(() => {
        deletedById(row.id).then(() => {
          this.$notify({
            title: '成功',
            message: '空间[' + row.name + ']删除成功!',
            type: 'success'
          })
          this.page()
        })
      })
    },
    handleSizeChange(val) {
      this.listQuery.pageSize = val
      this.page()
    },
    handleCurrentChange(val) {
      this.listQuery.pageNum = val
      this.page()
    },
    cancel() {
      this.dialogSpaceVisible = false
      this.spaceForm = this.init()
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
