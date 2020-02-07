<template>
  <div class="article-edit hzq-wiki-height">
    <div class="header">
      <router-link to="/article">
        <i class="el-icon-arrow-left back" />
      </router-link>
      <input v-model="title" placeholder="请输入文章标题" class="title">
      <ul style="float:right">
        <li>
          {{ saveState }}
        </li>
        <li>
          <router-link to="/admin/article">
            <el-button size="small">草稿</el-button>
          </router-link>
        </li>
        <li>
          <el-button size="small" type="primary" @click="preRelease">发布</el-button>
        </li>
      </ul>
    </div>
    <mavon-editor
      ref="mavonEditor"
      :scroll-style="true"
      v-model="articleForm.content"
      code-style="dark"
      class="hzq-wiki-height"
      @change="contentChange"
      @imgAdd="imgAdd"
      @save="saveContent" />

    <el-drawer
      :visible.sync="drawer"
      :with-header="false"
      size="25%"
      class="hzq-drawer">
      <h4 class="header">文章发布</h4>
      <el-divider/>
      <div class="content">
        <el-form :model="articleRelease">
          <el-divider content-position="left">标签</el-divider>
          <!-- 暂时禁止创建标签 TODO -->
          <el-select
            v-model="articleRelease.tagIds"
            style="width:100%"
            multiple
            filterable
            default-first-option
            placeholder="选择或输入标签"
            @change="createTag">
            <el-option
              v-for="item in tagLists"
              :key="item.id"
              :label="item.name"
              :value="item.id"/>
          </el-select>
          <!-- allow-create_-->
          <el-divider content-position="left">摘要</el-divider>
          <el-input
            v-model="articleRelease.hwDesc"
            :autosize="{ minRows: 4}"
            type="textarea"
            placeholder="不填写则会自动生成摘要"
            maxlength="150"
            show-word-limit
          />

        </el-form>
        <!-- <div class="article-drawer__footer">
          <el-button size="medium" plain type="info" @click="cancelForm">保存草稿</el-button>
          <el-button :loading="loading" plain size="medium" type="primary" @click="release">{{ loading ? '提交中 ...' : '发  布' }}</el-button>
        </div> -->
        <div class="footer">
          <el-button size="medium" type="info" @click="drawer = false">取消</el-button>
          <el-button :loading="buttonLoading" size="medium" type="primary" @click="release">{{ buttonLoading ? '提交中 ...' : '发  布' }}</el-button>
        </div>
      </div>
    </el-drawer>
  </div>
</template>
<script>
import { release } from '@/api/article/article'
import { getById, create, updateById } from '@/api/index'
import axios from 'axios'
import { getToken } from '@/utils/auth'
import { showAll } from '@/api/article/tag'
import { imageUploadUrl } from '@/api/system/ImageConstants'
export default {
  data() {
    return {
      moudle: 'articles',
      drawer: false,
      saveState: '尚未保存',
      articleForm: this.init(),
      timeOut: null,
      title: '',
      tagLists: [], // 所有的标签,
      articleRelease: {
        hwDesc: '',
        id: null,
        tagIds: []
      },
      buttonLoading: false
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
          that.createOrUpdate()
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
        hwUp: '0',
        hwDesc: '',
        tagIds: []
      }
    },
    get() {
      getById(this.moudle, this.$route.params.id).then(res => {
        this.articleForm = res
        this.title = this.articleForm.title
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
        that.createOrUpdate()
      }, 1000)
    },
    saveContent(value, render) {
      this.articleForm.content = value
      this.articleForm.contentHtml = render
      this.articleForm.hwState = '1'
      if (this.rulesForm()) {
        this.createOrUpdate()
      }
    },
    createOrUpdate() {
      // 判断是新建还是更新
      if (this.articleForm.id === '') {
        this.create()
      } else {
        this.updateById()
      }
    },
    createTag(item) {
    },
    // 预发布
    preRelease() {
      if (this.rulesForm()) {
        this.drawer = true
        this.articleRelease.id = this.articleForm.id
        // 获取所有的标签
        this.showTagsAll()
      }
    },
    // 发布文章
    release() {
      this.buttonLoading = true
      release(this.articleRelease).then(() => {
        // 跳转到阅读页面
        this.$router.push({ path: '/read/article/' + this.articleRelease.id })
      })
    },
    create() {
      create(this.moudle, this.articleForm).then(id => {
        this.articleForm.id = id
        this.saveState = '保存完成'
      })
    },
    updateById() {
      updateById(this.moudle, this.articleForm).then(() => {
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
      param.append('basePath', 'article/' + this.articleForm.id)
      const config = {
        headers: {
          'Content-Type': 'multipart/form-data',
          Authorization: getToken()
        }
      }
      var that = this
      // 添加请求头
      axios.post(imageUploadUrl, param, config).then(response => {
        that.$refs.mavonEditor.$img2Url(pos, response.data.data)
        that.$refs.mavonEditor.$refs.toolbar_left.$imgDelByFilename(pos)
      })
    },
    showTagsAll() {
      showAll().then(res => {
        this.tagLists = res
      })
    }
  }
}
</script>
<style rel="stylesheet/scss" lang="scss">
.article-edit {
  background: #fff;
  .header {
    .back {
      line-height: 3;
      font-weight: bold;
      padding: 0 10px 0 20px;
    }
    .title {
      border: none;
      height: 50px;
      font-size: 20px;
      width: 60%;
      outline: none;
    }
    ul {
      list-style: none; /* 将默认的列表符号去掉 */
      padding: 0; /* 将默认的内边距去掉 */
      margin: 0; /* 将默认的外边距去掉 */
      margin-top: 5px;
      margin-right: 20px;
    }
    ul li {
      float: left;
      line-height: 40px;
    }
    li + li {
      margin-left: 10px;
    }
  }
}
</style>

