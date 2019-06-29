<template>
  <div class="log">
    <el-table
      :data="logLists"
      style="width: 100%">
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" label-width="110px" class="demo-table-expand">
            <el-form-item label="登陆账户">
              <span>{{ props.row.username }}</span>
            </el-form-item>
            <el-form-item label="系统">
              <span>{{ props.row.system }}</span>
            </el-form-item>
            <el-form-item label="浏览器">
              <span>{{ props.row.browser }}</span>
            </el-form-item>
            <el-form-item label="IP">
              <span>{{ props.row.ip }}</span>
            </el-form-item>
            <el-form-item label="登陆时间">
              <span>{{ props.row.createTime }}</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>

      <el-table-column
        width="150px"
        label="登陆用户">
        <template slot-scope="scope">
          <span >{{ scope.row.username }}</span>
        </template>
      </el-table-column>
      <el-table-column
        width="130px"
        label="登陆IP">
        <template slot-scope="scope">
          <span >{{ scope.row.ip }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="浏览器">
        <template slot-scope="scope">
          <span >{{ scope.row.browser }}</span>
        </template>
      </el-table-column>
      <el-table-column
        width="155px"
        label="登陆时间">
        <template slot-scope="scope">
          <span >{{ scope.row.createTime }}</span>
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
  </div>
</template>

<script>
import { page } from '@/api/log/loginLog'
export default {
  data() {
    return {
      status: '',
      logLists: [],
      total: 0,
      listQuery: {
        pageNum: 1,
        pageSize: 10,
        value: ''
      }
    }
  },
  created() {
    this.page()
  },
  methods: {
    page() {
      page(this.listQuery).then(response => {
        this.logLists = response.data
        this.total = response.total
      })
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
