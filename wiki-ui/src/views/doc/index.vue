<template>
  <div class="doc">
    <div v-for="(item,index) in docLists" :key="index" class="boxs">
      <router-link :to="{ path: '/doc/layout', query: { docId: item.id, docName: item.name }}">
        <div class="icon">
          <img v-if="item.img === null" class="pic-404__child right" src="@/assets/doc_images/doc.png" >
          <img v-else :src="item.img" class="pic-404__child right">
        </div>
        <h2 style="position: relative;top:206px;font-size: 17px; margin:5px 15px;">
          {{ item.name }}
        </h2>
        <div style="position: relative;top:206px;font-size: 12px; margin:5px 15px;">
          <span v-if="item.createName != null" class="auth">
            作者:{{ item.createName }}
          </span>
          <br>
          <span class="auth">
            创建时间:{{ item.createTime }}
          </span>
        </div>
      </router-link>
    </div>
  </div>
</template>

<script>
import { dashboardPage } from '@/api/doc/index'
import { mapGetters } from 'vuex'
export default {
  data() {
    return {
      listQuery: {
        pageNum: 1,
        pageSize: 10,
        visitLevel: '1'
      },
      docLists: []
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
    showDocPage() {
      dashboardPage(this.listQuery, this.userId).then(response => {
        this.docLists = response.data
        this.total = response.total
      })
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.doc {
  .boxs{
    width: 247px;
    height: 290px;
    min-width: 245px;
    float: left;
    display: block;
    position: relative;
    min-height: 120px;
    overflow: hidden;
    cursor: pointer;
    margin-left: -1px;
    margin-top: -1px;
    border: 1px solid #e4ebf0;
    padding: 0;
    .icon{
      width: 100%;
      height: 209px;
      position: absolute;
      top: 15px;
    }
    .desc{
      position:relative;
      top:202px;
      font-size:13px;
      margin:5px 15px;
      overflow: hidden;
      line-height: 20px;
      max-height: 40px;
    }
    // 兼容火狐
    .desc::after{
      content: "...";
      position: absolute;
      bottom: 0;
      right: 0;
      padding-left: 50px;
      background: -webkit-linear-gradient(left, transparent, #fff 55%);
      background: -o-linear-gradient(right, transparent, #fff 55%);
      background: -moz-linear-gradient(right, transparent, #fff 55%);
      background: linear-gradient(to right, transparent, #fff 55%);
    }
    .auth{
      padding: 2px 0px;
      line-height: 16px;
      color:#666;
    }
  }
}
</style>
