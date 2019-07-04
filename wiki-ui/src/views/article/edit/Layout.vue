<template>
  <div class="app-doc">
    <el-container>
      <el-main style="padding:0px;  min-height: calc(100vh - 60px)">
        <hw-article />
      </el-main>
    </el-container>
  </div>
</template>
<script>
import { Article } from './components'
export default {
  name: 'Layout',
  components: {
    HwArticle: Article
  }
}
</script>
<style>
.app-doc{
  min-height: 100%
}
</style>
