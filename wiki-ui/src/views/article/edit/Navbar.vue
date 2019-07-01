<template>
  <div>
    <router-link to="/article">
      <el-button icon="el-icon-arrow-left" size="small" plain> 返回</el-button>
    </router-link>
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
    }
  }
}
</script>
