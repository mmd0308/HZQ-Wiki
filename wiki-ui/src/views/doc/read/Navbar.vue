<template>
  <div>
    <h3 style="margin:0px; float:left">
      {{ docForm.name }}
    </h3>
    <div style="float: right;">
      <el-button size="small" type="primary">
        <router-link to="/doc">
          首页
        </router-link>
      </el-button>
      <el-button v-if="editPrivilege()" size="small" type="success" @click="toEdit">
        编辑
      </el-button>
    </div>
  </div>
</template>
<script>
import { getPrivilegeByUserIdAndDocId } from '@/api/doc/userDOc'
import { getById } from '@/api/index'
import { mapGetters } from 'vuex'
import { isLogin } from '@/utils/authUtils'
import { userDocPrivilege } from '@/api/Constants'
export default {
  data() {
    return {
      moudle: 'show/docs',
      docId: this.$route.params.id,
      userDocPrivilege: userDocPrivilege,
      isEditDoc: false,
      docPrivilege: '',
      paramsPrivilege: {
        userId: '',
        spaceId: this.$route.params.spaceId,
        docId: this.docId
      },
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
    if (isLogin()) {
      // 如果登陆了,获取该用户对此文档的操作权限
      this.getPrivilege()
    }
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
      if (isLogin() && this.isEditDoc) { // 该拥有编辑此文档的权限
        return true
      }
      return false
    }
  }
}
</script>
