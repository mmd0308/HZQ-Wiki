<template>
  <div class="space">
    <div class="header">
      <el-form :ref="ruleForm" :rules="rules" :model="userSpaceForm" label-width="80px" >

        <el-form-item prop="userId" label="选择用户">
          <el-select v-model="userSpaceForm.userId" filterable placeholder="请选择用户" style="width:100%" >
            <el-option
              v-for="item in nonSpaceUserLists"
              :key="item.id"
              :label="item.name"
              :value="item.id"/>
          </el-select>
        </el-form-item>

        <el-form-item label="选择权限">
          <el-select v-model="userSpaceForm.privilege" style="width:100%" >
            <el-option
              v-for="item in userSpacePrivilege"
              v-if="item.value != 'OWNER'"
              :key="item.value"
              :label="item.text"
              :value="item.value"/>
          </el-select>
        </el-form-item>

        <el-form-item label="过期时间" prop="expireTime" >
          <el-date-picker
            v-model="userSpaceForm.expireTime"
            style="width:100%"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择过期时间"/>
        </el-form-item>
        <el-button type="success" @click="addUserToSpace">加入空间</el-button>
      </el-form>
    </div>

    <div style="margin-top:20px">
      <el-table
        :data="userLists"
        :header-cell-style="{background:'#545c64',color:'#ffffff'}"
        style="width: 100%">
        <el-table-column
          label="用户名称">
          <template slot-scope="scope">
            <span style="margin-left: 10px">{{ scope.row.fullName }}</span>
          </template>
        </el-table-column>
        <el-table-column
          label="操作权限"
          width="80" >
          <template slot-scope="scope">
            <el-tag v-if="scope.row.privilege != null" :type="userSpacePrivilege[scope.row.privilege].status" size="small" >{{ userSpacePrivilege[scope.row.privilege].text }}</el-tag>
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
  </div>
</template>

<script>
import { userSpaceAll, addOrUpdate, spaceNonUserAll } from '@/api/space/userSpace'
import { create, deleteById } from '@/api/index'
import { userSpacePrivilege } from '@/api/Constants'
export default {
  props: {
    spaceId: {
      type: Number,
      required: true
    }
  },
  data() {
    return {
      userSpacePrivilege: userSpacePrivilege,
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
    this.initPage(null)
  },
  methods: {
    init() {
      return {
        userId: null,
        spaceId: null,
        privilege: null,
        expireTime: null
      }
    },
    initPage(spaceId) {
      if (spaceId != null) {
        this.spaceId = spaceId
      }
      this.page()
      this.spaceNonUserAll()
    },
    page() {
      userSpaceAll(this.spaceId).then(response => {
        this.userLists = response
      })
    },
    spaceDelete(index, row) {
      this.$confirm('此操作将[' + row.fullName + ']用户移除该空间?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        typr: 'warning'
      }).then(() => {
        deleteById('users/spaces', row.id).then(() => {
          this.page()
          this.spaceNonUserAll()
        })
      })
    },
    spaceNonUserAll() {
      spaceNonUserAll(this.spaceId).then(res => {
        this.nonSpaceUserLists = res
      })
    },
    addUserToSpace() {
      this.$refs[this.ruleForm].validate((valid) => {
        if (valid) {
          this.userSpaceForm.spaceId = this.spaceId
          create('users/spaces', this.userSpaceForm).then(() => {
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
