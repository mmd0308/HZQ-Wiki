<template>
  <div>
    <h3 style="margin:0px; position:absolute; left:20px;">
      {{ docName }}
    </h3>
    <div style="position:absolute;right:20px;">
      <el-button size="small" type="primary">
        <router-link to="/dashboard">
          首页
        </router-link>
      </el-button>
      <!-- 可读模式或者浏览模式,允许编辑 -->
      <el-button v-if="docStatus == 'R' && docPrivilege != '1'" size="small" type="success" @click="toEdit">
        编辑
      </el-button>
    </div>
  </div>
</template>
<script>
export default {
  props: {
    docStatus: {
      type: String,
      required: true
    },
    docName: {
      type: String,
      required: true
    },
    docPrivilege: {
      type: Number,
      required: true
    }
  },
  methods: {
    toEdit() {
      this.docStatus = 'E'
      this.$emit('editStatus', this.docStatus)
    },
    save() {
      alert('k')
    }
  }
}
</script>
