<template>
  <div class="space">
    <div class="header">
      <el-form :ref="ruleForm" :inline="true" :rules="rules" :model="userDocForm" class="demo-form-inline">
        <el-form-item prop="userId">
          <el-select v-model="userDocForm.userId" filterable placeholder="请选择用户">
            <el-option
              v-for="item in nonDocUserLists"
              :key="item.id"
              :label="item.name"
              :value="item.id"/>
          </el-select>
        </el-form-item>
        <el-select v-model="userDocForm.privilege" style="width:160px">
          <el-option
            v-for="item in privilegeData"
            v-if="item.value != 0"
            :key="item.value"
            :label="item.label"
            :value="item.value"/>
        </el-select>

        <el-form-item prop="expireTime">
          <el-date-picker
            v-model="userDocForm.expireTime"
            type="date"
            style="width:160px;"
            value-format="yyyy-MM-dd"
            placeholder="选择过期时间"/>
        </el-form-item>

        <el-button style="float:right;margin-right:30px;" type="success" @click="addUserToDoc">加入</el-button>
      </el-form>
    </div>

    <el-table
      :data="userLists"
      style="width: 100%">
      <el-table-column
        label="用户名称">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.fullName }}</span>
        </template>
      </el-table-column>
      <el-table-column label="加入时间" width="170">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.createTime }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作权限" width="130">
        <template slot-scope="scope">
          <el-select v-model="scope.row.privilege" disabled placeholder="请选择">
            <el-option
              v-for="item in privilegeData"
              :key="item.value"
              :label="item.label"
              :value="item.value"/>
          </el-select>
        </template>
      </el-table-column>
      <el-table-column label="到期时间" width="110">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.expireTime }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="80">
        <template slot-scope="scope">
          <el-button
            v-if="scope.row.privilege != '0' && scope.row.spaceId == null "
            size="mini"
            type="danger"
            @click="spaceDelete(scope.$index, scope.row)">
            <i class="el-icon-delete"/>
          </el-button>
          <el-tag v-if="scope.row.spaceId != null" >组成员</el-tag>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { userDocAndSpaceAll, deletedById, addOrUpdate, spaceNonUserAll } from '@/api/doc/userDoc'
export default {
  data() {
    return {
      docId: this.$route.params.id,
      spaceId: this.$route.params.spaceId,
      privilegeData: [
        { value: 0, label: '拥有者' },
        { value: 1, label: '浏览者' },
        { value: 2, label: '编辑者' },
        { value: 3, label: '管理员' }
      ],
      nonDocUserLists: [],
      state1: '',
      dialogTitle: '新增用户',
      dialogUserVisible: false,
      status: '',
      userDocForm: this.init(),
      userSpaceLists: [],
      userLists: [],
      total: 0,
      listQuery: {
        name: ''
      },
      ruleForm: 'ruleForm',
      rules: {
        userId: [
          { required: true, message: '请选择用户', trigger: 'blur' }
        ],
        expireTime: [
          { required: true, message: '请选择到期时间', trigger: 'blur' }
        ]
      }
    }
  },
  created() {
    this.page()
    this.spaceNonUserAll()
  },
  methods: {
    init() {
      return {
        userId: '',
        docId: this.docId,
        privilege: 1,
        expireTime: ''
      }
    },
    page() {
      userDocAndSpaceAll(this.docId, this.spaceId).then(response => {
        this.userLists = response.data
      })
    },
    spaceDelete(index, row) {
      this.$confirm('此操作将[' + row.fullName + ']用户移除该文档?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        typr: 'warning'
      }).then(() => {
        deletedById(row.id).then(() => {
          this.$notify({
            title: '成功',
            message: '用户[' + row.fullName + ']删除成功!',
            type: 'success'
          })
          this.page()
          this.spaceNonUserAll()
        })
      })
    },
    spaceNonUserAll() {
      spaceNonUserAll(this.docId).then(res => {
        this.nonDocUserLists = res.data
      })
    },
    addUserToDoc() {
      this.$refs[this.ruleForm].validate((valid) => {
        if (valid) {
          addOrUpdate(this.userDocForm).then(() => {
            this.$notify({
              title: '成功',
              message: '成员加入成功!',
              type: 'success'
            })
            this.spaceNonUserAll()
            this.page()
            this.userDocForm = this.init()
          })
        } else {
          return false
        }
      })
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
