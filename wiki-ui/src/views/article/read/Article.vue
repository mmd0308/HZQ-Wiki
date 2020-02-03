<template>
  <div style="background:#fff">
    <div class="header" style="padding:20px 24px 0px ">
      <h1 style="font-size: 30px;font-weight: 700;word-break: break-word;">{{ articleForm.title }}</h1>
      <div class="article_auth" style="position: relative;">
        <span>
          作者: {{ articleForm.createName }}
        </span>
        <span>
          创建时间: {{ articleForm.createTime }}
        </span>
        <div v-if="checkPermission(articleForm.userId)" style="position: absolute;right: 0px;top: -8px;" >
          <router-link :to="'/write/article/'+articleForm.id">
            <el-button type="text" size="small">编辑</el-button>
          </router-link>
        </div>
      </div>
    </div>

    <mavon-editor
      ref="mavonEditor"
      :subfield="false"
      :toolbars-flag="false"
      :code-style="code_style"
      v-model="articleForm.content"
      class="height:100%;"
      default-open="preview" />

    <div class="hzq-right-bottom-toolbar">
      <!-- 回到顶端 -->
      <div
        v-if="backtop"
        style="border-radius: 50%; background: #ffffff; width: 40px; height: 40px; text-align: center; color: #8c8c8c;"
        class="option-box"
        @click="backTop">
        <i style="line-height:40px" class="el-icon-caret-top" />
      </div>
    </div>

    <div ref="toolBox" class="tool-box">
      <ul class="meau-list">
        <li>
          <div class="btn-like mp" >
            <svg-icon class="detail-icon" icon-class="star"/>
          </div>
        </li>
        <!-- <li>
          <div class="btn-like mp" >
            <svg-icon class="detail-icon" icon-class="star"/>
          </div>
        </li> -->
      </ul>
    </div>
  </div>
</template>

<script>
import { showGetById } from '@/api/article/article'
import { getToken } from '@/utils/auth'
import { mapGetters } from 'vuex'
export default {

  data() {
    return {
      moudle: 'articles',
      backtop: false,
      articleForm: this.init(),
      code_style: 'dark'
    }
  },
  computed: {
    ...mapGetters([
      'userId',
      'token'
    ])
  },
  created() {
    this.get()
  },
  mounted() {
    window.addEventListener('scroll', this.scrollToTop)
  },
  destroyed() {
    window.removeEventListener('scroll', this.scrollToTop)
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
      showGetById(this.$route.params.id).then(res => {
        this.articleForm = res
      })
    },
    // 判断是否显示编辑按钮
    checkPermission(id) {
      if (getToken() == null || this.token == null || this.token === '' || this.userId === '' || this.userId == null) { return false }
      if (this.userId === id) {
        return true
      }
      return false
    },
    // 点击图片回到顶部方法，加计时器是为了过渡顺滑
    backTop() {
      const that = this
      const timer = setInterval(() => {
        const ispeed = Math.floor(-that.scrollTop / 5)
        document.documentElement.scrollTop = document.body.scrollTop =
          that.scrollTop + ispeed
        if (that.scrollTop === 0) {
          clearInterval(timer)
        }
      }, 16)
    },
    // 为了计算距离顶部的高度，当高度大于60显示回顶部图标，小于60则隐藏
    scrollToTop() {
      const that = this
      const scrollTop =
        window.pageYOffset ||
        document.documentElement.scrollTop ||
        document.body.scrollTop
      that.scrollTop = scrollTop
      if (that.scrollTop > 60) {
        that.backtop = true
      } else {
        that.backtop = false
      }
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss">
.hzq-right-bottom-toolbar {
  position: fixed;
  width: 40px;
  z-index: 99999;
  bottom: 150px;
  right: calc((100vw - 1050px)/2 - 40px);
}

.tool-box{
  position: fixed;
  top: 180px;
  left: calc((100vw - 1050px)/2 - 78px);
  ul {
    li {
      list-style: none;
      .btn-like {
        color: #6b6b6b;
        width: 40px;
        background: #fff;
        height: 40px;
        line-height: 40px;
        text-align: center;
        margin-bottom: 20px;
        border-radius: 50%;
      }
      .btn-like:hover{
        color: #f56c6c;
      }
      .detail-icon {
        font-size: 18px;
      }
    }
    p {
      margin: 0px;
      font-size: 12px;
    }
  }
 }
    .article_auth{
      font-size: 12px;
      padding-bottom: 15px;
      color: rgba(0,0,0,.65);
      span + span{
        padding-left: 10px;
      }
    }
</style>
