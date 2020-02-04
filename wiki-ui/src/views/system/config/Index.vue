<template>
  <div class="hzq-admin">
    <div class="basic">
      <div class="form">
        <el-form :ref="ruleForm" :model="configForm" :rules="rules" label-width="100px" >
          <el-form-item label="项目标题" prop="title">
            <el-input v-if="fromState == 'R'" v-model="configForm.title" disabled/>
            <el-input v-else v-model="configForm.title" />
          </el-form-item>
          <el-form-item label="网站备案号" prop="keepToRecord">
            <el-input v-if="fromState == 'R'" v-model="configForm.keepToRecord" type="textarea" disabled/>
            <el-input v-else v-model="configForm.keepToRecord" type="textarea" />
          </el-form-item>
        </el-form>
      </div>
      <p
        style="font-size: 13px;color: gray;padding-left: 13px;">
        注: 修改成功后,刷新页面加载新配置!
      </p>
      <div class="footer">
        <el-button v-if="fromState == 'E'" size="medium" round type="info" @click="fromState = 'R'" >取 消</el-button>
        <el-button v-if="fromState == 'R'" size="medium" type="primary" round @click="fromState = 'E'">编 辑</el-button>
        <el-button v-else size="medium" type="primary" round @click="update">保 存</el-button>
      </div>
    </div>
  </div>
</template>

<script>
import { form, update } from '@/api/config/index'
export default {
  data() {
    return {
      fromState: 'R',
      configForm: {
        title: '',
        keeyToRecord: ''
      },
      ruleForm: 'ruleForm',
      rules: {
        title: [
          { message: '请输入网站标题', trigger: 'blur', required: true }
        ],
        keepToRecord: [
          { required: true, message: '请输入值备案号', trigger: 'blur' }
        ]
      }
    }
  },
  created() {
    this.initForm()
  },
  methods: {
    initForm() {
      form().then(response => {
        this.configForm = response
      })
    },
    update() {
      update(this.configForm).then(() => {
        this.fromState = 'R'
      })
    }
  }
}
</script>
