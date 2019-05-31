<template>
  <div class="doc-edit hzq-wiki-height">
    <mavon-editor v-if="docStatus == 'R'" v-model="value" :subfield="false" :toolbars-flag="false" class="hzq-wiki-height" default-open="preview"/>
    <mavon-editor
      v-else
      v-model="value1"
      class="hzq-wiki-height"
      @save="saveContent"/>
  </div>
</template>
<script>
import { page, addOrUpdate, deletedById } from '@/api/doc/content'
export default {
  props: {
    docStatus: {
      type: String,
      required: true
    }
  },
  data() {
    return {
      value: 'R',
      value1: 'E',
      contentForm: this.init()
    }
  },
  methods: {
    init() {
      return {
        docId: '',
        cataId: '',
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
