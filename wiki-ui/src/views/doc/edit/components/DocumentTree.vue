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
        <i class="el-icon-plus" @click="toAdd(null)"/>
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
      contentForm: this.initConentForm()
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
      this.filterNode(val)
    }
  },
  created() {
    this.tree()
  },
  methods: {
    initConentForm() {
      return {
        id: null,
        title: '',
        sequence: 0,
        parentId: -1, // 表示顶级菜单
        docId: this.docId,
        contentHtml: ' ',
        content: ' '
      }
    },
    initSetting() {
      var set = {
        view: {
          showLine: true,
          showIcon: false,
          selectedMulti: false,
          dblClickExpand: true, // 双击展开
          addHoverDom: null,
          removeHoverDom: null
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
          beforeDrag: this.beforeDragMethod, // 取消拖拽
          beforeEditName: this.beforeEditNameMethod,
          beforeRemove: this.beforeRemoveMethod,
          beforeRename: this.beforeRenameMethod,
          onClick: this.onClickMethod
        }
      }
      if (this.docStatus === 'E') {
        set.view.addHoverDom = this.addHoverDomMethod
        set.view.removeHoverDom = this.removeHoverDomMethod

        set.edit.enable = true
        set.edit.editNameSelectAll = true
        set.edit.showRemoveBtn = this.showRemoveBtnMethod
        set.edit.showRenameBtn = this.showRenameBtnMethod
      }
      return set
    },
    tree(conId) {
      all(this.docId).then(res => {
        this.zNodes = res.data
        $.fn.zTree.init($('#treeContent'), this.initSetting(), res.data)
        const zTree_Menu = $.fn.zTree.getZTreeObj('treeContent')
        var treeNode
        if (conId) { // 根据id进行选中
          treeNode = zTree_Menu.getNodeByParam('id', conId)
        } else {
          // 首次加载数据,或者没有制定选择时候,默认选择第一个
          treeNode = zTree_Menu.getNodes()[0]
        }
        zTree_Menu.selectNode(treeNode)
        this.selectContentTree(treeNode)
      })
    },
    toAdd(parentId) {
      // 初始化form表单
      this.contentForm = this.initConentForm()
      if (parentId) {
        this.contentForm.parentId = parentId
      }
      this.contentDialogFormVisible = true
    },
    addOrUpdate() {
      addOrUpdate(this.contentForm).then(res => {
        this.contentDialogFormVisible = false
        this.tree(res.data)
      })
    },
    // 取消树拖拽
    beforeDragMethod(treeId, treeNodes) {
      return false
    },
    beforeEditNameMethod(treeId, treeNode) {
      return true
    },
    beforeRemoveMethod(treeId, treeNode) {
      this.$confirm('此操作将永久删除[' + treeNode.title + ']文档?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        typr: 'warning'
      }).then(() => {
        deletedById(treeNode.id).then(() => {
          this.tree() // 刷新树
          this.$notify({
            title: '成功',
            message: '文档[' + treeNode.title + ']删除成功!',
            type: 'success'
          })
        })
      })
      return false // 返回false不执行onRemove
    },
    beforeRenameMethod(treeId, treeNode, newName, isCancel) {
      if (newName === '') {
        return false
      }
      // 修改名称
      this.contentForm.id = treeNode.id
      this.contentForm.title = newName
      addOrUpdate(this.contentForm).then(res => {
        this.$notify({
          title: '成功',
          message: '标题修改成功!',
          type: 'success'
        })
        return true
      })
    },
    toEdit(treeNode) {
      this.contentDialogFormVisible = true
      this.contentForm = this.initConentForm()
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
      this.selectContentTree(treeNode)
    },
    selectContentTree(treeNode) {
      bus.$emit('clickContentTree', treeNode)
    },
    addHoverDomMethod(treeId, treeNode) {
      var sObj = $('#' + treeNode.tId + '_span')
      if (treeNode.editNameFlag || $('#addBtn_' + treeNode.tId).length > 0) return
      var addStr = "<span class='button add' id='addBtn_" + treeNode.tId +	"' title='add node' onfocus='this.blur();'></span>"
      sObj.after(addStr)
      var btn = $('#addBtn_' + treeNode.tId)
      var that = this
      if (btn) {
        btn.bind('click', function() {
          that.toAdd(treeNode.id)
          // var zTree = $.fn.zTree.getZTreeObj('treeContent')
          // zTree.addNodes(treeNode, { id: 100, pId: treeNode.id, title: '新增文档节点' })
          return false
        })
      }
    },
    removeHoverDomMethod(treeId, treeNode) {
      $('#addBtn_' + treeNode.tId).unbind().remove()
    },
    filterNode(val) {
      var filterZNode = []
      this.zNodes.forEach(item => {
        if (item.title.indexOf(val) !== -1) {
          filterZNode.push(item)
        }
      })
      $.fn.zTree.init($('#treeContent'), this.initSetting(), filterZNode)
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
  .ztree li span.button {
    vertical-align: middle;
  }
  .ztree li span.button.add{
    margin-left: 2px;
    margin-right: -1px;
    background-position: -144px 0;
  }
}

</style>

