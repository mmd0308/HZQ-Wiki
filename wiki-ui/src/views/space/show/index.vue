<template>
  <div class="hzq-wiki-main">
    <div style="padding-bottom: 10px;">
      <el-radio-group v-model="spaceVisitLevel" @change="changeSpaceVisitLevel">
        <el-radio-button label="0">个人空间</el-radio-button>
        <el-radio-button label="1"> 共享空间</el-radio-button>
      </el-radio-group>
      <el-button type="success" style="float:right" @click="spaceToAdd">
        新增空间
      </el-button>
    </div>

    <el-table
      :data="spaceLists"
      :show-header="false"
      style="width: 100%">
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" class="demo-table-expand">
            <el-form-item label="空间名称">
              <span>{{ props.row.name }}</span>
            </el-form-item>
            <el-form-item label="更新人">
              <span>{{ props.row.updateName }}</span>
            </el-form-item>
            <el-form-item label="最新更新时间">
              <span>{{ props.row.updateTime }}</span>
            </el-form-item>
            <el-form-item label="创建人">
              <span>{{ props.row.createName }}</span>
            </el-form-item>
            <el-form-item label="创建时间">
              <span>{{ props.row.createTime }}</span>
            </el-form-item>
            <el-form-item label="备注">
              <span>{{ props.row.remark }}</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>

      <el-table-column
        label="空间名称"
        prop="name">
        <template slot-scope="scope">
          <router-link :to="{ path: '/doc', query: { spaceId: scope.row.id }}">
            {{ scope.row.name }}
          </router-link>
        </template>
      </el-table-column>

      <el-table-column
        v-if="spaceVisitLevel === '0'"
        label="拥有权限"
        width="100"
        prop="privilege">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.privilege == null">拥有者</el-tag>
          <!--   1 浏览者 2 编辑者 3 管理员 -->
          <el-tag v-if="scope.row.privilege == '1'" type="success">浏览者</el-tag>
          <el-tag v-if="scope.row.privilege == '2'" type="warning">编辑者</el-tag>
          <el-tag v-if="scope.row.privilege == '3'" type="danger">管理员</el-tag>
        </template>
      </el-table-column>

      <el-table-column
        v-if="spaceVisitLevel === '0'"
        label="操作"
        width="50">
        <template slot-scope="scope">
          <router-link :to="{ path:'/space/set', query: { spaceId: scope.row.id }}">
            <i class="el-icon-setting"/>
          </router-link>
        </template>
      </el-table-column>
    </el-table>

    <div
      style="text-align: center; padding-top: 15px;">
      <el-pagination
        :total="total"
        :current-page="listQuery.pageNum"
        :page-size="listQuery.pageSize"
        background
        layout="prev, pager, next"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"/>
    </div>

    <!-- 新增或者修改项目 -->
    <el-dialog :visible.sync="dialogSpaceVisible" title="新增空间" width="40%">
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
import { page, showPage, addOrUpdate } from '@/api/space/index'
import { mapGetters } from 'vuex'
export default {

  data() {
    return {
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
      spaceVisitLevel: '0',
      spaceLists: [],
      total: 0,
      listQuery: {
        pageNum: 1,
        pageSize: 10,
        name: '',
        visitLevel: this.spaceVisitLevel
      },
      ruleForm: 'ruleForm',
      rules: {
        name: [
          { required: true, message: '请输入空间名称', trigger: 'blur' }
        ]
      }
    }
  },
  computed: {
    ...mapGetters([
      'userId'
    ])
  },
  created() {
    if (this.spaceVisitLevel === '0') {
      this.showPage()
    } else {
      this.page()
    }
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
    addOrUpdate() {
      this.$refs[this.ruleForm].validate((valid) => {
        if (valid) {
          addOrUpdate(this.spaceForm).then(() => {
            this.dialogSpaceVisible = false
            this.spaceForm = this.init()
            if (this.spaceVisitLevel === '1') {
              this.page()
            } else {
              this.showPage()
            }
          })
        } else {
          return false
        }
      })
    },
    showPage() {
      showPage(this.listQuery, this.userId).then(response => {
        this.spaceLists = response.data
        this.total = response.total
      })
    },
    page() {
      page(this.listQuery).then(response => {
        this.spaceLists = response.data
        this.total = response.total
      })
    },
    changeSpaceVisitLevel(val) {
      if (val === '1') {
        this.listQuery.visitLevel = val
        this.page()
      } else {
        this.showPage()
      }
    },
    cancel() {
      this.dialogSpaceVisible = false
      this.spaceForm = this.init()
    },
    handleSizeChange(val) {
      this.listQuery.pageSize = val
      if (this.spaceVisitLevel === '1') {
        this.page()
      } else {
        this.showPage()
      }
    },
    handleCurrentChange(val) {
      this.listQuery.pageNum = val
      if (this.spaceVisitLevel === '1') {
        this.page()
      } else {
        this.showPage()
      }
    }
  }
}
</script>
<style rel="stylesheet/scss" lang="scss" scoped>
  .demo-table-expand {
    font-size: 0;
  }
  .demo-table-expand label {
    width: 90px;
    color: #99a9bf;
  }
  .demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 50%;
  }
</style>
