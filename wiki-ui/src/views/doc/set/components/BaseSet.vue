<template>
  <div class="basicSet">
    <el-form :ref="ruleForm" :model="docForm" label-position="right" label-width="100px">
      <el-form-item label="文档封面">
        <img
          v-if="docForm.img"
          :src="docForm.img"
          style="width: 230px;height: 190px;"
          @click="showDocImg">
        <div v-else style="width: 230px;height: 190px;    border: 1px solid dimgrey; text-align: center;" @click="showDocImg">
          请选择文档封面
        </div>
      </el-form-item>
      <el-form-item label="空间名称" prop="name">
        <el-input v-model="docForm.name" style="width: 600px;"/>
      </el-form-item>
      <el-form-item label="备注">
        <el-input v-model="docForm.remark" type="textarea" style="width: 600px;"/>
      </el-form-item>
      <el-form-item label="访问级别">
        <el-radio-group v-model="docForm.visitLevel">
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

    <my-upload
      :width="247"
      :height="220"
      :url="uploadUrl"
      :headers="headers"
      v-model="showUpload"
      :lang-ext="docZh"
      :params="uploadParams"
      :no-circle="true"
      field="file"
      img-format="png"
      @crop-success="cropSuccess"
      @crop-upload-success="cropUploadSuccess"
      @crop-upload-fail="cropUploadFail"/>
  </div>
</template>

<script>
import { get, addOrUpdate, deletedById } from '@/api/doc/index'
import myUpload from 'vue-image-crop-upload'
import { getToken } from '@/utils/auth'
export default {
  components: {
    'my-upload': myUpload
  },
  data() {
    return {
      docId: this.$route.params.id,
      uploadUrl: '/api/doc/uploadImages',
      headers: {
        'Authorization': getToken()
      },
      uploadParams: {
        docId: this.docId
      },
      docZh: {
        preview: '文档封面预览'
      },
      docForm: this.init(),
      ruleForm: 'ruleForm',
      showUpload: false
    }
  },
  created() {
    this.selectById()
  },
  methods: {
    init() {
      return {
        id: '',
        name: '',
        spaceId: '',
        remark: '',
        visitLevel: -1,
        img: ''
      }
    },
    update() {
      this.$refs[this.ruleForm].validate((valid) => {
        if (valid) {
          addOrUpdate(this.docForm).then(() => {
            this.$notify({
              title: '成功',
              message: '空间[' + this.docForm.name + ']保存成功!',
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
        if (value === this.docForm.name) {
          deletedById(this.docId).then(res => {
            this.$router.push({ path: '/doc?spaceId=' + this.docForm.spaceId })
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
    },
    selectById() {
      get(this.docId).then(res => {
        this.docForm = res.data
      })
    },
    showDocImg() {
      this.showUpload = !this.showUpload
    },
    cropSuccess(imgDataUrl, field) {
      this.docForm.img = imgDataUrl
    },
    cropUploadSuccess(jsonData, field) {
      this.docForm.img = jsonData.data
      this.showUpload = false
    },
    cropUploadFail(status, field) {
      console.log('-------- upload fail --------')
      console.log(status)
      console.log('field: ' + field)
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss">
.basicSet{
    background: #fff;
    .row{
        border-bottom: 1px solid #f0f0f0;
        line-height: 50px;
        padding: 20px;
        .updateAct{
            background: #ff7d44;
            width: 100px;
            line-height: 30px;
            margin: auto 0px;
            text-align: center;
            border-radius: 20px;
            margin-top: 40px;
        }
        .pic{
            width: 110px;
            height: 110px;
            display: block;
            cursor: pointer;
            margin: auto;
            float: left;
        }
        .pic img{
            width: 100%;
            height: 100%;
            border-radius: 50%;
        }
        .el-button--success{
            background: #ff7d44;
            border-color:#ff7d44;
        }
        .el-button--success:hover{
            background: #f98d5f;
            border-color:#f98d5f;
        }
    }
}
</style>
