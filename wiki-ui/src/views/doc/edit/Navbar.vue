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
      <el-button v-if="docStatus == 'R' && docPrivilege != '1'" size="small" type="success" @click="toEdit">
        编辑
      </el-button>
    </div>
  </div>
</template>
<script>
import { get, docPrivilege } from '@/api/doc/index'
import { mapGetters } from 'vuex'
import bus from '@/assets/js/eventbus'
export default {
  props: {
    docStatus: {
      type: String,
      required: true
    }
  },
  data() {
    return {
      docId: this.$route.params.id,
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
      get(this.docId).then(res => {
        this.docForm = res.data
      })
    },
    toEdit() {
      this.docStatus = 'E'
      this.$emit('editStatus', this.docStatus)
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
