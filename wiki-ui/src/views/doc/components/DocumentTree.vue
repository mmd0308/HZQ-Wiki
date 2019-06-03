<template>
  <div class="doc-tree" style=" min-height: calc(100vh - 60px)">
    <!-- {{ docStatus }} --  {{ docId }} -->
    <el-input
      v-if="docStatus == 'R'"
      v-model="filterText"
      placeholder="输入关键字进行过滤"/>

    <div v-else>
      <div style="text-align:center;margin:10px 0px">
        文档标题
        <i class="el-icon-plus" @click="toAdd"/>
      </div>
      <hr style="margin:0px">
    </div>

    <div class="tree-box">
      <div class="zTreeDemoBackground left">
        <ul id="treeContent" class="ztree"/>
      </div>
    </div>

    <el-dialog :visible.sync="contentDialogFormVisible" :title="contentTitle" width="35%">
      <el-form :model="contentForm" label-width="80px">
        <el-form-item label="文档名称">
          <el-input v-model="contentForm.title" />
        </el-form-item>
        <el-form-item label="文档排序">
          <el-input
            id="tentacles"
            v-model="contentForm.sequence"
            type="number"
            min="10"
            max="100"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="contentDialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="addOrUpdate">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { all, addOrUpdate, deletedById } from '@/api/doc/content'
import $ from 'jquery'
import bus from '@/assets/js/eventbus'
export default {
  props: {
    docStatus: {
      type: String,
      required: true
    },
    docId: {
      type: Number,
      required: true
    }
  },
  data() {
    return {
      setting: this.initSetting(),
      log: 'dark',
      className: 'dark',
      zNodes: [],
      filterText: '',
      treeVisible: false,
      treeId: '',
      defaultProps: {
        children: 'children',
        label: 'name'
      },
      contentDialogFormVisible: false,
      contentTitle: '新增文档',
      contentForm: {
        title: '',
        sequence: 0,
        parentId: -1, // 表示顶级菜单
        docId: this.docId
      }
    }
  },
  watch: {
    docStatus: {
      immediate: true,
      handler: function(val, oldVal) {
        // 如果状态发生变化,需要修改树的状态
        this.tree()
      }
    },
    filterText(val) {
      this.$refs.tree2.filter(val)
    }
  },
  created() {
    this.tree()
  },
  methods: {
    initSetting() {
      var set = {
        view: {
          showLine: true,
          showIcon: false,
          selectedMulti: false,
          dblClickExpand: true
        },
        edit: {
          enable: false, // 设置 zTree 是否处于编辑状态
          editNameSelectAll: false, // 节点编辑名称 input 初次显示时,设置 txt 内容是否为全选状态。
          showRemoveBtn: false,
          showRenameBtn: false
        },
        data: {
          key: {
            name: 'title'
          },
          simpleData: {
            enable: true,
            idKey: 'id',
            pIdKey: 'parentId'
          }
        },
        callback: {
          // beforeDrag: this.beforeDrag(),
          // beforeEditName: this.beforeEditName(),
          // beforeRemove: this.beforeRemove(),
          // beforeRename: this.beforeRename(),
          // onRemove: this.onRemove(),
          // onRename: this.onRename()
          onClick: this.onClickMethod
        }
      }
      if (this.docStatus === 'E') {
        set.edit.enable = true
        set.edit.editNameSelectAll = true
        set.edit.showRemoveBtn = this.showRemoveBtnMethod
        set.edit.showRenameBtn = this.showRenameBtnMethod
      }
      return set
    },
    tree(conId) {
      all(this.docId).then(res => {
        $.fn.zTree.init($('#treeContent'), this.initSetting(), res.data)
        if (conId) { // 根据id进行选中
          const zTree_Menu = $.fn.zTree.getZTreeObj('treeContent')
          zTree_Menu.selectNode(zTree_Menu.getNodeByParam('id', conId))
        }
      })
    },
    filterNode(value, data) {
      if (!value) return true
      return data.label.indexOf(value) !== -1
    },
    toAdd() {
      this.contentDialogFormVisible = true
    },
    addOrUpdate() {
      addOrUpdate(this.contentForm).then(res => {
        this.contentDialogFormVisible = false
        this.tree(res.data)
      })
    },
    treeClick(data, node, event) {
      debugger
    },
    setCatalog(command, data) {
      // 新增自己文档操作
      if (command === 'add') {
        this.contentForm.parentId = data.id // 设置当前新增文档的父id
        this.toAdd()
      }

      // 修改操作
      if (command === 'edit') {
        alert('edit')
      }

      // 删除操作
      if (command === 'delete') {
        if (data.children.length === 0) {
          this.$confirm('此操作将永久删除[' + data.name + ']文档?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            typr: 'warning'
          }).then(() => {
            deletedById(data.id).then(() => {
              this.$notify({
                title: '成功',
                message: '文档[' + data.name + ']删除成功!',
                type: 'success'
              })
              this.page()
            })
          })
        } else {
          this.$notify({
            title: '错误',
            message: '该文档包含子级文档,不允许删除!',
            type: 'error'
          })
        }
      }
    },
    beforeDrag(treeId, treeNodes) {
      return false
    },
    beforeEditName(treeId, treeNode) {
      this.className = (this.className === 'dark' ? '' : 'dark')
      this.showLog('[ ' + this.getTime() + ' beforeEditName ]&nbsp;&nbsp;&nbsp;&nbsp; ' + treeNode.name)
      var zTree = $.fn.zTree.getZTreeObj('treeContent')
      zTree.selectNode(treeNode)
      setTimeout(function() {
        if (confirm('进入节点 -- ' + treeNode.name + ' 的编辑状态吗？')) {
          setTimeout(function() {
            zTree.editName(treeNode)
          }, 0)
        }
      }, 0)
      return false
    },
    beforeRemove(treeId, treeNode) {
      this.className = (this.className === 'dark' ? '' : 'dark')
      this.showLog('[ ' + this.getTime() + ' beforeRemove ]&nbsp;&nbsp;&nbsp;&nbsp; ' + treeNode.name)
      var zTree = $.fn.zTree.getZTreeObj('treeContent')
      zTree.selectNode(treeNode)
      return confirm('确认删除 节点 -- ' + treeNode.name + ' 吗？')
    },
    onRemove(e, treeId, treeNode) {
      this.showLog('[ ' + this.getTime() + ' onRemove ]&nbsp;&nbsp;&nbsp;&nbsp; ' + treeNode.name)
    },
    beforeRename(treeId, treeNode, newName, isCancel) {
      this.className = (this.className === 'dark' ? '' : 'dark')
      this.showLog((isCancel ? "<span style='color:red'>" : '') + '[ ' + this.getTime() + ' beforeRename ]&nbsp;&nbsp;&nbsp;&nbsp; ' + treeNode.name + (isCancel ? '</span>' : ''))
      if (newName.length == 0) {
        setTimeout(function() {
          var zTree = $.fn.zTree.getZTreeObj('treeContent')
          zTree.cancelEditName()
          alert('节点名称不能为空.')
        }, 0)
        return false
      }
      return true
    },
    onRename(e, treeId, treeNode, isCancel) {
      this.showLog((isCancel ? "<span style='color:red'>" : '') + '[ ' + this.getTime() + ' onRename ]&nbsp;&nbsp;&nbsp;&nbsp; ' + treeNode.name + (isCancel ? '</span>' : ''))
    },
    showLog(str) {
      if (!this.log) this.log = $('#log')
      this.log.append("<li class='" + this.className + "'>" + str + '</li>')
      if (this.log.children('li').length > 8) {
        this.log.get(0).removeChild(this.log.children('li')[0])
      }
    },
    showRemoveBtnMethod(treeId, treeNode) {
      if (!treeNode.children) { // 没有子节点显示删除按钮
        return true
      }
      return false
    },
    showRenameBtnMethod(treeId, treeNode) {
      return true
    },
    onClickMethod(event, treeId, treeNode) {
      bus.$emit('clickContentTree', treeNode)
    }
  }
}
</script>
<style rel="stylesheet/scss" lang="scss">
.custom-tree-node {
    flex: 1;
    display: flex;
    align-items: center;
    justify-content: space-between;
    font-size: 14px;
    padding-right: 8px;
  }

.doc-tree {
  .ztree{
    //margin-top: 10px;
    //background: #f0f6e4;
    padding: 0px;
    width: 280px;
    min-height: calc(100vh - 100px);
    overflow-y: scroll;
    overflow-x: auto;
  }
  .ztree li {
    padding: 0;
    margin: 0;
    list-style: none;
    line-height: 30px;
    text-align: left;
    white-space: nowrap;
    outline: 0;
  }
  .ztree li ul{
    margin: 0px;
    padding: 0 0 0 10px
  }
  .ztree li a{
    width: 280px;
    height: 30px;
    padding-top: 0px;
    margin: 0;
    text-decoration: none;
    vertical-align: top;
    display: inline-block;
  }
  .ztree li a:hover {
    background: #ff7d44;
  }

  .ztree li a.curSelectedNode{
    background-color: #ff7d44;
    border: 0;
    height: 30px;
  }
}

</style>

