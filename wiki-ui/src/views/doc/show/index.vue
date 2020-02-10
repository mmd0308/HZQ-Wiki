<template>
  <div class="doc">
    <div style="margin-bottom: 15px;">
      <el-button type="info" size="mini" plain @click="clickTag(null)">全部</el-button>
      <div v-for="(item,index) in spaceLists" :key="index" style="display: inline;">
        <el-divider direction="vertical"/>
        <el-button type="info" size="mini" plain @click="clickTag(item.id)">
          {{ item.name }}
        </el-button>
      </div>
      <div v-if="(userId != null && userId != '') && (token != null && token != '')" style="position: absolute;right: 0px;top: 0px;">
        <el-button type="primary" size="mini" @click="clickToAdd()">创建文档</el-button>
      </div>
    </div>
    <div v-for="(item,index) in docLists" :key="index" class="boxs">
      <router-link :to="{ path: '/read/doc/' + item.id}">
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
      </router-link>
    </div>

    <!--新增 -->
    <el-drawer
      :visible.sync="drawer"
      :with-header="false"
      class="hzq-drawer">
      <h4 class="header">新增文档</h4>
      <el-divider/>
      <div class="content">
        <el-form :model="drawerForm" label-width="80px">
          <el-form-item label="文档名称">
            <el-input v-model="drawerForm.name"/>
          </el-form-item>
          <el-form-item label="文档权限">
            <el-radio v-model="drawerForm.visitLevel" :label="docVisitLevel['PUBLIC'].value">公开</el-radio>
            <el-radio v-model="drawerForm.visitLevel" :label="docVisitLevel['PRIVATE'].value">私有</el-radio>
          </el-form-item>
          <el-form-item label="备注说明">
            <el-input
              v-model="drawerForm.remark"
              type="textarea"
              placeholder="请输入内容"
              maxlength="100"
              show-word-limit
            />
          </el-form-item>
        </el-form>

        <div class="footer">
          <el-button size="medium" type="info" @click="drawer = false">取消</el-button>
          <el-button size="medium" type="primary" @click="handleSaveClick">保存 </el-button>
        </div>
      </div>
    </el-drawer>
  </div>
</template>
<script>
import { showPage } from '@/api/doc/index'
import { mapGetters } from 'vuex'
import { showAll } from '@/api/space/index'
import { docVisitLevel } from '@/api/doc/DocConstants'
import { create } from '@/api/index'
import $ from 'jquery'
export default {
  data() {
    return {
      listQuery: {
        pageNum: 1,
        pageSize: 12,
        spaceId: null
      },
      spaceLists: [],
      docLists: [],
      total: 0,
      docVisitLevel: docVisitLevel,
      drawer: false,
      drawerForm: this.initDrawerForm()
    }
  },
  computed: {
    ...mapGetters([
      'userId',
      'token'
    ])
  },
  created() {
    this.showDocPage()
    this.showSpaceAll()
  },
  methods: {
    initDrawerForm() {
      return {
        id: null,
        name: null,
        spaceId: null,
        visitLevel: 'PUBLIC',
        remark: null
      }
    },
    showSpaceAll() {
      showAll().then(res => {
        this.spaceLists = res
      })
    },
    clickTag(id) {
      this.listQuery.spaceId = id

      this.listQuery.pageNum = 1
      this.listQuery.pageSize = 12

      this.docLists = []
      this.total = 0
      this.showDocPage()
    },
    showDocPage() {
      showPage(this.listQuery).then(response => {
        if (this.docLists.length === 0) {
          this.docLists = response.records
          this.total = response.total
        } else {
          response.records.forEach(item => {
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
    clickToAdd() {
      this.drawer = true
    },
    handleSaveClick() {
      // 创建
      create('docs', this.drawerForm).then(() => {
        this.drawer = false
        this.drawerForm = this.initDrawerForm()
        window.location.reload()
      })
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
  .boxs:nth-of-type(4n+1){
    margin-left: -1px;
  }
}
</style>
