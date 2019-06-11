<template>
  <div class="space">
    <div class="header">
      <el-input v-model="listQuery.value" style="width:300px" placeholder="请输入值" class="input-with-select" @keyup.enter.native="page">
        <el-button slot="append" icon="el-icon-search"/>
      </el-input>
      <el-button style="float:right;margin-right:30px;" type="success" @click="toAdd">新增</el-button>
    </div>

    <el-table
      :data="configLists"
      style="width: 100%">
      <el-table-column
        label="键(KEY)">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.key }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="值(VALUE)">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.value }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="描述">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.remark }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="150">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="primary"
            @click="toEdit(scope.$index, scope.row)">编辑</el-button>
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
    <el-dialog :visible.sync="dialogVisible" :title="dialogTitle" width="40%">
      <el-form :ref="ruleForm" :model="configForm" :rules="rules" label-width="100px" >
        <el-form-item label="键(KEY)" prop="key">
          <el-input v-if="status === 'add'" v-model="configForm.key" style="width:80%"/>
          <el-input v-else v-model="configForm.key" disabled="true" style="width:80%"/>
        </el-form-item>
        <el-form-item label="值(VALUE)" prop="value">
          <el-input v-model="configForm.value" type="textarea" style="width:80%"/>
        </el-form-item>
        <el-form-item label="描述" prop="remark">
          <el-input v-model="configForm.remark" type="textarea" style="width:80%"/>
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
import { page, addOrUpdate, checkKey } from '@/api/config/index'
export default {
  data() {
    var validateKey = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入键'))
      } else {
        checkKey(value).then(response => {
          if (response.data === false) {
            callback(new Error('键重复,不能使用!'))
          } else {
            callback()
          }
        })
      }
    }
    return {
      dialogTitle: '新增配置',
      dialogVisible: false,
      status: '',
      configForm: this.init(),
      configLists: [],
      total: 0,
      listQuery: {
        pageNum: 1,
        pageSize: 10,
        value: ''
      },
      ruleForm: 'ruleForm',
      rules: {
        key: [
          { validator: validateKey, trigger: 'blur', required: true }
        ],
        value: [
          { required: true, message: '请输入值', trigger: 'blur' }
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
        key: '',
        value: '',
        remark: ''
      }
    },
    toAdd() {
      this.status = 'add'
      this.dialogVisible = true
      this.configForm = this.init()
    },
    toEdit(index, row) {
      this.dialogTitle = '修改配置'
      this.status = 'edit'
      this.dialogVisible = true
      this.configForm = this.init()
      this.configForm = row
    },
    checkKey() {
      checkKey('k').then(res => {

      })
    },
    page() {
      page(this.listQuery).then(response => {
        this.configLists = response.data
        this.total = response.total
      })
    },
    addOrUpdate() {
      this.$refs[this.ruleForm].validate((valid) => {
        if (valid) {
          addOrUpdate(this.configForm).then(() => {
            this.dialogVisible = false
            this.configForm = this.init()
            this.page()
          })
        } else {
          return false
        }
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
      this.dialogVisible = false
      this.configForm = this.init()
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
