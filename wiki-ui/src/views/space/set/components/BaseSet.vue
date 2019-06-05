<template>
  <div class="basicSet">
    <el-form :model="spaceForm" label-position="right" label-width="100px">
      <el-form-item label="空间名称">
        <el-input v-model="spaceForm.name" style="width: 600px;"/>
      </el-form-item>
      <el-form-item label="备注">
        <el-input v-model="spaceForm.remark" type="textarea" style="width: 600px;"/>
      </el-form-item>

      <el-form-item label="访问级别">
        <el-radio-group v-model="spaceForm.visitLevel">
          <el-radio :label="0" style="line-height: 30px;">私有空间</el-radio>
          <br>
          <el-radio :label="1" style="line-height: 30px;">共享空间</el-radio>
        </el-radio-group>
      </el-form-item>
    </el-form>
    <el-row class="row">
      <el-button type="success" round size="medium" @click="update">保 存</el-button>
      <el-button type="danger" round size="medium" @click="deleted">删 除</el-button>
    </el-row>
  </div>
</template>

<script>
export default {
  data() {
    return {
      spaceForm: {
        id: '',
        avatar: '',
        email: '',
        phone: '',
        nickName: ''
      },
      show: false
    }
  },
  methods: {
    deleted() {
      this.$prompt('请输入空间名称', '删除提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消'
      }).then(({ value }) => {
        this.$message({
          type: 'success',
          message: '你的空间名称是: ' + value
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '取消输入'
        })
      })
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss">
.basicSet{
    background: #fff;
    .row{
        line-height: 50px;
        padding: 20px;
        // .el-button--success{
        //     background: #ff7d44;
        //     border-color:#ff7d44;
        // }
        // .el-button--success:hover{
        //     background: #f98d5f;
        //     border-color:#f98d5f;
        // }
    }
}
</style>
