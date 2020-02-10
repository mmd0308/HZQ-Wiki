<template>
  <div class="article">
    <div
      v-loading="tableLoading"
      element-loading-text="拼命加载中"
      element-loading-spinner="el-icon-loading"
      element-loading-background="rgba(255, 255, 255, 0.83)" >
      <div
        v-for="(item,index) in articleLists"
        v-if="item.title != ''"
        :key="index"
        class="item" >
        <div style="height: 22px;">
          <router-link :to="{ path:'read/article/' + item.id }">
            <span class="article_title" style="font-weight: 800;">
              {{ item.title }}
            </span>
          </router-link>
        </div>
        <div class="article_description">
          {{ item.hwDesc }}
        </div>
        <div class="article_auth">
          <span>
            作者: {{ item.createName }}
          </span>
          <span>
            最后更新时间: {{ item.updateTime }}
          </span>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
import { showPage } from '@/api/article/article'
import $ from 'jquery'
export default {
  data() {
    return {
      moudle: 'articles',
      articleLists: [],
      tableLoading: false,
      listQuery: {
        pageNum: 1,
        pageSize: 5
      },
      timeOut: null
    }
  },
  created() {
    this.showPage()
  },
  methods: {
    showPage() {
      this.tableLoading = true
      showPage(this.listQuery).then(res => {
        this.articleLists = res.records
        this.tableLoading = false
      })
    }
  }
}
</script>
<style rel="stylesheet/scss" lang="scss">
.article{
  padding-top: 15px;
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
