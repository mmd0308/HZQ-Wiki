<template>
  <div class="app-doc">
    <el-container>
      <el-header>
        <navbar/>
      </el-header>
      <el-main>
        <document-main/>
      </el-main>
    </el-container>
  </div>
</template>
<script>
import Navbar from './Navbar'
import DocumentMain from './DocumentMain'
export default {
  name: 'Layout',
  components: {
    Navbar,
    DocumentMain
  }
}
</script>
