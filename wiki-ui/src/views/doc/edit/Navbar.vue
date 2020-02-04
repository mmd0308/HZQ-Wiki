<template>
  <div>
    <h3 style="margin:0px; float:left">
      {{ docForm.name }}
    </h3>

    <div style="float: right;">
      <h3 v-if="docStatus != 'R'" style="color:#fff;margin:0px 20px; float:left">
        {{ saveStatus }}
      </h3>
      <el-button size="small" type="primary">
        <router-link to="/">
          首页
        </router-link>
      </el-button>
      <!-- 可读模式或者浏览模式,允许编辑 -->
      <!-- docPrivilege != '1' -->
      <el-button v-if="editPrivilege()" size="small" type="success" @click="toEdit">
        编辑
      </el-button>
    </div>
  </div>
</template>
<script>
import { docPrivilege } from '@/api/doc/index'
import { getPrivilegeByUserIdAndDocId } from '@/api/doc/userDOc'
import { getById } from '@/api/index'
import { mapGetters } from 'vuex'
import { isAdmin, isLogin } from '@/utils/authUtils'
import { userDocPrivilege } from '@/api/Constants'
import bus from '@/assets/js/eventbus'
export default {
  data() {
    return {
      moudle: 'show/docs',
      docStatus: this.$route.path.startsWith('/write') ? 'E' : 'R',
      docId: this.$route.params.id,
      userDocPrivilege: userDocPrivilege,
      isEditDoc: false,
      docPrivilege: '',
      paramsPrivilege: {
        userId: '',
        spaceId: this.$route.params.spaceId,
        docId: this.docId
      },
      saveStatus: '',
      docForm: {
        name: ''
      }
    }
  },
  computed: {
    ...mapGetters([
      'userId'
    ])
  },
  created() {
    this.get()
    this.getPrivilege()
  },
  mounted() {
    var that = this
    bus.$on('editDocSaveState', function(val) {
      that.saveStatus = val
    })
  },
  methods: {
    get() {
      getById(this.moudle, this.docId).then(res => {
        this.docForm = res
      })
    },
    toEdit() {
      if (this.$route.path.startsWith('/read')) {
        // this.$router.push({ path: '/write' + this.$route.path.substr(5) })
        // 组件从新加载
        window.location.href = '/write' + this.$route.path.substr(5)
      }
    },
    getPrivilege() {
      this.paramsPrivilege.userId = this.userId
      getPrivilegeByUserIdAndDocId(this.docId).then(res => {
        this.isEditDoc = res
      })
    },
    editPrivilege() {
      if (this.docStatus === 'R' && isLogin() && this.isEditDoc) { // 该拥有编辑此文档的权限
        return true
      }
      return false
    }
  }
}
</script>
