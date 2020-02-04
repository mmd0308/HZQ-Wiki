<template>
  <div class="hzq-admin">
    <div class="headerForm">
      <el-form :inline="true" :model="formInline" class="demo-form-inline">
        <el-form-item label="关键字">
          <el-input v-model="formInline.user" size="small" placeholder="审批人"/>
        </el-form-item>
        <el-form-item label="文章状态">
          <el-select v-model="formInline.region" size="small" placeholder="活动区域">
            <el-option label="区域一" value="shanghai"/>
            <el-option label="区域二" value="beijing"/>
          </el-select>
        </el-form-item>
        <el-form-item style="float:right">
          <el-button type="primary" size="small" >查询</el-button>
          <el-button type="info" size="small" >重置</el-button>
          <el-button type="success" size="small" @click="handleToCreate">新增</el-button>
        </el-form-item>
      </el-form>
    </div>

    <el-table
      v-loading="tableLoading"
      :data="tableLists"
      :header-cell-style="{background:'#545c64',color:'#ffffff'}"
      element-loading-text="拼命加载中"
      element-loading-spinner="el-icon-loading"
      element-loading-background="rgba(255, 255, 255, 0.83)"
      tooltip-effect="dark"
      style="width: 100%">
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" class="demo-table-expand">
            <el-form-item label="文档名称">
              <span>{{ props.row.name }}</span>
            </el-form-item>
            <el-form-item label="访问级别">
              <el-tag v-if="props.row.visitLevel != null" :type="docVisitLevel[props.row.visitLevel].status" >{{ docVisitLevel[props.row.visitLevel].text }}</el-tag>
            </el-form-item>
            <el-form-item label="备注">
              <span>{{ props.row.remark }}</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>

      <el-table-column
        type="selection"
        width="55"/>
      <el-table-column
        prop="name"
        label="文档名称"/>

      <el-table-column
        label="访问级别"
        width="130" >
        <template slot-scope="scope">
          <el-tag v-if="scope.row.visitLevel != null" :type="docVisitLevel[scope.row.visitLevel].status" size="small">{{ docVisitLevel[scope.row.visitLevel].text }}</el-tag>
        </template>
      </el-table-column>

      <el-table-column
        label="访问权限"
        width="100" >
        <template slot-scope="scope">
          <el-tag v-if="scope.row.userDocPrivilege != null" :type="userDocPrivilege[scope.row.userDocPrivilege].status" size="small" >{{ userDocPrivilege[scope.row.userDocPrivilege].text }}</el-tag>
        </template>
      </el-table-column>

      <el-table-column
        prop="createName"
        label="创建人"
        width="130"/>

      <el-table-column
        fixed="right"
        label="操作"
        width="100">
        <template slot-scope="scope">
          <el-button v-if="scope.row.userDocPrivilege === 'OWNER' || scope.row.userDocPrivilege === 'ADMINISTRATOR'" type="text" size="small" @click="handleEditClick(scope.row)">编辑</el-button>
          <el-popconfirm
            confirm-button-text="删除"
            confirm-button-type="danger"
            cancel-button-text="不用了"
            cancel-button-type="text"
            icon="el-icon-info"
            icon-color="red"
            title="您确定删除该条数据吗？"
            @onConfirm="handleRemoveById(scope.row.id)"
          >
            <el-button v-if="scope.row.userDocPrivilege === 'OWNER'" slot="reference" type="text" size="small" >删除</el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <div style="padding:15px 0px 0px;float:right">
      <el-pagination
        :current-page="listQuery.pageNum"
        :page-sizes="[10, 20, 30, 40]"
        :page-size="listQuery.pageSize"
        :total="total"
        layout="total, sizes, prev, pager, next, jumper"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"/>
    </div>

    <!--编辑/新增 -->
    <el-drawer
      :visible.sync="drawer"
      :with-header="false"
      class="hzq-drawer">
      <h4 class="header">{{ drawerState === 'CREATE' ? '新增文档':'编辑文档' }}</h4>
      <el-divider/>
      <div class="content">
        <el-form :model="drawerForm" label-width="80px">
          <el-form-item label="文档名称">
            <el-input v-model="drawerForm.name"/>
          </el-form-item>
          <el-form-item label="备注说明">
            <el-input
              v-model="drawerForm.remark"
              type="textarea"
              placeholder="请输入内容"
              maxlength="100"
              show-word-limit
            />
          </el-form-item>
        </el-form>

        <div class="footer">
          <el-button size="medium" type="info" @click="drawer = false">取消</el-button>
          <el-button v-if="drawerState === 'CREATE'" size="medium" type="primary" @click="handleSaveClick">保存 </el-button>
          <el-button v-if="drawerState === 'UPDATE'" size="medium" type="primary" @click="handleUpdateClick">更新  </el-button>
        </div>
      </div>
    </el-drawer>
  </div>
</template>

<script>
import { create, page, updateById, deleteById } from '@/api/index'
import { docVisitLevel } from '@/api/doc/DocConstants'
import { userDocPrivilege } from '@/api/Constants'
export default {
  data() {
    return {
      moudle: 'docs',
      formInline: {
        user: '',
        region: ''
      },
      tableLists: [],
      tableLoading: false,
      total: 0,
      listQuery: {
        pageNum: 1,
        pageSize: 10
      },
      docVisitLevel: docVisitLevel,
      userDocPrivilege: userDocPrivilege,
      drawer: false,
      drawerForm: this.initDrawerForm(),
      drawerState: 'CREATE'
    }
  },
  methods: {
    init() {
      this.getPage()
    },
    initDrawerForm() {
      return {
        id: null,
        name: null,
        remark: null
      }
    },
    handleToCreate() {
      this.drawerForm = this.initDrawerForm()
      this.drawer = true
      this.drawerState = 'CREATE'
    },
    handleEditClick(row) {
      this.drawer = true
      this.drawerState = 'UPDATE'
      this.drawerForm = this.initDrawerForm()
      this.drawerForm = row
    },
    handleSaveClick() {
      // 创建
      create(this.moudle, this.drawerForm).then(() => {
        this.drawer = false
        this.getPage()
      })
    },
    handleUpdateClick() {
      // 更新
      updateById(this.moudle, this.drawerForm).then(() => {
        this.drawer = false
      })
    },
    handleRemoveById(id) {
      deleteById(this.moudle, id).then(() => {
        this.getPage()
      })
    },
    getPage() {
      this.tableLoading = true
      page(this.moudle, this.listQuery).then(res => {
        this.tableLists = res.records
        this.total = res.total
        this.tableLoading = false
      })
    },
    handleSizeChange(val) {
      this.listQuery.pageSize = val
      this.getPage()
    },
    handleCurrentChange(val) {
      this.listQuery.pageNum = val
      this.getPage()
    }
  }
}
</script>
<style>
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
