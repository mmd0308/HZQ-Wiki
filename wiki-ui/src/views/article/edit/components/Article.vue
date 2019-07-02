<template>
  <div class="article-edit hzq-wiki-height" >
    <div class="header">
      <router-link to="/article">
        <i class="el-icon-arrow-left back"/>
      </router-link>
      <input v-model="articleForm.title" placeholder="请输入文章标题" class="title" >
      <ul style="float:right">
        <li>
          <el-button>草稿</el-button>
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
      @imgAdd="imgAdd"
      @save="saveContent"/>
  </div>
</template>
<script>
import { addOrUpdate } from '@/api/article/article'
import axios from 'axios'
import { getToken } from '@/utils/auth'
export default {
  data() {
    return {
      articleForm: this.init()
    }
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
    saveContent(value, render) {
      this.articleForm.content = value
      this.articleForm.contentHtml = render
      this.articleForm.hwState = '1'
      addOrUpdate(this.articleForm).then(res => {
        if (this.articleForm.id == null) { // 表示新增
          this.articleForm.id = res.data
        }
        // 保存成功返回id
        this.$notify({
          title: '成功',
          message: '保存成功!',
          type: 'success'
        })
      })
    },
    updateArticle() {
      this.articleForm.hwState = '2'
      addOrUpdate(this.articleForm).then(res => {
        if (this.articleForm.id == null) { // 表示新增
          this.articleForm.id = res.data
        }
        // 保存成功返回id
        this.$notify({
          title: '成功',
          message: '已经发布!',
          type: 'success'
        })
      })
    },
    imgAdd(pos, $file) {
      this.changeSave = false
      const param = new FormData() // 创建form对象
      param.append('file', $file) // 通过append向form对象添加数据
      param.append('articleId', this.contentForm.id)
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
    }
    li + li {
      margin-left:10px;
    }
  }

}
</style>

