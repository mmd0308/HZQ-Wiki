<template>
  <div class="space">
    <el-form :ref="ruleForm" :model="configForm" :rules="rules" label-width="100px" >
      <el-form-item label="项目标题" prop="title">
        <el-input v-model="configForm.title" style="width:80%"/>
      </el-form-item>
      <el-form-item label="网站备案号" prop="keepToRecord">
        <el-input v-model="configForm.keepToRecord" type="textarea" style="width:80%"/>
      </el-form-item>
    </el-form>
    <p
      style="font-size: 13px;color: gray;padding-left: 13px;">
      注: 修改成功后,刷新页面加载新配置!
    </p>
    <div slot="footer" style="text-align: center;">
      <el-button type="primary" @click="update">保 存</el-button>
    </div>
  </div>
</template>

<script>
import { form, update } from '@/api/config/index'
export default {
  data() {
    return {
      configForm: {
        title: '',
        keeyToRecord: ''
      },
      listQuery: {
        pageNum: 1,
        pageSize: 10,
        value: ''
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
        this.configForm = response.data
      })
    },
    update() {
      update(this.configForm).then(() => {
        this.$notify({
          title: '成功',
          message: '更新成功!',
          type: 'success'
        })
      })
    }
  }
}
</script>
