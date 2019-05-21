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
          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item label="空间名称">
              <span>{{ props.row.name }}</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column
        label="空间名称"
        prop="name">
        <template slot-scope="scope">
          <router-link to="/doc">
            {{ scope.row.name }}
          </router-link>
        </template>
      </el-table-column>
      <el-table-column
        label="操作"
        width="50">
        <template slot-scope="scope">
          <i class="el-icon-setting"/>
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
import { page, addOrUpdate, deletedById } from '@/api/space/index'
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
    page() {
      page(this.listQuery).then(response => {
        this.spaceLists = response.data
        this.total = response.total
      })
    },
    changeSpaceVisitLevel(val) {
      this.listQuery.visitLevel = val
      this.page()
    },
    cancel() {
      this.dialogSpaceVisible = false
      this.spaceForm = this.init()
    },
    handleSizeChange(val) {
      this.listQuery.pageSize = val
      this.page()
    },
    handleCurrentChange(val) {
      this.listQuery.pageNum = val
      this.page()
    }
  }
}
</script>
<style rel="stylesheet/scss" lang="scss" scoped>

</style>
