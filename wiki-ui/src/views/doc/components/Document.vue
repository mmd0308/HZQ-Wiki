<template>
  <div id="main">
    <mavon-editor v-if="docStatus == 'R'" v-model="value" :subfield="false" :toolbars-flag="false" default-open="preview"/>
    <mavon-editor v-else v-model="value1"/>
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
