<template>
  <div class="doc-edit hzq-wiki-height">
    <mavon-editor v-if="docStatus == 'R'" v-model="contentForm.content" :subfield="false" :scroll-style="true" :toolbars-flag="false" :external-link="externalLink" class="hzq-wiki-height" default-open="preview" />
    <mavon-editor v-else ref="mavonEditor" :scroll-style="true" v-model="contentForm.content" class="hzq-wiki-height" @imgAdd="imgAdd" @change="contentChange" @save="saveContent" />
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
      contentForm: this.init(),
      timeOut: null,
      saveState: '已保存',
      externalLink: {
        markdown_css: function() {
          // 这是你的markdown css文件路径
          return '/static/markdown/github-markdown.min.css'
        },
        hljs_js: function() {
          // 这是你的hljs文件路径
          return '/static/highlightjs/highlight.min.js'
        },
        hljs_css: function(css) {
          // 这是你的代码高亮配色文件路径
          return '/static/highlightjs/styles/' + css + '.min.css'
        },
        hljs_lang: function(lang) {
          // 这是你的代码高亮语言解析路径
          return '/static/highlightjs/languages/' + lang + '.min.js'
        },
        katex_css: function() {
          // 这是你的katex配色方案路径路径
          return '/static/katex/katex.min.css'
        },
        katex_js: function() {
          // 这是你的katex.js路径
          return '/static/katex/katex.min.js'
        }
      }
    }
  },
  watch: {
    saveState: {
      handler: function(val) {
        bus.$emit('editDocSaveState', val)
      },
      deep: true
    }
  },
  mounted() {
    const self = this
    bus.$on('clickContentTree', function(treeNode) {
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
    contentChange(value, render) {
      this.saveState = '保存中...'
      if (this.timeOut != null) {
        clearTimeout(this.timeOut)
      }
      this.contentForm.content = value
      this.contentForm.contentHtml = render
      var that = this
      this.timeOut = setTimeout(function() {
        that.addOrUpdate()
      }, 500)
    },
    saveContent(value, render) {
      this.contentForm.content = value
      this.contentForm.contentHtml = render
      this.addOrUpdate()
    },
    addOrUpdate() {
      addOrUpdate(this.contentForm).then(() => {
        this.saveState = '保存成功'
      })
    },
    imgAdd(pos, $file) {
      this.changeSave = false
      const param = new FormData() // 创建form对象
      param.append('file', $file) // 通过append向form对象添加数据
      param.append('docId', this.contentForm.docId)
      const config = {
        headers: {
          'Content-Type': 'multipart/form-data',
          Authorization: getToken()
        }
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
