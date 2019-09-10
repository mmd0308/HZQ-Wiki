<template>
  <div class="read-height">
    <mavon-editor ref="mavonEditor" :subfield="false" :toolbars-flag="false" v-model="articleForm.content" :external-link="externalLink" class="height:100%;" default-open="preview" />
  </div>
</template>
<script>
import { get } from '@/api/article/article'
export default {
  data() {
    return {
      articleForm: this.init(),
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
