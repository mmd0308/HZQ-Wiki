<template>
  <div class="app-doc">
    <el-container>
      <el-header>
        <navbar :doc-status="docStatus" :doc-name="docName" :doc-privilege = "docPrivilege" @editStatus="editStatus"/>
      </el-header>
      <el-main style="padding:0px;  min-height: calc(100vh - 60px)">
        <document-main :doc-status="docStatus" :doc-id="docId" />
      </el-main>
    </el-container>
  </div>
</template>
<script>
import Navbar from './Navbar'
import DocumentMain from './DocMain'
import { docPrivilege } from '@/api/doc/index'
import { mapGetters } from 'vuex'
export default {
  name: 'Layout',
  components: {
    Navbar,
    DocumentMain
  },
  data() {
    return {
      docStatus: 'R', // 状态为可读
      docName: this.$route.query.docName,
      docId: this.$route.query.docId,
      spaceId: this.$route.query.spaceId,
      docPrivilege: '',
      paramsPrivilege: {
        userId: '',
        spaceId: this.$route.query.spaceId,
        docId: this.$route.query.docId
      }
    }
  },
  computed: {
    ...mapGetters([
      'userId'
    ])
  },
  created() {
    this.getPrivilege()
  },
  methods: {
    editStatus(stat) {
      this.docStatus = stat
    },
    getPrivilege() {
      this.paramsPrivilege.userId = this.userId
      docPrivilege(this.paramsPrivilege).then(res => {
        this.docPrivilege = res.data
      })
    }
  }
}
</script>
<style>
.app-doc{
  min-height: 100%
}
</style>
