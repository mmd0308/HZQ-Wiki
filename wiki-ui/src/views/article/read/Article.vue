<template>
  <div class="read-height" >
    <mavon-editor
      ref="mavonEditor"
      :subfield="false"
      :toolbars-flag="false"
      v-model="articleForm.content"
      class="height:100%;"
      default-open="preview"
    />
  </div>
</template>
<script>
import { get } from '@/api/article/article'
export default {
  data() {
    return {
      articleForm: this.init()
    }
  },
  created() {
    this.get()
  },
  methods: {
    init() {
      return {
        id: null,
        title: '',
        content: '',
        contentHtml: '',
        hwState: '1',
        hwUp: '0'
      }
    },
    get() {
      get(this.$route.params.id).then(res => {
        this.articleForm = res.data
      })
    }
  }
}
</script>
<style rel="stylesheet/scss" lang="scss">
</style>

