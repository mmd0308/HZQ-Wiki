<template>
  <div class="basicSet">
    <el-row class="row">
      <el-col :span="5">
        <div class="pic">
          <img :src="basicForm.avatar">
        </div>
      </el-col>
      <el-col :span="19">
        <div class="updateAct" @click="toggleShow">
          更改头像
        </div>
      </el-col>
    </el-row>
    <el-row class="row">
      <el-col :span="5">
        昵称
      </el-col>
      <el-col :span="19">
        <el-input v-model="basicForm.nickName" placeholder="请输入内容" style="width:300px;" />
      </el-col>
    </el-row>
    <el-row class="row">
      <el-col :span="5">
        电子邮箱
      </el-col>
      <el-col :span="19">
        <el-input v-model="basicForm.email" placeholder="请输入内容" style="width:300px;" />
      </el-col>
    </el-row>
    <el-row class="row">
      <el-col :span="5">
        手机
      </el-col>
      <el-col :span="19">
        <el-input v-model="basicForm.phone" placeholder="请输入内容" style="width:300px;" />
      </el-col>
    </el-row>
    <el-row class="row">
      <el-button type="success" round size="medium" @click="update">保 存</el-button>
    </el-row>
    <!-- <my-upload
      :width="300"
      :height="300"
      :url="uploadUrl"
      :headers="headers"
      v-model="show"
      field="file"
      img-format="png"
      @crop-success="cropSuccess"
      @crop-upload-success="cropUploadSuccess"
      @crop-upload-fail="cropUploadFail"/> -->
  </div>
</template>

<script>
// import myUpload from 'vue-image-crop-upload'
export default {
  data() {
    return {
      basicForm: {
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
