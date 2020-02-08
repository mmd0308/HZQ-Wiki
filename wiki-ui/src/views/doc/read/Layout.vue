<template>
  <div class="app-doc">
    <el-container>
      <el-header>
        <navbar/>
      </el-header>
      <el-main style="padding:0px;  min-height: calc(100vh - 60px)">
        <document-main />
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
    }
  },
  methods: {
  }
}
</script>
<style>
.app-doc{
  min-height: 100%
}
</style>
