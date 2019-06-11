<template>
  <div class="basicSet">
    <el-form :ref="ruleForm" :model="userForm" :rules="rules" label-width="100px" >
      <el-form-item label="旧密码" prop="oldPassword">
        <el-input v-model="userForm.oldPassword" type="password" style="width:80%" />
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="userForm.password" type="password" style="width:80%"/>
      </el-form-item>
      <el-form-item label="确认密码" prop="checkPassword">
        <el-input v-model="userForm.checkPassword" type="password" style="width:80%"/>
      </el-form-item>
    </el-form>

    <el-row class="row">
      <el-button type="success" round size="medium" @click="update">保 存</el-button>
    </el-row>
  </div>
</template>

<script>
import { addOrUpdate, checkPass } from '@/api/system/user/index'
import { mapGetters } from 'vuex'
export default {
  data() {
    var validateOldPassword = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else {
        this.paramsForm.id = this.userId
        this.paramsForm.password = this.userForm.oldPassword
        checkPass(this.paramsForm).then(response => {
          if (response.data === false) {
            callback(new Error('旧密码错误,请重新输入!'))
          } else {
            callback()
          }
        })
      }
    }
    var validatePassword = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else {
        if (this.userForm.checkPassword !== '') {
          this.$refs.ruleForm.validateField('checkPassword')
        }
        callback()
      }
    }
    var validateCheckPassword = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.userForm.password) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
    return {
      userForm: this.init(),
      ruleForm: 'ruleForm',
      paramsForm: {
        id: '',
        password: ''
      },
      rules: {
        oldPassword: [
          { validator: validateOldPassword, trigger: 'blur', required: true }
        ],
        password: [
          { validator: validatePassword, trigger: 'blur', required: true }
        ],
        checkPassword: [
          { validator: validateCheckPassword, trigger: 'blur', required: true }
        ]
      }
    }
  },
  computed: {
    ...mapGetters([
      'userId'
    ])
  },
  methods: {
    init() {
      return {
        id: '',
        password: '',
        oldPassword: '',
        checkPassword: ''
      }
    },
    update() {
      this.$refs[this.ruleForm].validate((valid) => {
        if (valid) {
          addOrUpdate(this.userForm).then(() => {
            this.$notify({
              title: '成功',
              message: '用户密码修改成功!',
              type: 'success'
            })
            this.userForm = this.init()
          })
        } else {
          return false
        }
      })
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
    }
}
</style>
