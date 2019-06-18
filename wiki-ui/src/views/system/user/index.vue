<template>
  <div class="space">
    <div class="header">
      <el-input v-model="listQuery.name" style="width:300px" placeholder="请输入空间名称" class="input-with-select" @keyup.enter.native="page">
        <el-button slot="append" icon="el-icon-search"/>
      </el-input>
      <el-button style="float:right;margin-right:30px;" type="success" @click="spaceToAdd">新增</el-button>
    </div>
    <el-table
      :data="userLists"
      style="width: 100%">
      <el-table-column
        width="120"
        label="用户名称">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column
        width="140"
        label="登陆账号">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.username }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="邮箱">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.email }}</span>
        </template>
      </el-table-column>
      <el-table-column
        width="120"
        label="电话">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.phone }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="130">
        <!-- admin的账号允许删除和修改 -->
        <template v-if="scope.row.id !== 1" slot-scope="scope">
          <el-button type="primary" size="mini" icon="el-icon-edit" @click="spaceToEdit(scope.$index, scope.row)" />
          <el-button type="danger" size="mini" icon="el-icon-delete" @click="spaceDelete(scope.$index, scope.row)" />
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
    <el-dialog :visible.sync="dialogUserVisible" :title="dialogTitle" width="40%">
      <el-form :ref="ruleForm" :model="userForm" :rules="rules" label-width="100px" >
        <el-form-item label="用户名称" prop="name">
          <el-input v-model="userForm.name" style="width:80%"/>
        </el-form-item>
        <el-form-item label="登陆账号" prop="username">
          <el-input v-model="userForm.username" style="width:80%"/>
        </el-form-item>
        <el-form-item v-if="status == 'add'" label="密码" prop="password">
          <el-input v-model="userForm.password" type="password" style="width:80%"/>
        </el-form-item>
        <el-form-item v-if="status == 'add'" label="确认密码" prop="checkPassword">
          <el-input v-model="userForm.checkPassword" type="password" style="width:80%"/>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="userForm.email" style="width:80%"/>
        </el-form-item>
        <el-form-item label="联系电话" prop="phone">
          <el-input v-model="userForm.phone" style="width:80%"/>
        </el-form-item>
        <el-form-item label="描述" prop="remark">
          <el-input v-model="userForm.remark" type="textarea" style="width:80%"/>
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
import { page, addOrUpdate, deletedById } from '@/api/system/user/index'
export default {
  data() {
    var validatePassword = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else {
        if (this.userForm.checkPassword !== '') {
          this.$refs.ruleForm.validateField('checkPassword')
        }
        callback()
      }
    }
    var validateCheckPassword = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.userForm.password) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
    return {
      dialogTitle: '新增用户',
      dialogUserVisible: false,
      status: '',
      userForm: this.init(),
      userLists: [],
      total: 0,
      listQuery: {
        pageNum: 1,
        pageSize: 10,
        name: ''
      },
      ruleForm: 'ruleForm',
      rules: {
        name: [
          { required: true, message: '请输入用户名称', trigger: 'blur' },
          { min: 2, max: 5, message: '长度在 2 到 5 个字符', trigger: 'blur' }
        ],
        username: [
          { required: true, message: '请输入登陆账号', trigger: 'blur' }
        ],
        password: [
          { required: true, validator: validatePassword, trigger: 'blur' }
        ],
        checkPassword: [
          { required: true, validator: validateCheckPassword, trigger: 'blur' }
        ],
        email: [
          { required: true, message: '请输入邮箱地址', trigger: 'blur' },
          { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
        ],
        phone: [
          { required: true, message: '联系电话不能为空' }
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
        username: '',
        password: '',
        checkPassword: '',
        email: '',
        phone: '',
        remark: ''
      }
    },
    spaceToAdd() {
      this.status = 'add'
      this.dialogUserVisible = true
    },
    spaceToEdit(index, row) {
      this.dialogTitle = '修改用户'
      this.status = 'edit'
      this.dialogUserVisible = true
      this.userForm = row
    },
    page() {
      page(this.listQuery).then(response => {
        this.userLists = response.data
        this.total = response.total
      })
    },
    addOrUpdate() {
      this.$refs[this.ruleForm].validate((valid) => {
        if (valid) {
          if (this.status === 'edit') { // 修改用户信息 不修改密码
            this.userForm.password = null
          }
          addOrUpdate(this.userForm).then(() => {
            this.dialogUserVisible = false
            this.userForm = this.init()
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
