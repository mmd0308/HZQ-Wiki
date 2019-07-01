<template>
  <div class="document-main" style=" min-height: calc(100vh - 60px)">
    <el-container style=" min-height: calc(100vh - 60px)">
      <el-main style="padding:0px 0 0 1px">
        <document :doc-status="docStatus"/>
      </el-main>
    </el-container>
  </div>
</template>
<script>
import { Document } from './components'
export default {
  name: 'Layout',
  components: {
    Document
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
