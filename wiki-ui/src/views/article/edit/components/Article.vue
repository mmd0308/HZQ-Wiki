<template>
  <div class="article-edit hzq-wiki-height" >
    <div class="header">
      <router-link to="/article">
        <i class="el-icon-arrow-left back"/>
      </router-link>
      <input v-model="title" placeholder="请输入文章标题" class="title" >
      <ul style="float:right">
        <li>
          {{ saveState }}
        </li>
        <li>
          <router-link to="/set/article">
            <el-button>草稿</el-button>
          </router-link>
        </li>
        <li>
          <el-button type="primary" @click="updateArticle">发布文章</el-button>
        </li>
      </ul>
    </div>
    <mavon-editor
      ref="mavonEditor"
      :scroll-style="true"
      v-model="articleForm.content"
      class="hzq-wiki-height"
      @change="contentChange"
      @imgAdd="imgAdd"
      @save="saveContent"/>
  </div>
</template>
<script>
import { addOrUpdate, get } from '@/api/article/article'
import axios from 'axios'
import { getToken } from '@/utils/auth'
export default {
  data() {
    return {
      saveState: '尚未保存',
      articleForm: this.init(),
      timeOut: null,
      title: ''
    }
  },
  watch: {
    title: {
      handler: function(val) {
        this.saveState = '保存中...'
        if (this.timeOut != null) {
          clearTimeout(this.timeOut)
        }
        this.articleForm.title = this.title
        var that = this
        this.timeOut = setTimeout(function() {
          that.addOrUpdate()
        }, 1000)
      },
      deep: true
    }
  },
  created() {
    if (this.$route.params.id != null && this.$route.params.id !== '') {
      this.get()
    }
  },
  methods: {
    init() {
      return {
        id: '',
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
    },
    contentChange(value, render) {
      this.saveState = '保存中...'
      if (this.timeOut != null) {
        clearTimeout(this.timeOut)
      }
      this.articleForm.title = this.title
      this.articleForm.content = value
      this.articleForm.contentHtml = render
      this.articleForm.hwState = '1'
      var that = this
      this.timeOut = setTimeout(function() {
        that.addOrUpdate()
      }, 1000)
    },
    saveContent(value, render) {
      this.articleForm.content = value
      this.articleForm.contentHtml = render
      this.articleForm.hwState = '1'
      if (this.rulesForm()) {
        this.addOrUpdate()
      }
    },
    // 发布文章
    updateArticle() {
      if (this.rulesForm()) {
        this.articleForm.hwState = '2'
        addOrUpdate(this.articleForm).then(res => {
          this.$router.push({ path: '/read/article/' + this.articleForm.id })
        })
      }
    },
    addOrUpdate() {
      addOrUpdate(this.articleForm).then(res => {
        // 保存成功返回id
        if (this.articleForm.id === '') { // 表示新增
          this.articleForm.id = res.data
        }
        this.saveState = '保存完成'
      })
    },
    rulesForm() {
      if (this.articleForm.content === '' && this.articleForm.title === '') {
        this.$notify({
          title: '失败',
          message: '内容和标题不能同时为空!',
          type: 'error',
          duration: 2000
        })
        return false
      } else {
        return true
      }
    },
    imgAdd(pos, $file) {
      this.changeSave = false
      const param = new FormData() // 创建form对象
      param.append('file', $file) // 通过append向form对象添加数据
      param.append('articleId', this.articleForm.id)
      const config = {
        headers: { 'Content-Type': 'multipart/form-data', 'Authorization': getToken() }
      }
      var that = this
      // 添加请求头
      axios.post('/api/article/uploadImages', param, config).then(response => {
        that.$refs.mavonEditor.$img2Url(pos, response.data.data)
        that.$refs.mavonEditor.$refs.toolbar_left.$imgDelByFilename(pos)
      })
    }
  }
}
</script>
<style rel="stylesheet/scss" lang="scss">
.article-edit {
  background: #fff;
  .header{
    .back{
      line-height: 3;
      font-weight: bold;
      padding: 0 10px 0 20px
    }
    .title{
      border:none;
      height:50px;
      font-size:20px;
      width: 60%;
      outline: none;
    }
    ul {
      list-style:none; /* 将默认的列表符号去掉 */
      padding:0; /* 将默认的内边距去掉 */
      margin:0; /* 将默认的外边距去掉 */
      margin-top:5px;
      margin-right: 20px;
    }
    ul li {
      float: left;
      line-height: 40px;
    }
    li + li {
      margin-left:10px;
    }
  }

}
</style>

