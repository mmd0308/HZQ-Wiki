<template>
  <div class="login-container">
    <img
      src="@/assets/login.jpg"
      style="width: 100%;height: 100%;position: absolute;z-index: -10;" >
    <div class="login-register">
      <h3 class="title">
        <div :class="{'check-item':formStates === 'login','title-item':true}" @click="checkForm('login')">登录</div>
        <b>|</b>
        <div :class="{'check-item':formStates === 'register','title-item':true}" @click="checkForm('register')"> 注册</div>
      </h3>
      <!-- 登录 -->
      <el-form
        v-if="formStates === 'login'"
        ref="loginForm"
        :model="loginForm"
        :rules="loginRules"
        auto-complete="on"
        label-position="left"
        label-width="0px"
        class="card-box login-form">
        <el-form-item prop="username">
          <span class="svg-container svg-container_login">
            <svg-icon icon-class="user" />
          </span>
          <el-input v-model="loginForm.username" name="username" type="text" auto-complete="on" placeholder="请输入账号" />
        </el-form-item>
        <el-form-item prop="password">
          <span class="svg-container">
            <svg-icon icon-class="password"/>
          </span>
          <el-input
            :type="pwdType"
            v-model="loginForm.password"
            name="password"
            auto-complete="on"
            placeholder="密码"
            @keyup.enter.native="handleLogin"/>
          <span class="show-pwd" @click="showPwd">
            <svg-icon v-if="pwdType === 'password'" icon-class="eye" />
            <svg-icon v-else icon-class="eye-open" />
          </span>
        </el-form-item>
        <div class="tips">
          <span style="margin-right:20px;">用户名: admin</span>
          <span> 密码: hzqing.com</span>
        </div>
        <el-button :loading="loading" type="primary" style="width:100%; border-radius: 25px;" @click.native.prevent="handleLogin">
          登 录
        </el-button>
      </el-form>
      <!-- 注册 -->
      <el-form
        v-if="formStates === 'register'"
        ref="regForm"
        :model="regForm"
        :rules="regRules"
        auto-complete="on"
        label-position="left"
        label-width="0px"
        class="card-box login-form">

        <el-form-item prop="username">
          <span class="svg-container svg-container_login">
            <svg-icon icon-class="user" />
          </span>
          <el-input v-model="regForm.username" type="text" auto-complete="on" placeholder="登陆账号" />
        </el-form-item>

        <el-form-item prop="phone">
          <span class="svg-container svg-container_login">
            <svg-icon icon-class="phone" />
          </span>
          <el-input v-model="regForm.phone" type="text" auto-complete="on" placeholder="手机号" />
        </el-form-item>

        <el-form-item prop="email">
          <span class="svg-container svg-container_login">
            <svg-icon icon-class="reg-email" />
          </span>
          <el-input v-model="regForm.email" type="text" auto-complete="on" placeholder="邮箱" />
        </el-form-item>

        <el-form-item prop="password">
          <span class="svg-container">
            <svg-icon icon-class="password"/>
          </span>
          <el-input
            :type="pwdType"
            v-model="regForm.password"
            name="password"
            auto-complete="on"
            placeholder="密码"
            @keyup.enter.native="handleRegister"/>
          <span class="show-pwd" @click="showPwd">
            <svg-icon v-if="pwdType === 'password'" icon-class="eye" />
            <svg-icon v-else icon-class="eye-open" />
          </span>
        </el-form-item>
        <el-button :loading="loading" type="primary" style="width:100%; border-radius: 25px;" @click.native.prevent="handleRegister">
          注 册
        </el-button>
      </el-form>
    </div>
  </div>
</template>

<script>
import { registerUser } from '@/api/system/user/index'
export default {
  name: 'Login',
  data() {
    const validatePass = (rule, value, callback) => {
      if (value.length < 5) {
        callback(new Error('密码不能小于5位'))
      } else {
        callback()
      }
    }
    return {
      loginForm: {
        username: 'admin',
        password: 'admin'
      },
      regForm: this.initReg(),
      loginRules: {
        username: [{ required: true, trigger: 'blur', message: '请输入账号' }],
        password: [{ required: true, trigger: 'blur', validator: validatePass }]
      },
      regRules: {
        username: [{ required: true, trigger: 'blur', message: '请输入账号' }],
        password: [{ required: true, trigger: 'blur', validator: validatePass }],
        email: [
          { required: true, message: '请输入邮箱地址', trigger: 'blur' },
          { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
        ],
        phone: [{ required: true, message: '联系电话不能为空' }]
      },
      loading: false,
      pwdType: 'password',
      formStates: 'login'
    }
  },
  methods: {
    initReg() {
      return {
        name: '',
        username: '',
        phone: '',
        password: '',
        email: ''
      }
    },
    showPwd() {
      if (this.pwdType === 'password') {
        this.pwdType = ''
      } else {
        this.pwdType = 'password'
      }
    },
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true
          this.$store.dispatch('Login', this.loginForm).then(() => {
            this.loading = false
            // 判断，根据用户不同，返回不同的界面
            this.$router.push({ path: this.redirect || '/' })
          }).catch(() => {
            this.loading = false
          })
        } else {
          console.log('登录校验错误')
          return false
        }
      })
    },
    handleRegister() {
      this.$refs.regForm.validate(valid => {
        if (valid) {
          this.loading = true
          this.regForm.name = this.regForm.username
          registerUser(this.regForm).then(() => {
            this.$notify({
              title: '成功',
              message: '恭喜你！你已注册成功，请登录！',
              type: 'success',
              duration: 2000
            })
            this.loading = false
            this.formStates = 'login'
            this.regForm = this.initReg()
          })
        } else {
          console.log('注册校验错误')
          return false
        }
      })
    },
    checkForm(item) {
      this.formStates = item
    }
  }
}
</script>
<style rel="stylesheet/scss" lang="scss">
  $bg:#2d3a4b;
  $dark_gray:#889aa4;
  $light_gray:#969696;

  .login-container {
    position: fixed;
    height: 100%;
    width:100%;
    background-color: #f1f1f1;
   // background: url(/static/login.jpg) no-repeat 0 0;
    input:-webkit-autofill {
      -webkit-box-shadow: 0 0 0px 1000px #293444 inset !important;
      -webkit-text-fill-color: #fff !important;
    }
    input {
      background: transparent;
      border: 0px;
      -webkit-appearance: none;
      border-radius: 0px;
      padding: 12px 5px 12px 15px;
      color: $light_gray;
      height: 47px;
    }
    .el-input {
      display: inline-block;
      height: 47px;
      width: 85%;
    }
    .tips {
      font-size: 14px;
      color: $light_gray;
      margin: 20px 0px;
      text-align: center;
    }
    .svg-container {
      padding: 6px 5px 6px 15px;
      color: $dark_gray;
      vertical-align: middle;
      width: 30px;
      display: inline-block;
      &_login {
        font-size: 20px;
      }
    }
    .title {
      font-size: 18px;
      color: $light_gray;
      margin: auto;
      text-align: center;
      font-weight: 400;
      padding: 30px 0px 0px;
      .title-item {
        padding: 0px 0px  5px;
        margin: 0px 15px;
        display: inline;
      }
      .title-item:hover{
        border-bottom: 2px solid #e05143;
      }
      .check-item{
        color: #e05143;
        border-bottom: 2px solid #e05143;
      }
    }
    .login-register{
      width: 400px;
      margin: 150px auto;
      background: #fff;
    }
    .login-form {
      padding: 40px 35px 35px 35px;
      border-radius: 4px;
    }
    .el-form-item {
      border: 1px solid rgba(255, 255, 255, 0.1);
      background: rgba(0, 0, 0, 0.1);
      border-radius: 5px;
      color: #454545;
    }
    .show-pwd {
      position: absolute;
      right: 10px;
      top: 7px;
      font-size: 16px;
      color: $dark_gray;
      cursor: pointer;
      user-select:none;
    }
    .thirdparty-button{
      position: absolute;
      right: 35px;
      bottom: 28px;
    }
    .login-icon{
      width: 32px !important;
      height: 32px !important;
      margin: 0 15px;
    }
  }
</style>
