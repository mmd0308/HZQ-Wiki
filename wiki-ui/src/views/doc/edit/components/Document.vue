<template>
  <div class="doc-edit hzq-wiki-height">
    <mavon-editor
      v-if="docStatus == 'R'"
      v-model="contentForm.content"
      :subfield="false"
      :toolbars-flag="false"
      class="hzq-wiki-height"
      default-open="preview"/>
    <mavon-editor
      v-else
      v-model="contentForm.content"
      class="hzq-wiki-height"
      @save="saveContent"/>
  </div>
</template>
<script>
import { addOrUpdate } from '@/api/doc/content'
import bus from '@/assets/js/eventbus'
export default {
  props: {
    docStatus: {
      type: String,
      required: true
    }
  },
  data() {
    return {
      contentForm: this.init()
    }
  },
  mounted() {
    const self = this
    bus.$on('clickContentTree', function(treeNode) {
      self.contentForm.id = treeNode.id
      self.contentForm.content = treeNode.content
      self.contentForm.contentHtml = treeNode.contentHtml
    })
  },
  methods: {
    init() {
      return {
        id: '',
        content: '',
        contentHtml: ''
      }
    },
    saveContent(value, render) {
      this.contentForm.content = value
      this.contentForm.contentHtml = render
      addOrUpdate(this.contentForm).then(() => {
        this.$notify({
          title: '成功',
          message: '保存成功!',
          type: 'success'
        })
      })
    }
  }
}
</script>
<style>
.doc-edit{

}
</style>
