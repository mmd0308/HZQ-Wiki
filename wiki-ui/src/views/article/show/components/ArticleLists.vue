<template>
  <div class="article">
    <div v-for="(item,index) in articleLists" v-if="item.title != ''" :key="index" class="item" >

      <div style="height: 22px;">
        <router-link :to="{path:'read/article/' + item.id}">
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
import { page } from '@/api/article/article'
export default {
  data() {
    return {
      articleLists: [],
      total: 0,
      listQuery: {
        pageNum: 1,
        pageSize: 15,
        hwState: '2'
      }
    }
  },
  created() {
    this.page()
  },
  methods: {
    page() {
      page(this.listQuery).then(response => {
        this.articleLists = response.data
        this.total = response.total
      })
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
