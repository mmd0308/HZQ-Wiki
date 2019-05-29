<template>
  <div class="document-main">
    <el-container>
      <el-aside width="280px">
        <document-tree :doc-status="docStatus"/>
      </el-aside>
      <el-main style="padding:0px 0 0 1px">
        <document :doc-status="docStatus"/>
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
  },
  props: {
    docStatus: {
      type: String,
      required: true
    }
  }
}
</script>
