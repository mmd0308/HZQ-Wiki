<template>
  <div class="index-container">
    <h1>代码在疯狂的更新中...</h1>
    <doc-index/>
    <article-index/>
  </div>
</template>
<script>
import DocIndex from './components/DocIndex'
import ArticleIndex from './components/ArticleIndex'
export default {
  name: 'Index',
  components: {
    DocIndex,
    ArticleIndex
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.index {
  &-container {
    padding: 1px 00px 40px 00px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
</style>
