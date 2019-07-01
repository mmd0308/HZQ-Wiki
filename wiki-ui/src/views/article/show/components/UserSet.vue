<template>
  <div class="basicSet">
    <el-form :ref="ruleForm" :model="basicForm" :rules="rules" label-position="right" label-width="100px">
      <el-form-item label="用户头像">
        <div @click="showUserImg">
          <avatar :src="basicForm.img" :username="basicForm.username" size="80" />
        </div>
      </el-form-item>
      <el-form-item label="用户名称" prop="name">
        <el-input v-model="basicForm.name" style="width: 600px;"/>
      </el-form-item>
      <el-form-item label="用户邮箱" prop="name">
        <el-input v-model="basicForm.email" style="width: 600px;"/>
      </el-form-item>
      <el-form-item label="用户手机" prop="name">
        <el-input v-model="basicForm.phone" style="width: 600px;"/>
      </el-form-item>
      <el-form-item label="备注">
        <el-input v-model="basicForm.remark" type="textarea" style="width: 600px;"/>
      </el-form-item>
    </el-form>
    <el-row class="row">
      <el-button type="success" round size="medium" @click="update">保 存</el-button>
    </el-row>

    <my-upload
      :width="80"
      :height="80"
      :url="uploadUrl"
      :headers="headers"
      v-model="showUpload"
      field="file"
      img-format="png"
      @crop-success="cropSuccess"
      @crop-upload-success="cropUploadSuccess"
      @crop-upload-fail="cropUploadFail"/>
  </div>
</template>

<script>
import myUpload from 'vue-image-crop-upload'
import { getToken } from '@/utils/auth'
import { mapGetters } from 'vuex'
import { get, addOrUpdate } from '@/api/system/user/index'
import Avatar from 'vue-avatar'
export default {
  components: {
    'my-upload': myUpload,
    Avatar
  },
  data() {
    return {
      basicForm: this.init(),
      ruleForm: 'ruleForm',
      showUpload: false,
      uploadUrl: '/api/user/uploadImages',
      headers: {
        'Authorization': getToken()
      },
      rules: {
        name: [
          { required: true, message: '请输入用户名称', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '请输入邮箱地址', trigger: 'blur' },
          { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
        ],
        phone: [
          { required: true, message: '联系电话不能为空' }
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
    this.selectById()
  },
  methods: {
    init() {
      return {
        id: '',
        name: '',
        img: '',
        username: '',
        email: '',
        phone: '',
        remark: ''
      }
    },
    update() {
      this.$refs[this.ruleForm].validate((valid) => {
        if (valid) {
          addOrUpdate(this.basicForm).then(() => {
            this.$notify({
              title: '成功',
              message: '空间[' + this.basicForm.name + ']保存成功!',
              type: 'success'
            })
          })
        } else {
          return false
        }
      })
    },
    selectById() {
      get(this.userId).then(res => {
        this.basicForm = res.data
      })
    },
    showUserImg() {
      this.showUpload = !this.showUpload
    },
    cropSuccess(imgDataUrl, field) {
      this.basicForm.img = imgDataUrl
    },
    cropUploadSuccess(jsonData, field) {
      this.basicForm.img = jsonData.data
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
