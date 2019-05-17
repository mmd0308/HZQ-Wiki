<template>
  <div class="document-main">
    <el-container>
      <el-aside width="280px">
        <document-tree/>
      </el-aside>
      <el-main>
        <document/>
      </el-main>
    </el-container>
  </div>
</template>
<script>
import { Document, DocumentTree } from '../components'
export default {
  name: 'Layout',
  components: {
    Document,
    DocumentTree
  }
}
</script>
