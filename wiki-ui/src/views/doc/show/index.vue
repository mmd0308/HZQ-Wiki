<template>
  <div class="hzq-wiki-main-width">
    <div style="padding-bottom: 10px;">
      <el-radio-group v-model="docVisitLevel" @change="changeDocVisitLevel">
        <el-radio-button label="0">个人文档</el-radio-button>
        <el-radio-button label="1"> 共享文档</el-radio-button>
      </el-radio-group>
      <el-button type="success" style="float:right" @click="docToAdd">
        新增文档
      </el-button>
    </div>

    <el-table
      :data="docLists"
      :show-header="false"
      style="width: 100%">
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item label="文档名称">
              <div @click="toLink(props.row)">
                <span>{{ props.row.name }}</span>
              </div>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column
        label="文档名称"
        prop="name">
        <template slot-scope="scope" >
          <div @click="toLink(scope.row)">
            {{ scope.row.name }}
          </div>
        </template>
      </el-table-column>
      <el-table-column
        v-if="docVisitLevel === '0'"
        label="拥有权限"
        width="100"
        prop="privilege">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.privilege == '0'">拥有者</el-tag>
          <!--  0 拥有者 1 浏览者 2 编辑者 3 管理员 -->
          <el-tag v-if="scope.row.privilege == '1'" type="success">浏览者</el-tag>
          <el-tag v-if="scope.row.privilege == '2'" type="warning">编辑者</el-tag>
          <el-tag v-if="scope.row.privilege == '3'" type="danger">管理员</el-tag>
        </template>
      </el-table-column>
      <el-table-column
        v-if="docVisitLevel === '0'"
        label="操作"
        width="50">
        <template slot-scope="scope">
          <router-link :to="{ path:'/set/doc', query: { docId: scope.row.id, spaceId: scope.row.spaceId }}">
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
    <el-dialog :visible.sync="dialogDocVisible" :title="dialogTitle" width="40%">
      <el-form :model="docForm" :ref="ruleForm" :rules="rules" label-width="100px" >
        <el-form-item label="文档名称" prop="name">
          <el-input v-model="docForm.name" style="width:80%"/>
        </el-form-item>
        <el-form-item label="访问级别" prop="visitLevel">
          <el-select v-model="docForm.visitLevel" placeholder="请选择" style="width:80%">
            <el-option
              v-for="item in visitLevels"
              :key="item.value"
              :label="item.label"
              :value="item.value"/>
          </el-select>
        </el-form-item>
        <el-form-item label="描述" prop="remark">
          <el-input v-model="docForm.remark" type="textarea" style="width:80%"/>
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
import { page, addOrUpdate, showDocPage } from '@/api/doc/index'
import { mapGetters } from 'vuex'
export default {
  data() {
    return {
      dialogTitle: '新增文档',
      dialogDocVisible: false,
      docForm: this.init(),
      ruleForm: 'ruleForm',
      rules: {
        name: [
          { required: true, message: '请输入文档名称', trigger: 'blur' }
        ]
      },
      visitLevels: [
        { value: 0, label: '私有文档' },
        { value: 1, label: '共享文档' }
      ],
      docVisitLevel: '0',
      docLists: [],
      spaceId: '',
      total: 0,
      listQuery: {
        pageNum: 1,
        pageSize: 10,
        visitLevel: '0',
        spaceId: ''
      }
    }
  },
  computed: {
    ...mapGetters([
      'userId'
    ])
  },
  created() {
    this.spaceId = this.$route.query.spaceId
    if (this.docVisitLevel === '0') {
      this.showDocPage()
    } else {
      this.page()
    }
  },
  methods: {
    init() {
      return {
        name: '',
        spaceId: '',
        visitLevel: 0,
        remark: ''
      }
    },
    page() {
      this.listQuery.visitLevel = this.docVisitLevel
      page(this.listQuery).then(response => {
        this.docLists = response.data
        this.total = response.total
      })
    },
    showDocPage() {
      this.listQuery.spaceId = this.spaceId
      showDocPage(this.listQuery, this.userId).then(response => {
        this.docLists = response.data
        this.total = response.total
      })
    },
    changeDocVisitLevel(val) {
      if (val === '0') {
        this.showDocPage()
      } else {
        this.page()
      }
    },
    docToAdd() {
      this.dialogDocVisible = true
      this.docForm.spaceId = this.spaceId
    },
    addOrUpdate() {
      this.$refs[this.ruleForm].validate((valid) => {
        if (valid) {
          addOrUpdate(this.docForm).then(() => {
            if (this.docVisitLevel === '0') {
              this.showDocPage()
            } else {
              this.page()
            }
            this.cancel()
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
    toLink(item) {
      this.$router.push({
        path: '/edit/doc',
        query: {
          docId: item.id,
          docName: item.name,
          spaceId: item.spaceId
        }
      })
    },
    cancel() {
      this.dialogDocVisible = false
      this.docForm = this.init()
    }
  }
}
</script>
