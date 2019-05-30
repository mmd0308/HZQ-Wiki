<template>
  <div class="app-doc">
    <el-container>
      <el-header>
        <navbar :doc-status="docStatus" @editStatus="editStatus"/>
      </el-header>
      <el-main style="padding:0px;  min-height: calc(100vh - 60px)">
        <document-main :doc-status="docStatus" :doc-id="docId" />
      </el-main>
    </el-container>
  </div>
</template>
<script>
import Navbar from './Navbar'
import DocumentMain from './DocMain'
export default {
  name: 'Layout',
  components: {
    Navbar,
    DocumentMain
  },

  data() {
    return {
      docStatus: 'R', // 状态为可读
      docId: this.$route.query.docId
    }
  },
  methods: {
    editStatus(stat) {
      this.docStatus = stat
    }
  }
}

</script>
<style>
.app-doc{
  min-height: 100%
}
</style>
