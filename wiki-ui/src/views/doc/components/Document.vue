<template>
  <div class="doc-edit hzq-wiki-height">
    <mavon-editor v-if="docStatus == 'R'" v-model="value" :subfield="false" :toolbars-flag="false" class="hzq-wiki-height" default-open="preview"/>
    <mavon-editor v-else v-model="value1" class="hzq-wiki-height"/>
  </div>
</template>
<script>
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
      value1: 'E'
    }
  }
}
</script>
<style>
.doc-edit{

}
</style>
