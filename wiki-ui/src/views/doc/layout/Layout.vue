<template>
  <div class="app-doc">
    <el-container>
      <el-header>
        <navbar :doc-status="docStatus" @editStatus="editStatus"/>
      </el-header>
      <el-main style="padding:0px;">
        <document-main :doc-status="docStatus" />
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
      docStatus: 'R' // 状态为可读
    }
  },
  methods: {
    editStatus(stat) {
      this.docStatus = stat
    }
  }
}

</script>
