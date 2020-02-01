<template>
  <div class="article">
    <div>
      <el-button type="info" size="mini" plain @click="clickTag(null)">全部</el-button>

      <div v-for="(item,index) in tagsLists" :key="index" style="display: inline;">
        <el-divider direction="vertical"/>
        <el-button type="info" size="mini" plain @click="clickTag(item.id)">
          {{ item.name }}
        </el-button>
      </div>
    </div>

    <div v-for="(item,index) in articleLists" v-if="item.title != ''" :key="index" class="item" >
      <div style="height: 22px;">
        <router-link :to="{path:'read/articles/' + item.id}">
          <span class="article_title" style="font-weight: 800;">
            {{ item.title }}
          </span>
        </router-link>
        <!-- <el-tag size="mini" type="warning">置顶</el-tag> -->
      </div>
      <div class="article_description">
        {{ item.hwDesc }}
      </div>
      <div class="article_auth">
        <span>
          作者: {{ item.createName }}
        </span>
        <span>
          创建时间: {{ item.createTime }}
        </span>
      </div>

    </div>
  </div>
</template>

<script>
import { showPage } from '@/api/article/article'
import { showAll } from '@/api/article/tag'
import $ from 'jquery'
export default {
  data() {
    return {
      moudle: 'articles',
      articleLists: [],
      tagsLists: [],
      total: 0,
      listQuery: {
        pageNum: 1,
        pageSize: 10,
        tagId: null
      },
      timeOut: null
    }
  },
  created() {
    this.showPage()
    this.showTagsAll()
  },
  methods: {
    showPage() {
      showPage(this.listQuery).then(res => {
        if (this.articleLists.length === 0) {
          this.articleLists = res.records
          this.total = res.total
        } else {
          res.records.forEach(item => {
            this.articleLists.push(item)
          })
        }
        const that = this
        // 滚动滑轮加载数据
        $(window).on('scroll', function() { // 给window绑定scroll触底事件
          if ($(window).scrollTop() + $(window).height() + 100 >= $(document).height()) {
            if (that.total > that.articleLists.length) { // 表示还有数据没有加载
              if (that.timeOut != null) {
                clearTimeout(that.timeOut)
              }
              that.timeOut = setTimeout(function() {
                // 加载下一页的数据
                that.listQuery.pageNum = that.listQuery.pageNum + 1
                that.showPage()
                // 演示0.5秒加载,避免并发加载错误的数据
              }, 500)
            }
          }
        })
      })
    },
    showTagsAll() {
      showAll().then(res => {
        this.tagsLists = res
      })
    },
    clickTag(id) {
      this.listQuery.tagId = id
      this.listQuery.pageNum = 1
      this.listQuery.pageSize = 10

      this.articleLists = []
      this.total = 0
      this.showPage()
    }
  }
}
</script>
<style rel="stylesheet/scss" lang="scss">
.article{
  .item{
    padding: 20px 0 8px;
    border-bottom:1px solid #e4ebf0;
    .article_description{
      margin-bottom: 20px;
      font-size: 14px;
      margin-top: 16px;
      color: rgba(0,0,0,.65);
      font-family: PingFang SC;
      font-weight: 400;
      line-height: 20px;
      overflow: hidden;
      text-overflow: ellipsis;
      display: -webkit-box;
      -webkit-line-clamp: 2;
    }
    .article_auth{
      font-size: 12px;
      color: rgba(0,0,0,.65);
      span + span{
        padding-left: 10px;
      }
    }
  }
  .item:last-child{
    border-bottom: none;
  }
}
</style>
