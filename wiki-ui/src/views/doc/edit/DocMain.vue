<template>
  <div class="document-main" style=" min-height: calc(100vh - 60px)">
    <el-container style=" min-height: calc(100vh - 60px)">
      <el-aside width="280px">
        <document-tree :doc-status="docStatus" :doc-id="docId"/>
      </el-aside>
      <el-main style="padding:0px 0 0 1px">
        <document :doc-status="docStatus"/>
      </el-main>
    </el-container>
  </div>
</template>
<script>
import { Document, DocumentTree } from './components'
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
    },
    docId: {
      type: Number,
      required: true
    }
  }
}
</script>
