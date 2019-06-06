<template>
  <div class="basicSet">
    <el-form :ref="ruleForm" :model="spaceForm" :rules="rules" label-position="right" label-width="100px">
      <el-form-item label="空间名称" prop="name">
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
import { get, addOrUpdate, deletedById } from '@/api/space/index'
export default {
  props: {
    spaceId: {
      type: Number,
      required: true
    }
  },
  data() {
    return {
      spaceForm: {
        name: '',
        visitLevel: -1,
        remark: ''
      },
      ruleForm: 'ruleForm',
      rules: {
        name: [
          { required: true, message: '请输入空间名称', trigger: 'blur' }
        ]
      }
    }
  },
  created() {
    this.selectById()
  },
  methods: {
    selectById() {
      get(this.spaceId).then(res => {
        this.spaceForm = res.data
      })
    },
    update() {
      this.$refs[this.ruleForm].validate((valid) => {
        if (valid) {
          addOrUpdate(this.spaceForm).then(() => {
            this.$notify({
              title: '成功',
              message: '空间[' + this.spaceForm.name + ']保存成功!',
              type: 'success'
            })
          })
        } else {
          return false
        }
      })
    },
    deleted() {
      this.$prompt('请输入空间名称', '删除提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消'
      }).then(({ value }) => {
        if (value === this.spaceForm.name) {
          deletedById(this.spaceId).then(res => {
            this.$router.push({ path: '/space' })
          })
        } else {
          this.$notify({
            title: '错误',
            message: '你的空间名称输入错误,删除失败!',
            type: 'error'
          })
        }
      }).catch(() => {
        console.log('取消删除成功')
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
  }
}
</style>
