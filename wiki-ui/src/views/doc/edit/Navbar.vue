<template>
  <div>
    <h3 style="margin:0px; position:absolute; left:20px;">
      {{ docName }}
    </h3>
    <div style="position:absolute;right:20px;">
      <el-button size="small" type="primary">
        <router-link to="/">
          首页
        </router-link>
      </el-button>
      <el-button v-if="docStatus == 'R'" size="small" type="success" @click="toEdit">
        编辑
      </el-button>
      <!-- <el-button size="small" type="danger">删除</el-button> -->
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
