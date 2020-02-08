<template>
  <div class="doc-edit hzq-wiki-height">
    <mavon-editor
      v-model="contentForm.content"
      :subfield="false"
      :scroll-style="true"
      :toolbars-flag="false"
      code-style="dark"
      class="hzq-wiki-height"
      default-open="preview" />
  </div>
</template>
<script>
import { getById } from '@/api/index'
import bus from '@/assets/js/eventbus'
export default {
  data() {
    return {
      moudle: 'contents',
      contentForm: this.init()
    }
  },
  mounted() {
    const self = this
    bus.$on('clickReadContentTree', function(treeNode) {
      self.get(treeNode.id)
    })
  },
  methods: {
    init() {
      return {
        id: '',
        docId: '',
        content: '',
        contentHtml: ''
      }
    },
    get(id) {
      getById('show/' + this.moudle, id).then(res => {
        this.contentForm = res
      })
    }
  }
}
</script>
