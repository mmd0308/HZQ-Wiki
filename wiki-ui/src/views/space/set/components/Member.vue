<template>
  <div class="space">
    <div class="header">
      <el-form :ref="ruleForm" :inline="true" :rules="rules" :model="userSpaceForm" class="demo-form-inline">
        <el-form-item prop="userId">
          <el-select v-model="userSpaceForm.userId" filterable placeholder="请选择用户">
            <el-option
              v-for="item in nonSpaceUserLists"
              :key="item.id"
              :label="item.name"
              :value="item.id"/>
          </el-select>
        </el-form-item>
        <el-select v-model="userSpaceForm.privilege" style="width:160px">
          <el-option
            v-for="item in privilegeData"
            v-if="item.value != 0"
            :key="item.value"
            :label="item.label"
            :value="item.value"/>
        </el-select>

        <el-form-item prop="expireTime">
          <el-date-picker
            v-model="userSpaceForm.expireTime"
            type="date"
            style="width:160px;"
            value-format="yyyy-MM-dd"
            placeholder="选择过期时间"/>
        </el-form-item>
        <el-button style="float:right;margin-right:30px;" type="success" @click="addUserToSpace">加入空间</el-button>
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
        <template v-if="scope.row.privilege != '0'" slot-scope="scope">
          <el-button
            size="mini"
            type="danger"
            @click="spaceDelete(scope.$index, scope.row)">
            <i class="el-icon-delete"/>
          </el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { userSpaceAll, deletedById, addOrUpdate, spaceNonUserAll } from '@/api/space/userSpace'
export default {
  props: {
    spaceId: {
      type: Number,
      required: true
    }
  },
  data() {
    return {
      privilegeData: [
        { value: 0, label: '拥有者' },
        { value: 1, label: '浏览者' },
        { value: 2, label: '编辑者' },
        { value: 3, label: '管理员' }
      ],
      nonSpaceUserLists: [],
      state1: '',
      dialogTitle: '新增用户',
      dialogUserVisible: false,
      status: '',
      userSpaceForm: this.init(),
      userLists: [],
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
        spaceId: this.spaceId,
        privilege: 1,
        expireTime: ''
      }
    },
    page() {
      userSpaceAll(this.listQuery, this.spaceId).then(response => {
        this.userLists = response.data
      })
    },
    spaceDelete(index, row) {
      this.$confirm('此操作将[' + row.fullName + ']用户移除该空间?', '提示', {
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
      spaceNonUserAll(this.spaceId).then(res => {
        this.nonSpaceUserLists = res.data
      })
    },
    addUserToSpace() {
      this.$refs[this.ruleForm].validate((valid) => {
        if (valid) {
          addOrUpdate(this.userSpaceForm).then(() => {
            this.$notify({
              title: '成功',
              message: '成员加入成功!',
              type: 'success'
            })
            this.spaceNonUserAll()
            this.page()
            this.userSpaceForm = this.init()
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
