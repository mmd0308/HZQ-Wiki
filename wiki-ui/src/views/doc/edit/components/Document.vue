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
      ref="mavonEditor"
      v-model="contentForm.content"
      class="hzq-wiki-height"
      @imgAdd="imgAdd"
      @save="saveContent"/>
  </div>
</template>
<script>
import { addOrUpdate, get } from '@/api/doc/content'
import bus from '@/assets/js/eventbus'
import axios from 'axios'
import { getToken } from '@/utils/auth'
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
      self.contentForm.docId = treeNode.docId
      self.contentForm.id = treeNode.id
      // self.contentForm.content = treeNode.content
      // self.contentForm.contentHtml = treeNode.contentHtml
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
      get(id).then(res => {
        this.contentForm = res.data
      })
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
    },
    imgAdd(pos, $file) {
      this.changeSave = false
      const param = new FormData() // 创建form对象
      param.append('file', $file) // 通过append向form对象添加数据
      param.append('docId', this.contentForm.docId)
      const config = {
        headers: { 'Content-Type': 'multipart/form-data', 'Authorization': getToken() }
      }
      var that = this
      // 添加请求头
      axios.post('/api/doc/uploadImages', param, config).then(response => {
        that.$refs.mavonEditor.$img2Url(pos, response.data.data)
        that.$refs.mavonEditor.$refs.toolbar_left.$imgDelByFilename(pos)
      })
    }
  }
}
</script>
<style>
.doc-edit{
  position: absolute;
  overflow: auto;
  top: 60px;
  bottom: 0px;
  right: 0px;
  left: 280px;
}
</style>

