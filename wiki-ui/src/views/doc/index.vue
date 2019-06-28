<template>
  <div class="doc">
    <div v-for="(item,index) in docLists" :key="index" class="boxs" @click="toLink(item)">
      <!-- <router-link :to="{ path: '/doc/layout', query: { docId: item.id, docName: item.name, docPrivilege:item.privilege }}"> -->
      <div class="icon">
        <img v-if="item.img === null" class="pic-404__child right" src="@/assets/doc_images/doc.png" >
        <img v-else :src="item.img" class="pic-404__child right" @error="imgError(item)">
      </div>
      <h2 class="title">
        {{ item.name }}
      </h2>
      <div class="desc" >
        <span v-if="item.createName != null" class="auth">
          作者:{{ item.createName }}
        </span>
        <br>
        <span class="auth">
          创建时间:{{ item.createTime }}
        </span>
      </div>
      <!-- </router-link> -->
    </div>
  </div>
</template>

<script>
import { dashboardPage } from '@/api/doc/index'
import { mapGetters } from 'vuex'
import $ from 'jquery'
export default {
  data() {
    return {
      listQuery: {
        pageNum: 1,
        pageSize: 12,
        visitLevel: '1'
      },
      docLists: [],
      total: 0
    }
  },
  computed: {
    ...mapGetters([
      'userId'
    ])
  },
  created() {
    this.showDocPage()
  },
  methods: {
    toLink(item) {
      this.$router.push({
        path: '/doc/layout',
        query: {
          docId: item.id,
          docName: item.name,
          spaceId: item.spaceId
        }
      })
    },
    showDocPage() {
      dashboardPage(this.listQuery, this.userId).then(response => {
        if (this.docLists.length === 0) {
          this.docLists = response.data
          this.total = response.total
        } else {
          response.data.forEach(item => {
            this.docLists.push(item)
          })
        }
        const that = this
        $(window).on('scroll', function() { // 给window绑定scroll触底事件
          if ($(window).scrollTop() + $(window).height() + 1 >= $(document).height()) {
            if (that.total > that.docLists.length) { // 表示还有数据没有加载
              that.listQuery.pageNum = that.listQuery.pageNum + 1
              that.showDocPage()
            }
          }
        })
      })
    },
    imgError(item) {
      item.img = null
    },
    handleSizeChange() {

    },
    handleCurrentChange(val) {
      this.listQuery.pageNum = val
      this.showDocPage()
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.doc {
  .boxs{
    width: 247px;
    height: 294px;
    min-width: 245px;
    float: left;
    display: block;
    position: relative;
    min-height: 120px;
    overflow: hidden;
    cursor: pointer;
    margin-top: -1px;
    border: 1px solid #e4ebf0;
    padding: 0;
    .icon{
      position: absolute;
      top: 0px;
      img{
        width: 247px;
        height: 220px;
      }
    }
    .title{
      position: relative;
      top:220px;
      font-size: 17px;
      margin:5px 15px;
    }
    .desc{
      position: relative;
      top:220px;
      font-size: 12px;
      margin:5px 15px;
    }
    .auth{
      line-height: 18px;
      color:#666;
    }
  }
  .boxs:nth-of-type(4n+2){
  margin-left: -1px;
  }
}
</style>
