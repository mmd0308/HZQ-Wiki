<template>
  <div class="hzq-admin">
    <div class="basic">
      <div class="form">
        <el-form :ref="ruleForm" :model="basicForm" :rules="rules" label-position="right" label-width="100px">
          <el-form-item label="用户头像">
            <div v-if="fromState == 'R'">
              <avatar :src="basicForm.img" :username="basicForm.username" :size="80" />
            </div>
            <div v-else @click="showUserImg">
              <avatar :src="basicForm.img" :username="basicForm.username" :size="80" />
            </div>
          </el-form-item>
          <el-form-item label="用户名称" prop="name">
            <el-input v-if="fromState == 'R'" v-model="basicForm.name" disabled/>
            <el-input v-else v-model="basicForm.name" />
          </el-form-item>
          <el-form-item label="用户邮箱" prop="name">
            <el-input v-if="fromState == 'R'" v-model="basicForm.email" disabled/>
            <el-input v-else v-model="basicForm.email"/>
          </el-form-item>
          <el-form-item label="用户手机" prop="name">
            <el-input v-if="fromState == 'R'" v-model="basicForm.email" disabled/>
            <el-input v-else v-model="basicForm.email"/>
          </el-form-item>
          <el-form-item label="备注">
            <el-input v-if="fromState == 'R'" v-model="basicForm.remark" type="textarea" disabled/>
            <el-input v-else v-model="basicForm.remark" type="textarea"/>
          </el-form-item>
        </el-form>
      </div>
      <div class="footer">
        <el-button v-if="fromState == 'E'" size="medium" round type="info" @click="fromState = 'R'" >取 消</el-button>
        <el-button v-if="fromState == 'R'" size="medium" type="primary" round @click="toUpdate">编 辑</el-button>
        <el-button v-else size="medium" type="primary" round @click="update">保 存</el-button>
      </div>

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
  </div>
</template>

<script>
import myUpload from 'vue-image-crop-upload'
import { getToken } from '@/utils/auth'
import { mapGetters } from 'vuex'
import { get, updateById } from '@/api/index'
import Avatar from 'vue-avatar'
export default {
  components: {
    'my-upload': myUpload,
    Avatar
  },
  data() {
    return {
      moudle: 'users',
      basicForm: this.init(),
      fromState: 'R',
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
    this.getById()
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
    toUpdate() {
      this.fromState = 'E'
    },
    update() {
      this.$refs[this.ruleForm].validate((valid) => {
        if (valid) {
          updateById(this.moudle, this.basicForm).then(() => {
            this.fromState = 'R'
          })
        } else {
          return false
        }
      })
    },
    getById() {
      get(this.moudle, this.userId).then(res => {
        this.basicForm = res
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
