<template>
  <div class="read-height">
    <mavon-editor
      ref="mavonEditor"
      :subfield="false"
      :toolbars-flag="false"
      :code-style="code_style"
      v-model="articleForm.content"
      class="height:100%;"
      default-open="preview" />

    <div class="hzq-right-bottom-toolbar" style="right: 100px;">
      <!-- 回到顶端 -->
      <div
        v-if="backtop"
        style="border-radius: 50%; background: #ffffff; width: 40px; height: 40px; text-align: center; color: #8c8c8c;"
        class="option-box"
        @click="backTop">
        <i style="line-height:40px" class="el-icon-caret-top" />
      </div>
    </div>
  </div>
</template>

<script>
import { showGetById } from '@/api/article/article'
export default {
  data() {
    return {
      moudle: 'articles',
      backtop: false,
      articleForm: this.init(),
      code_style: 'dark'
    }
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
    },
    handleScroll() {
      var scrollTop =
        window.pageYOffset ||
        document.documentElement.scrollTop ||
        document.body.scrollTop
      const tools = this.$refs.toolBox
      if (scrollTop < 50) {
        tools.style.setProperty('--toolBoxHeight', '0px')
      } else {
        tools.style.setProperty('--toolBoxHeight', scrollTop - 50 + 'px')
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
}
.tool-box {
  --toolBoxHeight: 216px;
  z-index: 2;
  position: fixed;
  left: 100px;
  top: var(--toolBoxHeight);
  ul {
    li {
      list-style: none;
      .btn-like {
        color: #6b6b6b;
        width: 44px;
        background: #fff;
        height: 56px;
        text-align: center;
        margin-bottom: 6px;
        border-radius: 6px;
      }
      .btn-like:hover {
        color: #f56c6c;
      }
      .detail-icon {
        margin-top: 7px;
        margin-bottom: 3px;
        font-size: 18px;
      }
    }
    p {
      margin: 0px;
      font-size: 12px;
    }
  }
}
</style>
