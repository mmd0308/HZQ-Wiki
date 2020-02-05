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
            <el-form-item label="空间名称">
              <span>{{ props.row.name }}</span>
            </el-form-item>
            <el-form-item label="访问级别">
              <el-tag v-if="props.row.visitLevel != null" :type="spaceVisitLevel[props.row.visitLevel].status" >{{ spaceVisitLevel[props.row.visitLevel].text }}</el-tag>
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
        label="空间名称"/>

      <el-table-column
        label="访问级别"
        width="100" >
        <template slot-scope="scope">
          <el-tag v-if="scope.row.visitLevel != null" :type="spaceVisitLevel[scope.row.visitLevel].status" size="small">{{ spaceVisitLevel[scope.row.visitLevel].text }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column
        label="访问权限"
        width="100" >
        <template slot-scope="scope">
          <el-tag v-if="scope.row.userSpacePrivilege != null" :type="userSpacePrivilege[scope.row.userSpacePrivilege].status" size="small" >{{ userSpacePrivilege[scope.row.userSpacePrivilege].text }}</el-tag>
        </template>
      </el-table-column>

      <el-table-column
        prop="createName"
        label="创建人"
        width="130"/>

      <el-table-column
        fixed="right"
        label="操作"
        width="130">
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="handleMemberClick(scope.row)">成员</el-button>
          <el-button v-if="scope.row.userSpacePrivilege === 'OWNER' || scope.row.userSpacePrivilege === 'ADMINISTRATOR'" type="text" size="small" @click="handleEditClick(scope.row)">编辑</el-button>
          <el-popconfirm
            style="margin-left:10px"
            confirm-button-text="删除"
            confirm-button-type="danger"
            cancel-button-text="不用了"
            cancel-button-type="text"
            icon="el-icon-info"
            icon-color="red"
            title="您确定删除该条数据吗？"
            @onConfirm="handleRemoveById(scope.row.id)"
          >
            <el-button v-if="scope.row.userSpacePrivilege === 'OWNER'" slot="reference" type="text" size="small" >删除</el-button>
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

    <!--编辑 -->
    <el-drawer
      :visible.sync="drawer"
      :with-header="false"
      class="hzq-drawer">
      <h4 class="header">标签编辑</h4>
      <el-divider/>
      <div class="content">
        <el-form :model="drawerForm" label-width="80px">
          <el-form-item label="空间名称">
            <el-input v-model="drawerForm.name"/>
          </el-form-item>
          <el-form-item label="空间权限">
            <el-radio v-model="drawerForm.visitLevel" :label="spaceVisitLevel['PUBLIC'].value">公开</el-radio>
            <el-radio v-model="drawerForm.visitLevel" :label="spaceVisitLevel['PRIVATE'].value">私有</el-radio>
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
          <el-button v-if="drawerState === 'UPDATE'" size="medium" type="primary" @click="handleUpdateClick">更新 </el-button>
        </div>
      </div>
    </el-drawer>

    <!-- 添加成员 -->
    <el-drawer
      :visible.sync="drawerMember"
      :with-header="false"
      class="hzq-drawer">
      <h4 class="header">成员管理</h4>
      <el-divider/>
      <div class="content">
        <member ref="member" />
      </div>
    </el-drawer>
  </div>
</template>

<script>
import { page, updateById, deleteById, create } from '@/api/index'
import { spaceVisitLevel } from '@/api/space/SpaceConstants'
import { userSpacePrivilege } from '@/api/Constants'
import Member from './components/Member'
export default {
  components: {
    Member
  },
  data() {
    return {
      moudle: 'spaces',
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
      spaceVisitLevel: spaceVisitLevel,
      userSpacePrivilege: userSpacePrivilege,
      drawer: false,
      drawerForm: this.initDrawerForm(),
      drawerState: 'CREATE',
      drawerMember: false
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
        visitLevel: 'PUBLIC',
        remark: null
      }
    },
    handleToCreate() {
      this.drawer = true
      this.drawerForm = this.initDrawerForm()
      this.drawerState = 'CREATE'
    },
    handleSaveClick() {
      // 创建
      create(this.moudle, this.drawerForm).then(() => {
        this.drawer = false
        this.getPage()
      })
    },
    handleMemberClick(row) {
      this.drawerMember = true
      debugger
      this.$refs['member'].initPage(row.id)
    },
    handleEditClick(row) {
      this.drawer = true
      this.drawerState = 'UPDATE'
      this.drawerForm = this.initDrawerForm()
      this.drawerForm = row
    },
    handleUpdateClick() {
      // 编辑保存
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
